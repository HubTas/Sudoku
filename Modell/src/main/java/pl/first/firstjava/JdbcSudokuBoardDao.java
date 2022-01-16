package pl.first.firstjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.first.firstjava.exception.DataBaseException;
import pl.first.firstjava.exception.SudokuDaoException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>,AutoCloseable {
    private static String url = "jdbc:postgresql://localhost:5432/SudokuBoard";
    private static String username = "postgres";
    private static String password = "Haslo";
    private String filename;
    private ResultSet resultSet;
    Connection connection;
    Statement statement;

    public JdbcSudokuBoardDao(String file) {
        this.filename = file;
        try {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie Udalo sie polaczyc");
        }
    }

    @Override
    public void close() throws DataBaseException {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
           throw new DataBaseException("Bad data base exception");
        }
    }

    @Override
    public SudokuBoard read() throws SudokuDaoException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        String values;
        String readQuery = "select board_name, values from saves where board_name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)) {
            preparedStatement.setString(1,filename);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            values = resultSet.getString(2);
            board.makeSudokuBoardFromString(values);
            resultSet.close();
        } catch (SQLException e) {
            throw new DataBaseException("Bad read data base exception");
        }
        return board;

    }

    @Override
    public void write(SudokuBoard obj) throws SudokuDaoException {
        String values = obj.toString();
        String query = "insert into saves(board_name,values) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,filename);
            statement.setString(2,values);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException("Bad write data base exception");
        }
    }
}
