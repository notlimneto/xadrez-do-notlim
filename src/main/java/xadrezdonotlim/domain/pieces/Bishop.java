package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.util.MoveUtil;
import xadrezdonotlim.validation.BishopValidation;

import java.util.List;


@Getter
public class Bishop implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public Bishop(char color, String square) {
        this.color = color;
        this.pieceCode = 'B';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return BishopValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!BishopValidation.moveValidation(board, currentPosition, nextPosition, color)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
    }

    public boolean hasMove(Board board) {
        List<String> possibleMoves = MoveUtil.getDiagnoalMoves(square);

        for (String move : possibleMoves) {
            if(isMovePossibleConsideringSelfCheck(board, square, move)) return true;
        }

        return false;
    }

    @Override
    public Bishop clone() {
        try {
            return (Bishop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
