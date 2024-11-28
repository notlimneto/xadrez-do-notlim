package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.validation.BishopValidation;

import java.util.List;

@Getter
public class Bishop implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Bishop(char color) {
        this.color = color;
        this.pieceCode = 'B';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return BishopValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        return null;
    }

    public void makePieceAdjustmentsOnMove(){

    }
}
