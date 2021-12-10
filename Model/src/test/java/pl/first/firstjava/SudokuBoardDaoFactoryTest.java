package pl.first.firstjava;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {
    @Test
    public void getFileDaoTest(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("text"));
    }
}
