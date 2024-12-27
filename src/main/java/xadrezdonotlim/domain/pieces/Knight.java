package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.validation.KnightValidation;

import java.util.ArrayList;


@Getter
public class Knight implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public Knight(char color, String square) {
        this.color = color;
        this.pieceCode = 'C';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KnightValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!KnightValidation.moveValidation(board, currentPosition, nextPosition, color)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
    }

    public boolean hasMove(Board board) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        Integer indexOfColumn = columns.indexOf(square.substring(0, 1));
        Integer indexOfRow = rows.indexOf(square.substring(1));

        boolean isColumnInLeftBoundByTwo = indexOfColumn - 2 >= 0;
        boolean isColumnInLeftBoundByOne = indexOfColumn - 1 >= 0;

        boolean isColumnInRightBoundByTwo = indexOfColumn + 2 < 8;
        boolean isColumnInRightBoundByOne = indexOfColumn + 1 < 8;

        boolean isRowInLeftBoundByTwo = indexOfRow - 2 >= 0;
        boolean isRowInLeftBoundByOne = indexOfRow - 1 >= 0;

        boolean isRowInRightBoundByTwo = indexOfRow + 2 < 8;
        boolean isRowInRightBoundByOne = indexOfRow + 1 < 8;

        ArrayList<String> possibleMoves = new ArrayList<>();

        if (isColumnInLeftBoundByTwo) {
            if(isRowInLeftBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 2)) + rows.charAt(indexOfRow - 1));
            if(isRowInRightBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 2)) + rows.charAt(indexOfRow + 1));
        }
        if (isColumnInRightBoundByTwo) {
            if(isRowInLeftBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 2)) + rows.charAt(indexOfRow - 1));
            if(isRowInRightBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 2)) + rows.charAt(indexOfRow + 1));
        }
        if (isRowInLeftBoundByTwo) {
            if(isColumnInLeftBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow - 2));
            if(isColumnInRightBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow - 2));
        }
        if (isRowInRightBoundByTwo) {
            if(isColumnInLeftBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow + 2));
            if(isColumnInRightBoundByOne) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow + 2));
        }

        for (String possibleMove : possibleMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, possibleMove)) return true;
        }
        return false;
    }

    @Override
    public Knight clone() {
        try {
            return (Knight) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
