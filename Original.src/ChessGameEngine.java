import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
// -------------------------------------------------------------------------
/**
 * This is the backend behind the Chess game. Handles the turn-based aspects of
 * the game, click events, and determines win/lose conditions.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessGameEngine{
    private ChessGamePiece currentPiece;
    private boolean        firstClick;
    private int            currentPlayer;
    private ChessGameBoard board;
    private King           king1;
    private King           king2;
    // ----------------------------------------------------------
    /**
     * Create a new ChessGameEngine object. Accepts a fully-created
     * ChessGameBoard. (i.e. all components rendered)
     *
     * @param board
     *            the reference ChessGameBoard
     */
    public ChessGameEngine( ChessGameBoard board ){
        firstClick = true;
        currentPlayer = 1;
        this.board = board;
        this.king1 = (King)board.getCell( 7, 3 ).getPieceOnSquare();
        this.king2 = (King)board.getCell( 0, 3 ).getPieceOnSquare();
        ( (ChessPanel)board.getParent() ).getGameLog().clearLog();
        ( (ChessPanel)board.getParent() ).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!" );
    }
    // ----------------------------------------------------------
    /**
     * Resets the game to its original state.
     */
    public void reset(){
        firstClick = true;
        currentPlayer = 1;
        ( (ChessPanel)board.getParent() ).getGraveyard( 1 ).clearGraveyard();
        ( (ChessPanel)board.getParent() ).getGraveyard( 2 ).clearGraveyard();
        ( (ChessPanel)board.getParent() ).getGameBoard().initializeBoard();
        ( (ChessPanel)board.getParent() ).revalidate();
        this.king1 = (King)board.getCell( 7, 3 ).getPieceOnSquare();
        this.king2 = (King)board.getCell( 0, 3 ).getPieceOnSquare();
        ( (ChessPanel)board.getParent() ).getGameLog().clearLog();
        ( (ChessPanel)board.getParent() ).getGameLog().addToLog(
                "A new chess "
                        + "game has been started. Player 1 (white) will play "
                        + "against Player 2 (black). BEGIN!" );
    }
    /**
     * Switches the turn to be the next player's turn.
     */
    private void nextTurn(){
        currentPlayer = ( currentPlayer == 1 ) ? 2 : 1;
        ( (ChessPanel)board.getParent() ).getGameLog().addToLog(
                "It is now Player " + currentPlayer + "'s turn." );
    }
    // ----------------------------------------------------------
    /**
     * Gets the current player. Used for determining the turn.
     *
     * @return int the current player (1 or 2)
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * Determines if the requested player has legal moves.
     *
     * @param playerNum
     *            the player to check
     * @return boolean true if the player does have legal moves, false otherwise
     */
    public boolean playerHasLegalMoves( int playerNum ){
        ArrayList<ChessGamePiece> pieces;
        if ( playerNum == 1 ){
            pieces = board.getAllWhitePieces();
        }
        else if ( playerNum == 2 ){
            pieces = board.getAllBlackPieces();
        }
        else
        {
            return false;
        }
        for ( ChessGamePiece currPiece : pieces ){
            if ( currPiece.hasLegalMoves( board ) ){
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the last-clicked piece is a valid piece (i.e. if it is
     * the correct color and if the user actually clicked ON a piece.)
     * @return boolean true if the piece is valid, false otherwise
     */
    private boolean selectedPieceIsValid(){
        if ( currentPiece == null ) // user tried to select an empty square
        {
            return false;
        }
        if ( currentPlayer == 2 ) // black player
        {
            if ( currentPiece.getColorOfPiece() == ChessGamePiece.BLACK ){
                return true;
            }
            return false;
        }
        else
        // white player
        {
            if ( currentPiece.getColorOfPiece() == ChessGamePiece.WHITE ){
                return true;
            }
            return false;
        }
    }
    /**
     * Determines if the requested King is in check.
     *
     * @param checkCurrent
     *            if true, will check if the current king is in check if false,
     *            will check if the other player's king is in check.
     * @return true if the king is in check, false otherwise
     */
    public boolean isKingInCheck( boolean checkCurrent ){
        if ( checkCurrent ){
            if ( currentPlayer == 1 ){
                return king1.isChecked( board );
            }
            return king2.isChecked( board );
        }
        else
        {
            if ( currentPlayer == 2 ){
                return king1.isChecked( board );
            }
            return king2.isChecked( board );
        }
    }
    /**
     * Asks the user if they want to play again - if they don't, the game exits.
     *
     * @param endGameStr
     *            the string to display to the user (i.e. stalemate, checkmate,
     *            etc)
     */
    private void askUserToPlayAgain( String endGameStr ){
        int resp =
                JOptionPane.showConfirmDialog( board.getParent(), endGameStr
                        + " Do you want to play again?" );
        if ( resp == JOptionPane.YES_OPTION ){
            reset();
        }
        else
        {
            board.resetBoard( false );
            // System.exit(0);
        }
    }
    /**
     * Determines if the game should continue (i.e. game is in check or is
     * 'normal'). If it should not, the user is asked to play again (see above
     * method).
     */
    private void checkGameConditions(){
        int origPlayer = currentPlayer;
        for ( int i = 0; i < 2; i++ ){
            int gameLostRetVal = determineGameLost();
            if ( gameLostRetVal < 0 ){ //draw
                askUserToPlayAgain( "Game over - STALEMATE. You should both go"
                        + " cry in a corner!" );
                return;
            }
            else if ( gameLostRetVal > 0 ){
                askUserToPlayAgain( "Game over - CHECKMATE. " + "Player "
                        + gameLostRetVal + " loses and should go"
                        + " cry in a corner!" );
                return;
            }
            else if ( isKingInCheck( true ) ){
                Message message = new KingCheckedMessage(board,currentPlayer);
                message.showMessage();
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;
            // check the next player's conditions as well.
        }
        currentPlayer = origPlayer;
        nextTurn();
    }
    /**
     * Determines if the game is lost. Returns 1 or 2 for the losing player, -1
     * for stalemate, or 0 for a still valid game.
     *
     * @return int 1 or 2 for the losing play, -1 for stalemate, or 0 for a
     *         still valid game.
     */
    public int determineGameLost(){
        if ( king1.isChecked( board ) && !playerHasLegalMoves( 1 ) ) // player 1
        // loss
        {
            return 1;
        }
        if ( king2.isChecked( board ) && !playerHasLegalMoves( 2 ) ) // player 2
        // loss
        {
            return 2;
        }
        if ( ( !king1.isChecked( board ) && !playerHasLegalMoves( 1 ) )
                || ( !king2.isChecked( board ) && !playerHasLegalMoves( 2 ) )
                || ( board.getAllWhitePieces().size() == 1 &&
                board.getAllBlackPieces().size() == 1 ) ) // stalemate
        {
            return -1;
        }
        return 0; // game is still in play
    }
    // ----------------------------------------------------------
    /**
     * Given a MouseEvent from a user clicking on a square, the appropriate
     * action is determined. Actions include: moving a piece, showing the possi
     * ble moves of a piece, or ending the game after checking game conditions.
     *
     * @param e
     *            the mouse event from the listener
     */
    public void determineActionFromSquareClick( MouseEvent e ){
        BoardSquare squareClicked = (BoardSquare)e.getSource();
        ChessGamePiece pieceOnSquare = squareClicked.getPieceOnSquare();
        board.clearColorsOnBoard();
        if ( firstClick ){
            currentPiece = pieceOnSquare;
            if ( selectedPieceIsValid() ){
                currentPiece.showLegalMoves( board );
                squareClicked.setBackground( Color.GREEN );
                firstClick = false;
            }
            else
            {
                if ( currentPiece != null ){ //choose rival's chess
                    Message message = new ChooseRivalChessMessage(squareClicked);
                    message.showMessage();
                }
                else //choose an empty board
                {
                    Message message = new ChooseEmptyBoard(squareClicked);
                    message.showMessage();
                }
            }
        }
        else //second click
        {
            if ( pieceOnSquare == null ||
                    !pieceOnSquare.equals( currentPiece ) ) // moving
            {
                boolean moveSuccessful =
                        currentPiece.move(
                                board,
                                squareClicked.getRow(),
                                squareClicked.getColumn() );
                if ( moveSuccessful ){ //can move, check if King's checked
                    checkGameConditions();
                }
                else
                {
                    InvalidMessage message = new InvalidMessage(squareClicked);
                    message.showMessage();
                }
                firstClick = true;
            }
            else
            // user is just unselecting the current piece
            {
                firstClick = true;
            }
        }
    }
}
