package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.util.MoveUtil;
import xadrezdonotlim.validation.KingValidation;
import xadrezdonotlim.validation.QueenValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Queen implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public Queen(char color, String square) {
        this.color = color;
        this.pieceCode = 'D';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return QueenValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!QueenValidation.moveValidation(board, currentPosition, nextPosition, color)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
    }

    public boolean hasMove(Board board) {
        List<String> linearMoves = MoveUtil.getLinearMoves(square);
        List<String> diagonalMoves = MoveUtil.getDiagnoalMoves(square);

        for (String move : linearMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, move)) return true;
        }

        for (String move : diagonalMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, move)) return true;
        }

        return false;
    }

    @Override
    public Queen clone() {
        try {
            return (Queen) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
