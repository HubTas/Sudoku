package pl.first.firstjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

    @Test
    public void writeAndReadTest(){
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
        assertThrows(RuntimeException.class,()->{
                SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
                Dao<SudokuBoard> fileSudokuBoardDao;
                fileSudokuBoardDao = factory.getFileDao("source2");
                fileSudokuBoardDao.read();
                });
    }

    @Test
    public void writeExceptionTest(){
        assertThrows(RuntimeException.class,()->{
            SudokuSolver solver = new BacktrackingSudokuSolver();
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> fileSudokuBoardDao;
            SudokuBoard board = new SudokuBoard(solver);
            fileSudokuBoardDao = factory.getFileDao("??");
            fileSudokuBoardDao.write(board);
        });
    }
}
