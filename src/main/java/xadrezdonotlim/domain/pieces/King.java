package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.validation.KingValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class King implements PieceInterface, Cloneable {
    private final char color;
    private final char pieceCode;
    private boolean hasMoved;

    @Setter
    private String square;

    public King(char color, String square) {
        this.color = color;
        this.pieceCode = 'R';
        this.square = square;
        this.hasMoved = false;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KingValidation.moveValidation(board, currentPosition, nextPosition, hasMoved, color);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!KingValidation.moveValidation(board, currentPosition, nextPosition, hasMoved, color)) return false;

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

        List<String> possibleMoves = new ArrayList<>();


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

    public static boolean isMoveCastles(String currentPosition, String nextPosition, char color) {
        if (color == ColorEnum.WHITE.getValue()) {
            return currentPosition.equals("e1") && (nextPosition.equals("g1") || nextPosition.equals("c1"));
        } else {
            return currentPosition.equals("e8") && (nextPosition.equals("g8") || nextPosition.equals("c8"));

        }
    }

    @Override
    public void makeMove(Board board, String currentPosition, String nextPosition) {
        var positionMap = board.getBoard();
        PieceInterface pieceMoved = positionMap.get(currentPosition);

        positionMap.put(currentPosition, null);
        updatePieceMaps(board, pieceMoved, currentPosition, nextPosition);
        positionMap.put(nextPosition, pieceMoved);

        this.hasMoved = true;

        if (isMoveCastles(currentPosition, nextPosition, color)) {
            String castlesRookCurrentSquare = (color == ColorEnum.WHITE.getValue()) ?
                    (nextPosition.equals("c1") ? "a1" : "h1") :
                    (nextPosition.equals("c8") ? "a8" : "h8");
            String castlesRookNextSquare = (color == ColorEnum.WHITE.getValue()) ?
                    (nextPosition.equals("c1") ? "d1" : "f1") :
                    (nextPosition.equals("c8") ? "d8" : "f8");
            var castlesRook = positionMap.get(castlesRookCurrentSquare);

            castlesRook.makeMove(board, castlesRookCurrentSquare, castlesRookNextSquare);
        }

        pieceMoved.setSquare(nextPosition);
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
