//package pl.first.firstjava;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
///**
// *
// * @author Jakub Mielczarek | Huber Tasarz
// */
//
//class SudokuBoardTest {
//
//    public SudokuBoardTest(){
//    }
//
//    @Test
//    public void testSudokuBoard() {
//        SudokuBoard testSudoku1 = new SudokuBoard();
//        testSudoku1.fillBoard();
//        SudokuBoard testSudoku2 = new SudokuBoard();
//        testSudoku2.fillBoard();
//        assertNotEquals(testSudoku1, testSudoku2);
//        for (int j = 0; j < 9; j++) {
//            for (int i = 0; i < 9; i++) {
//                int x = testSudoku1.getBoard(i, j);
//                if(j < 3){
//                    if(i < 3){
//                        for (int z = 0; z < 3; z++) {
//                            for (int y = 0; y < 3; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else if(i < 6){
//                        for (int z = 0; z < 3; z++) {
//                            for (int y = 3; y < 6; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        for (int z = 0; z < 3; z++) {
//                            for (int y = 6; y < 9; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                }
//                else if(j < 6){
//                    if(i < 3){
//                        for (int z = 3; z < 6; z++) {
//                            for (int y = 0; y < 3; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else if(i < 6){
//                        for (int z = 3; z < 6; z++) {
//                            for (int y = 3; y < 6; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        for (int z = 3; z < 6; z++) {
//                            for (int y = 6; y < 9; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                }
//                else{
//                    if(i < 3){
//                        for (int z = 6; z < 9; z++) {
//                            for (int y = 0; y < 3; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else if(i < 6){
//                        for (int z = 6; z < 9; z++) {
//                            for (int y = 3; y < 6; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        for (int z = 6; z < 9; z++) {
//                            for (int y = 6; y < 9; y++) {
//                                if (i != y && j != z) {
//                                    assertNotEquals(x, testSudoku1.getBoard(y, z));
//                                }
//                            }
//                        }
//                    }
//                }
//                for (int z = 0; z < 9; z++) {
//                    if (j != z) {
//                        assertNotEquals(x, testSudoku1.getBoard(i, z));
//                    }
//                    if (i != z) {
//                        assertNotEquals(x, testSudoku1.getBoard(z, j));
//                    }
//                }
//            }
//        }
//    }
//}