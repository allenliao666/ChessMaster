import java.util.ArrayList;

public class CalculateMove {

    private ChessGamePiece piece;
    public CalculateMove(ChessGamePiece piece){
        this.piece = piece;
    }

    public ArrayList<String> south(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = piece.getRow() + 1; i < 8 && count < numMoves; i++ ){
                if ( !( board.getCell( i, piece.getColumn() ).getPieceOnSquare()
                        == null) && !( piece.isEnemy( board, i, piece.getColumn()) ) ){break;}
                moves.add( i + "," + piece.getColumn() );
                count++;
                if ( piece.isEnemy( board, i, piece.getColumn() ) ){
                    break;
                }
            }
        }
        return moves;
    }

    public ArrayList<String> north(
            ChessGameBoard board,
           int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = piece.getRow() - 1; i >= 0 && count < numMoves; i-- ){
                if ( !( board.getCell( i, piece.getColumn() ).getPieceOnSquare()
                        == null) && !( piece.isEnemy( board, i, piece.getColumn()) ) ){break;}
                moves.add( i + "," + piece.getColumn() );
                count++;
                if ( piece.isEnemy( board, i, piece.getColumn() ) ){
                    break;
                }
            }
        }
        return moves;
    }

    public ArrayList<String> east(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for (int i = piece.getColumn() + 1; i < 8 && count < numMoves; i++ ){
                if ( !( board.getCell(  piece.getRow(), i).getPieceOnSquare()
                        == null) && !( piece.isEnemy( board, piece.getRow(), i) ) ){break;}
                moves.add(  piece.getRow()+ "," + i );
                count++;
                if ( piece.isEnemy( board, piece.getRow(),  i) ){
                    break;
                }
            }
        }
        return moves;
    }

    public ArrayList<String> west(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = piece.getColumn() - 1; i >= 0 && count < numMoves; i-- ){
                if ( !( board.getCell(  piece.getRow(), i).getPieceOnSquare()
                        == null) && !( piece.isEnemy( board, piece.getRow(), i) ) ){break;}
                moves.add(  piece.getRow()+ "," + i );
                count++;
                if ( piece.isEnemy( board, piece.getRow(),  i) ){
                    break;
                }
            }
        }
        return moves;
    }


    public ArrayList<String> move(
            ChessGameBoard board,
            int numMoves, String direction ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( piece.isOnScreen( rowDirection(piece.getRow(),i,direction)
                        , colDirection(piece.getColumn(), i,direction ))
                        && ( board.getCell( rowDirection(piece.getRow(),i,direction),
                        colDirection(piece.getColumn(), i,direction ) ).getPieceOnSquare() == null ) ){
                    moves.add( ( rowDirection(piece.getRow(),i,direction) ) + "," + ( colDirection(piece.getColumn(), i,direction ) ) );
                    count++;
                }
                else if ( piece.isEnemy( board, rowDirection(piece.getRow(),i,direction), colDirection(piece.getColumn(), i,direction ) ) ){
                    moves.add( ( rowDirection(piece.getRow(),i,direction) ) + "," + ( colDirection(piece.getColumn(), i,direction )) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }

    public int rowDirection(int position,int i, String direction){
        switch (direction){
            case "southEast", "southWest":
                return position + i;
            case "northEast", "northWest":
                return position - i;
        }
        return 0;
    }

    public int colDirection(int position,int i, String direction){
        switch (direction){
            case "southEast","northEast" :
                return position + i;
            case "southWest", "northWest":
                return position - i;
        }
        return 0;
    }

}
