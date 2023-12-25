import java.util.ArrayList;

public class CalculateMove {

    private ChessGamePiece piece;
    public CalculateMove(ChessGamePiece piece){
        this.piece = piece;
    }

    public ArrayList<String> move(
            ChessGameBoard board,
            int numMoves, Direction d ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for (int i = 1; i < 8 && count < numMoves; i++ ){
                if (!piece.isOnScreen( d.getRowMove(piece.getRow(),i), d.getColMove(piece.getColumn(), i))){
                    break;
                }
                if ( board.getCell(d.getRowMove(piece.getRow(),i),d.getColMove(piece.getColumn(), i)).getPieceOnSquare()
                        == null){
                    moves.add(d.getRowMove(piece.getRow(),i) + "," + d.getColMove(piece.getColumn(), i));
                    count++;
                } else if (piece.isEnemy( board, d.getRowMove(piece.getRow(),i), d.getColMove(piece.getColumn(), i))) {
                    moves.add(d.getRowMove(piece.getRow(),i) + "," + d.getColMove(piece.getColumn(), i));
                    count++;
                    break;
                }else {
                    break;
                }
            }
        }
        return moves;
    }
}
