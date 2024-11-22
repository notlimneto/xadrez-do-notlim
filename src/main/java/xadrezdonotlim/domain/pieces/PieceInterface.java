package xadrezdonotlim.domain.pieces;

import xadrezdonotlim.domain.Board;

public interface PieceInterface {

    String[] getPossibleMoves(String position, Board board);

    char getColor();

    char getPieceCode();
}
