package xadrezdonotlim.domain.pieces;

import xadrezdonotlim.domain.Board;

import java.util.List;

public interface PieceInterface {

    boolean isMovePossible(Board board, String currentPosition, String nextPosition);

    List<String> getPossibleMoves(String currentPosition, Board board);

    void makePieceAdjustmentsOnMove();

    default void makeMove(Board board, String currentPosition, String nextPosition) {
        var positionMap = board.getBoard();
        PieceInterface pieceMoved = positionMap.get(currentPosition).getPiece();

        positionMap.get(currentPosition).setPiece(null);
        positionMap.get(nextPosition).setPiece(pieceMoved);
    }

    char getColor();

    char getPieceCode();
}
