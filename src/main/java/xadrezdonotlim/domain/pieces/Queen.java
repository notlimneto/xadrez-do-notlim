package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;

@Getter
public class Queen implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Queen(char color) {
        this.color = color;
        this.pieceCode = 'D';
    }

    public boolean isMovePossible(Board board, String position, String move) {
        return false;
    }

    public String[] getPossibleMoves(String position, Board board) {
        return null;
    }
}
