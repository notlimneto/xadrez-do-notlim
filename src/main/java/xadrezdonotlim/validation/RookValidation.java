package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

public class RookValidation {

    public static boolean moveValidation(Board board, String currentPosition, String nextPosition, char color) {
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);
        Integer indexOfNextColumn = columns.indexOf(nextColumn);

        boolean isMoveDiagonal = !indexOfCurrentColumn.equals(indexOfNextColumn) && !currentRow.equals(nextRow);

        if(isMoveDiagonal) return false;

        boolean hasSameColorPieceOnNextSquare = positionMap.get(nextPosition) != null && positionMap.get(nextPosition).getColor() == color;

        if (hasSameColorPieceOnNextSquare) return false;

        if (currentColumn.equals(nextColumn)) {
            if(currentRow.compareTo(nextRow) > 0) {
                for (int i = currentRow - 1; i > nextRow; i--) {
                    if (positionMap.get(currentColumn + i) != null) return false;
                }
            } else {
                for (int i = currentRow + 1; i < nextRow; i++) {
                    if (positionMap.get(currentColumn + i) != null) return false;
                }
            }
        } else {
            if(indexOfCurrentColumn.compareTo(indexOfNextColumn) > 0) {
                for (int i = indexOfCurrentColumn - 1; i > indexOfNextColumn; i--) {
                    if (positionMap.get(String.valueOf(columns.charAt(i)) + currentRow) != null) return false;
                }
            } else {
                for (int i = indexOfCurrentColumn + 1; i < indexOfNextColumn; i++) {
                    if (positionMap.get(String.valueOf(columns.charAt(i)) + currentRow) != null) return false;
                }
            }
        }

        return true;
    }
}
