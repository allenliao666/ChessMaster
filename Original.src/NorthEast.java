public class NorthEast implements Direction{
    @Override
    public int getRowMove(int position, int i) {
        return position -i;
    }
    @Override
    public int getColMove(int position, int i) {
        return position + i;
    }
}
