import javax.swing.*;

public class InvalidMessage extends Message{
    private int row;
    private int col;
    public InvalidMessage(BoardSquare squareClicked){
        super(squareClicked);
    }

    @Override
    public void showMessage() {
        this.row = squareClicked.getRow();
        this.col = squareClicked.getColumn();
        JOptionPane.showMessageDialog(squareClicked, "The move to row " + ( row + 1 ) + " and column "
                        + ( col + 1 )
                        + " is either not valid or not legal "
                        + "for this piece. Choose another move location, "
                        + "and try using your brain this time!",
                "Invalid move",
                JOptionPane.ERROR_MESSAGE );
    }
}
