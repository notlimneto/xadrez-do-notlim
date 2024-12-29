package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.King;
import xadrezdonotlim.domain.pieces.Rook;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.util.MoveUtil;

public class KingValidation {
    public static boolean moveValidation(Board board, String currentPosition, String nextPosition, boolean hasMoved, char color) {
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);
        Integer indexOfNextColumn = columns.indexOf(nextColumn);

        if (King.isMoveCastles(currentPosition, nextPosition, color)) {
            if (hasMoved) return false;
            var castlesRook = (color == ColorEnum.WHITE.getValue()) ?
                    positionMap.get(nextPosition.equals("c1") ? "a1" : "h1") :
                    positionMap.get(nextPosition.equals("c8") ? "a8" : "h8");
            if (castlesRook == null || castlesRook.getClass() != Rook.class || castlesRook.getColor() != color) return false;
            if (((Rook) castlesRook).hasMoved()) return false;
            for (String square : MoveUtil.getSquaresBetweeenCastles(nextPosition, color)) {
                if (positionMap.get(square) != null) return false;
            }
            String[] squaresBetweenCurrentAndNextPosition = (color == ColorEnum.WHITE.getValue()) ?
                    (nextPosition.equals("c1") ? new String[]{"c1", "d1"} : new String[]{"f1", "g1"}) :
                    (nextPosition.equals("c8") ? new String[]{"c8", "d8"} : new String[]{"f8", "g8"});
            for (String square : squaresBetweenCurrentAndNextPosition) {
                if (BoardUtil.isKingChecked(board, square, color)) return false;
            }
            if (BoardUtil.isKingChecked(board, currentPosition, color)) return false;
        } else {
            boolean movementCondition = Math.abs(nextRow - currentRow) <= 1 && Math.abs(indexOfNextColumn - indexOfCurrentColumn) <= 1;
            if (!movementCondition) return false;

            boolean hasSameColorPieceOnNextSquare = positionMap.get(nextPosition) != null && positionMap.get(nextPosition).getColor() == color;
            if (hasSameColorPieceOnNextSquare) return false;
        }

        return true;
    }
}
