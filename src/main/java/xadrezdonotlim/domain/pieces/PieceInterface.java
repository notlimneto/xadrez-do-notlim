package xadrezdonotlim.domain.pieces;

import xadrezdonotlim.domain.Board;

public interface PieceInterface {

    boolean isMovePossible(Board board, String position, String move);

    String[] getPossibleMoves(String position, Board board);

    char getColor();

    char getPieceCode();
}
