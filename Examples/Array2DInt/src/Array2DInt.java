import java.util.*;

public class Array2DInt implements Array2DIntADT {

    int[][] userArray;
    public int rows;
    public int cols;

    Array2DInt(int row, int col) {
        this.rows = row;
        this.cols = col;
        userArray = new int[row][col];
    }

    @Override
    public void set(int row, int col, int num) {
        userArray[row][col] = num;
    }

    @Override
    public int get(int row, int col) {
        return userArray[row][col];
    }

    @Override
    public void setElementsToZero() {

        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                userArray[i][j] = 0;
            }
        }
    }

    @Override
    public int[] rowGet(int row) {
        int[] theRow = new int[this.cols];

        for(int i = 0; i < this.cols; i++) {
            theRow[i] = userArray[row][i];
        }

        return theRow;
    }

    @Override
    public void rowDelete(int row) {
        int[][] tempArr = new int[this.rows-1][this.cols];
        int counter = 0;

        for(int i = 0; i < this.rows; i++) {
            if(i != row) {
                for(int j = 0; j < this.cols; j++) {
                    tempArr[counter][j] = userArray[i][j];
                }
                counter++;
            }

        }

        userArray = tempArr;
        this.rows = this.rows-1;
    }

    @Override
    public int[] columnGet(int col) {
        int[] theCol = new int[this.rows];

        for(int i = 0; i < this.rows; i++) {
            theCol[i] = userArray[i][col];
        }

        return theCol;
    }

    @Override
    public void columnDelete(int col) {
        int[][] tempArr = new int[this.rows][this.cols-1];
        int counter = 0;

        for(int i = 0; i < this.cols; i++) {
            if(i != col) {
                for(int j = 0; j < this.rows; j++) {
                    tempArr[j][counter] = userArray[j][i];

                }
                counter++;
            }

        }

        userArray = tempArr;
        this.cols = this.cols-1;
    }

    public void printArray(){
        System.out.println(Arrays.deepToString(userArray));
    }

    public static void main(String[] args) {

        Array2DInt tester1 = new Array2DInt(2, 2);
        tester1.set(0,0, 1);
        tester1.set(1,0,2);
        tester1.set(0,1,5);
        tester1.set(1,1,8);
        System.out.print("Array after inserting values: ");
        tester1.printArray();
        System.out.print("Get value at position row 1, column 1: ");
        System.out.println(tester1.get(1,1));
        System.out.print("Get row 0: ");
        System.out.println(Arrays.toString(tester1.rowGet(0)));
        System.out.print("Get col 1: ");
        System.out.println(Arrays.toString(tester1.columnGet(1)));
        System.out.print("Array after deleting row 2: ");
        tester1.rowDelete(1);
        tester1.printArray();
        System.out.print("Array after deleting column 2: ");
        tester1.columnDelete(1);
        tester1.printArray();
        System.out.print("Array after setting elements to zero: ");
        tester1.setElementsToZero();
        tester1.printArray();

        System.out.println("-----------------------------------------------");

        Array2DInt tester2 = new Array2DInt(4, 4);
        tester2.set(0,0, 1);
        tester2.set(0,1,5);
        tester2.set(0,2,8);
        tester2.set(0,3,16);
        tester2.set(1,0,27);
        tester2.set(1,1,44);
        tester2.set(1,2,84);
        tester2.set(1,3,23);
        tester2.set(2,0,13);
        tester2.set(2,1,3);
        tester2.set(2,2,2);
        tester2.set(2,3,7);
        tester2.set(3,0,6);
        tester2.set(3,1,10);
        tester2.set(3,2,22);
        tester2.set(3,3,39);
        System.out.print("Array after inserting values: ");
        tester2.printArray();
        System.out.print("Get value at position row 1, column 3: ");
        System.out.println(tester2.get(1,3));
        System.out.print("Get row 3: ");
        System.out.println(Arrays.toString(tester2.rowGet(3)));
        System.out.print("Get col 0: ");
        System.out.println(Arrays.toString(tester2.columnGet(0)));
        System.out.print("Array after deleting row 2: ");
        tester2.rowDelete(1);
        tester2.printArray();
        System.out.print("Array after deleting column 2: ");
        tester2.columnDelete(1);
        tester2.printArray();
        System.out.print("Array after setting elements to zero: ");
        tester2.setElementsToZero();
        tester2.printArray();

        System.out.println("-----------------------------------------------");

        Array2DInt tester3 = new Array2DInt(2, 4);
        tester3.set(0,0, 1);
        tester3.set(0,1,3);
        tester3.set(0,2,6);
        tester3.set(0,3,1);
        tester3.set(1,0,87);
        tester3.set(1,1,82);
        tester3.set(1,2,19);
        tester3.set(1,3,9);
        System.out.print("Array after inserting values: ");
        tester3.printArray();
        System.out.print("Get value at position row 1, column 0: ");
        System.out.println(tester3.get(1,0));
        System.out.print("Get row 1: ");
        System.out.println(Arrays.toString(tester3.rowGet(1)));
        System.out.print("Get col 2: ");
        System.out.println(Arrays.toString(tester3.columnGet(2)));
        System.out.print("Array after deleting row 2: ");
        tester3.rowDelete(1);
        tester3.printArray();
        System.out.print("Array after deleting column 2: ");
        tester3.columnDelete(1);
        tester3.printArray();
        System.out.print("Array after setting elements to zero: ");
        tester3.setElementsToZero();
        tester3.printArray();

        System.out.println("-----------------------------------------------");
    }
}
