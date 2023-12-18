import javax.swing.*;

public class Message {
    BoardSquare squareClicked;
    ChessGameBoard board;
    int currentPlayer;
    public Message(BoardSquare squareClicked){
        this.squareClicked = squareClicked;
    }

    public Message(ChessGameBoard board, int currentPlayer){
        this.board = board;
        this.currentPlayer = currentPlayer;
    }


    public void showMessage(){
        System.out.println("Error");

    }
}
