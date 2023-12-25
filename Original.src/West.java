public class West implements Direction{
    @Override
    public int getRowMove(int position, int i) {
        return position;
    }
    @Override
    public int getColMove(int position, int i) {
        return position - i;
    }
}
