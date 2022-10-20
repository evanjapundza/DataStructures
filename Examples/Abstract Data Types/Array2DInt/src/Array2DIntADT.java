
public interface Array2DIntADT {

    public void set(int row, int col, int num);
    public int get(int row, int col);
    public void setElementsToZero();
    public int[] rowGet(int row);
    public void rowDelete(int row);
    public int[] columnGet(int col);
    public void columnDelete(int col);
}
