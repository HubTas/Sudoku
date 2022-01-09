package pl.first.firstjava;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }


    @Override
    public SudokuBoard read() {
        SudokuBoard o = null;

        try (FileInputStream input = new FileInputStream(filename);
             ObjectInputStream input2 = new ObjectInputStream(input)) {
            o = (SudokuBoard) input2.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    @Override
    public void write(SudokuBoard o) {
        try (FileOutputStream output = new FileOutputStream(filename);
             ObjectOutputStream output2 = new ObjectOutputStream(output)) {
            output2.writeObject(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            FileInputStream write = new FileInputStream(filename);
            FileOutputStream read = new FileOutputStream(filename);
            write.close();
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
