package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

public class KingValidation {
    public static boolean moveValidation(Board board, String currentPosition, String nextPosition, char color) {
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);
        Integer indexOfNextColumn = columns.indexOf(nextColumn);

        boolean movementCondition = Math.abs(nextRow - currentRow) == 1 || Math.abs(indexOfNextColumn - indexOfCurrentColumn) == 1;

        if (!movementCondition) return false;

        boolean hasSameColorPieceOnNextSquare = positionMap.get(nextPosition).getPiece() != null && positionMap.get(nextPosition).getPiece().getColor() == color;

        if (hasSameColorPieceOnNextSquare) return false;

        return true;
    }
}
