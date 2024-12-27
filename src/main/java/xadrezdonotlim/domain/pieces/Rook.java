package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.util.MoveUtil;
import xadrezdonotlim.validation.KingValidation;
import xadrezdonotlim.validation.RookValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Rook implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public Rook(char color, String square) {
        this.color = color;
        this.pieceCode = 'T';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return RookValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!RookValidation.moveValidation(board, currentPosition, nextPosition, color)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
    }

    public boolean hasMove(Board board) {
        List<String> possibleMoves = MoveUtil.getLinearMoves(square);

        for (String move : possibleMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, move)) return true;
        }

        return false;
    }

    @Override
    public Rook clone() {
        try {
            return (Rook) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}