package pl.first.firstjava;

import pl.first.firstjava.exception.SudokuDaoException;

import java.sql.Connection;
import java.sql.*;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>,AutoCloseable{
    private static String url = "jdbc:postgresql://localhost:5432/SudokuBoard";
    private static String username = "postgres";
    private static String password = "user";
    private String filename;
    private ResultSet resultSet;
    Connection connection;
    Statement statement;

    public JdbcSudokuBoardDao(String file) {
        this.filename = file;
        try{
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Nie Udalo sie polaczyc");
        }
    }

    @Override
    public void close() throws Exception {
        try{
            connection.close();

        } catch (SQLException e){
           e.printStackTrace();
        }
    }

    @Override
    public SudokuBoard read() throws SudokuDaoException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        String values;
        String readQuery = "select board_name, values from saves where board_name=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
            preparedStatement.setString(1,filename);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            values = resultSet.getString(2);
            board.makeSudokuBoardFromString(values);
            statement.close();
            resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return board;

    }

    @Override
    public void write(SudokuBoard obj) throws SudokuDaoException {
        String values = obj.toString();
        String query = "insert into saves(board_name,values) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,filename);
            statement.setString(2,values);
            statement.executeUpdate();
        } catch(SQLException e){
            throw new SudokuDaoException("e");
        }
    }
}
