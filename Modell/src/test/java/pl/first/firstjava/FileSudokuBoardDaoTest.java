package pl.first.firstjava;

import org.junit.jupiter.api.Test;
import pl.first.firstjava.exception.SudokuDaoException;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest() throws SudokuDaoException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2;
        Dao<SudokuBoard> fileSudokuBoardDao;
        fileSudokuBoardDao = factory.getFileDao("source");
        fileSudokuBoardDao.write(board);
        board2 = fileSudokuBoardDao.read();
        assertEquals(board,board2);
    }

    @Test
    public void readExceptionTest(){
        assertThrows(SudokuDaoException.class,()->{
                SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
                Dao<SudokuBoard> fileSudokuBoardDao;
                fileSudokuBoardDao = factory.getFileDao("source2");
                fileSudokuBoardDao.read();
                });
    }

    @Test
    public void writeExceptionTest(){
        assertThrows(SudokuDaoException.class,()->{
            SudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> fileSudokuBoardDao;
            SudokuBoard board = new SudokuBoard(solver);
            fileSudokuBoardDao = factory.getFileDao("??");
            fileSudokuBoardDao.write(board);
        });
    }
}
