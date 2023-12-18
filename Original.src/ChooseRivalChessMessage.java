import javax.swing.*;

public class ChooseRivalChessMessage extends Message{
    public ChooseRivalChessMessage(BoardSquare squareClicked){
        super(squareClicked);
    }

    @Override
    public void showMessage() {
        JOptionPane.showMessageDialog(
                squareClicked,
                "You tried to pick up the other player's piece! "
                        + "Get some glasses and pick a valid square.",
                "Illegal move",
                JOptionPane.ERROR_MESSAGE );    }
}
