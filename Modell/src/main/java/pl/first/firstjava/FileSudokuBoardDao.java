package pl.first.firstjava;

import pl.first.firstjava.exception.SudokuDaoException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename + ".txt";
    }


    @Override
    public SudokuBoard read() throws SudokuDaoException {
        SudokuBoard o = null;

        try (FileInputStream input = new FileInputStream(filename);
             ObjectInputStream input2 = new ObjectInputStream(input)) {
            o = (SudokuBoard) input2.readObject();
        } catch (IOException e) {
            throw new SudokuDaoException("daoReadException");
        } catch (ClassNotFoundException e) {
            throw new SudokuDaoException("daoReadException");
        }
        return o;
    }

    @Override
    public void write(SudokuBoard o) throws SudokuDaoException {
        try (FileOutputStream output = new FileOutputStream(filename);
             ObjectOutputStream output2 = new ObjectOutputStream(output)) {
            output2.writeObject(o);
        } catch (IOException e) {
            throw new SudokuDaoException("daoWriteException");
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
