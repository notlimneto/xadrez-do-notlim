package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.validation.KingValidation;

import java.util.List;

@Getter
public class King implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public King(char color) {
        this.color = color;
        this.pieceCode = 'R';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KingValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        return null;
    }

    public void makePieceAdjustmentsOnMove(){

    }
}
