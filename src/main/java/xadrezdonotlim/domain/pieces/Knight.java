package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.validation.KnightValidation;

import java.util.List;

@Getter
public class Knight implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Knight(char color) {
        this.color = color;
        this.pieceCode = 'C';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KnightValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        return null;
    }

    public void makePieceAdjustmentsOnMove(){

    }
}
