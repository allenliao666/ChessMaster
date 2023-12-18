import javax.swing.*;

public class Message {
    BoardSquare squareClicked;
    public Message(BoardSquare squareClicked){
        this.squareClicked = squareClicked;
    }

    public void showMessage(){
        System.out.println("Error");

    }
}
