package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

public class KnightValidation {

    public static boolean moveValidation(Board board, String currentPosition, String nextPosition, char color) {
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);
        Integer indexOfNextColumn = columns.indexOf(nextColumn);

        Integer columnDifference = Math.abs(indexOfCurrentColumn - indexOfNextColumn);
        Integer rowDifference = Math.abs(currentRow - nextRow);

        if (columnDifference > 2 || rowDifference > 2) return false;

        boolean columnMovementCondition = columnDifference == 2;
        boolean rowMovementCondition = rowDifference == 2 ;

        boolean notSquareEdgesCondition = columnMovementCondition ^ rowMovementCondition; //XOR

        boolean isSameColumnOrRow = currentRow.equals(nextRow) || currentColumn.equals(nextColumn);

        boolean movementCondition = !isSameColumnOrRow && notSquareEdgesCondition;

        if (!movementCondition) return false;

        if (positionMap.get(nextPosition) != null) {
            return !(positionMap.get(nextPosition).getColor() == color);
        } else return true;
    }
}
