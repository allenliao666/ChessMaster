import javax.swing.*;

public class ChooseEmptyBoard extends Message{
    public ChooseEmptyBoard(BoardSquare squareClicked){
        super(squareClicked);
    }

    @Override
    public void showMessage() {
        JOptionPane.showMessageDialog(
                squareClicked,
                "You tried to pick up an empty square! "
                        + "Get some glasses and pick a valid square.",
                "Illegal move",
                JOptionPane.ERROR_MESSAGE );
    }
}
