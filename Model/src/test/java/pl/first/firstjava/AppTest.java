package pl.first.firstjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    public AppTest() {
    }

    @Test
    public void testEmptParams() {
        App test;
        test = new App();
        String[] params = {};
        try {
            test.main(params);
        } catch (ArrayIndexOutOfBoundsException oobe) {
            System.out.println("Expected exception" + oobe);
        }
        test.main(params);
    }

    @Test
    public void testWithParams() {
        String[] params = {"Student"};
        App.main(params);

    }
}

