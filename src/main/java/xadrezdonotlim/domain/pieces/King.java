package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.validation.KingValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class King implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public King(char color, String square) {
        this.color = color;
        this.pieceCode = 'R';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KingValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!KingValidation.moveValidation(board, currentPosition, nextPosition, color)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
    }

    public boolean hasMove(Board board) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        Integer indexOfColumn = columns.indexOf(square.substring(0, 1));
        Integer indexOfRow = rows.indexOf(square.substring(1));

        boolean isColumnInLeftBound = indexOfColumn - 1 >= 0;
        boolean isColumnInRightBound = indexOfColumn + 1 < 8;

        boolean isRowInLeftBound = indexOfRow - 1 >= 0;
        boolean isRowInRightBound = indexOfRow + 1 < 8;

        ArrayList<String> possibleMoves = new ArrayList<>();


        if (isColumnInLeftBound) {
            if(isRowInLeftBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow - 1));
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow));
            if(isRowInRightBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow + 1));
        }
        if (isColumnInRightBound) {
            if(isRowInLeftBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow - 1));
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow));
            if(isRowInRightBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow + 1));
        }
        if (isRowInLeftBound) {
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn)) + rows.charAt(indexOfRow - 1));
        }
        if (isRowInRightBound) {
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn)) + rows.charAt(indexOfRow + 1));
        }

        for (String possibleMove : possibleMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, possibleMove)) return true;
        }
        return false;
    }

    @Override
    public King clone() {
        try {
            return (King) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
