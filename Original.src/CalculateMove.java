import java.util.ArrayList;

public class CalculateMove {

    private ChessGamePiece piece;
    public CalculateMove(ChessGamePiece piece){
        this.piece = piece;
    }

    public ArrayList<String> move(
            ChessGameBoard board,
            int numMoves, String direction ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for (int i = 1; i < 8 && count < numMoves; i++ ){
                if (piece.isOnScreen( rowDirection(piece.getRow(),i,direction), colDirection(piece.getColumn(), i,direction ))){
                    if ( board.getCell(  rowDirection(piece.getRow(),i,direction),colDirection(piece.getColumn(), i,direction )).getPieceOnSquare()
                            == null){
                        moves.add(rowDirection(piece.getRow(),i,direction) + "," + colDirection(piece.getColumn(), i,direction ));
                        count++;
                    } else if (piece.isEnemy( board, rowDirection(piece.getRow(),i,direction), colDirection(piece.getColumn(), i,direction ))) {
                        moves.add(rowDirection(piece.getRow(),i,direction) + "," + colDirection(piece.getColumn(), i,direction ));
                        count++;
                        break;
                    }else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
        return moves;
    }


    public int rowDirection(int position,int i, String direction){
        switch (direction){
            case "southEast", "southWest", "south":
                return position + i;
            case "northEast", "northWest", "north":
                return position - i;
            case "west", "east":
                return position;
        }
        return 0;
    }

    public int colDirection(int position,int i, String direction){
        switch (direction){
            case "southEast","northEast","east" :
                return position + i;
            case "southWest", "northWest","west" :
                return position - i;
            case "north", "south":
                return position;
        }
        return 0;
    }

}
