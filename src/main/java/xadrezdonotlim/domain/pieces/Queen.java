package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.validation.QueenValidation;

import java.util.List;

@Getter
public class Queen implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Queen(char color) {
        this.color = color;
        this.pieceCode = 'D';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return QueenValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        return null;
    }

    public void makePieceAdjustmentsOnMove(){

    }
}
