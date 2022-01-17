package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.first.firstjava.exception.SudokuDaoException;
import java.io.IOException;

public class BaseSudokuBoardDaoTest {

    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardSecond;
    private SudokuBoard sudokuBoardThird = new SudokuBoard(sudokuSolver);
    private SudokuBoard sudokuBoardForth = new SudokuBoard(sudokuSolver);
    private Dao<SudokuBoard> databaseSudokuBoardDao;

    @Test
    public void writeReadDbTest() throws IOException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("Test")) {
            databaseSudokuBoardDao = jdbcSudokuBoardDao;
            databaseSudokuBoardDao.write(sudokuBoard);
            sudokuBoardSecond = databaseSudokuBoardDao.read();

            assertThrows(NullPointerException.class, () -> jdbcSudokuBoardDao.write(null));
        }
        assertEquals(sudokuBoard, sudokuBoardSecond);
        assertFalse(sudokuBoard==sudokuBoardSecond);

        assertThrows(SudokuDaoException.class, () -> databaseSudokuBoardDao.read());

    }
}
