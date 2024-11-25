package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;

@Getter
public class Knight implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Knight(char color) {
        this.color = color;
        this.pieceCode = 'C';
    }

    public boolean isMovePossible(Board board, String position, String move) {
        return false;
    }

    public String[] getPossibleMoves(String position, Board board) {
        return null;
    }
}
