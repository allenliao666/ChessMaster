import javax.swing.*;

public class KingCheckedMessage extends Message{
    public KingCheckedMessage(ChessGameBoard board, int currentPlayer){
        super(board,currentPlayer);
    }

    @Override
    public void showMessage() {
        JOptionPane.showMessageDialog(
                board.getParent(),
                "Be careful player " + currentPlayer + ", " +
                        "your king is in check! Your next move must get " +
                        "him out of check or you're screwed.",
                "Warning",
                JOptionPane.WARNING_MESSAGE );
    }
}
