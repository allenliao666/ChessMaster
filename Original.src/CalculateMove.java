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

    public ArrayList<String> northWest(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( piece.isOnScreen( piece.getRow() - i, piece.getColumn() - i )
                        && ( board.getCell( piece.getRow() - i,
                        piece.getColumn() - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( piece.getRow() - i ) + "," + ( piece.getColumn() - i ) );
                    count++;
                }
                else if ( piece.isEnemy( board, piece.getRow() - i, piece.getColumn() - i ) ){
                    moves.add( ( piece.getRow() - i ) + "," + ( piece.getColumn() - i ) );
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


    public ArrayList<String> northEast(
            ChessGameBoard board,
            int numMoves ) {
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if (piece.isPieceOnScreen()) {
            for (int i = 1; i < 8 && count < numMoves; i++) {
                if (piece.isOnScreen(piece.getRow() - i, piece.getColumn() + i)
                        && (board.getCell(piece.getRow() - i,
                        piece.getColumn() + i).getPieceOnSquare() == null)) {
                    moves.add((piece.getRow() - i) + "," + (piece.getColumn() + i));
                    count++;
                } else if (piece.isEnemy(board, piece.getRow() - i, piece.getColumn() + i)) {
                    moves.add((piece.getRow() - i) + "," + (piece.getColumn() + i));
                    count++;
                    break;
                } else {
                    break;
                }
            }
        }
        return moves;
    }

    public ArrayList<String> southWest(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( piece.isOnScreen( piece.getRow() + i, piece.getColumn() - i )
                        && ( board.getCell( piece.getRow() + i,
                        piece.getColumn() - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( piece.getRow() + i ) + "," + ( piece.getColumn() - i ) );
                    count++;
                }
                else if ( piece.isEnemy( board, piece.getRow() + i, piece.getColumn() - i ) ){
                    moves.add( ( piece.getRow() + i ) + "," + ( piece.getColumn() - i ) );
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

    public ArrayList<String> southEast(
            ChessGameBoard board,
            int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( piece.isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( piece.isOnScreen( piece.getRow() + i, piece.getColumn() + i )
                        && ( board.getCell( piece.getRow() + i,
                        piece.getColumn() + i ).getPieceOnSquare() == null ) ){
                    moves.add( ( piece.getRow() + i ) + "," + ( piece.getColumn() + i ) );
                    count++;
                }
                else if ( piece.isEnemy( board, piece.getRow() + i, piece.getColumn() + i ) ){
                    moves.add( ( piece.getRow() + i ) + "," + ( piece.getColumn() + i ) );
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

}
