package xadrezdonotlim.domain.pieces;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.ColorEnum;

import java.util.List;

public interface PieceInterface {

    boolean isMovePossible(Board board, String currentPosition, String nextPosition);

    List<String> getPossibleMoves(Board board, String currentPosition);

    default void makeMove(Board board, String currentPosition, String nextPosition) {
        var positionMap = board.getBoard();
        PieceInterface pieceMoved = positionMap.get(currentPosition);

        positionMap.put(currentPosition, null);
        updatePieceMaps(board, pieceMoved, currentPosition, nextPosition);
        positionMap.put(nextPosition, pieceMoved);

        pieceMoved.setSquare(nextPosition);
    }

    default void updatePieceMaps(Board board, PieceInterface piece, String currentPosition, String nextPosition) {
        var positionMap = board.getBoard();

        if (piece.getColor() == ColorEnum.WHITE.getValue()) {
            board.getWhitePieces().remove(currentPosition);
            board.getWhitePieces().put(nextPosition, piece);

            if (positionMap.get(nextPosition) != null) board.getBlackPieces().remove(nextPosition);
        } else {
            board.getBlackPieces().remove(currentPosition);
            board.getBlackPieces().put(nextPosition, piece);

            if (positionMap.get(nextPosition) != null) board.getWhitePieces().remove(nextPosition);
        }
    }

    char getColor();

    char getPieceCode();

    String getSquare();

    void setSquare(String newSquare);
}
