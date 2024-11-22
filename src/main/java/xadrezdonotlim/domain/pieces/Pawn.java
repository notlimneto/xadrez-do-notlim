package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;

@Getter
public class Pawn implements PieceInterface{
    private final char color;
    private final char pieceCode;

    public Pawn(char color) {
        this.color = color;
        this.pieceCode = 'P';
    }

    public String[] getPossibleMoves(String position, Board board) {
        return null;
    }
}
