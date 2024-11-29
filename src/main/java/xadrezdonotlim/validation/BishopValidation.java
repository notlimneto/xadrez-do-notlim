package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

public class BishopValidation {
    public static boolean moveValidation(Board board, String currentPosition, String nextPosition, char color){
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);
        Integer indexOfNextColumn = columns.indexOf(nextColumn);

        Integer rowDifference = currentRow - nextRow;

        Integer columnDifference = indexOfCurrentColumn - indexOfNextColumn;

        boolean isMoveDiagonal = Math.abs(rowDifference) == Math.abs(columnDifference);

        if (!isMoveDiagonal) return false;

        boolean hasSameColorPieceOnNextSquare = positionMap.get(nextPosition).getPiece() != null && positionMap.get(nextPosition).getPiece().getColor() == color;

        if (hasSameColorPieceOnNextSquare) return false;

        if (currentRow.compareTo(nextRow) > 0) {
            if (indexOfCurrentColumn.compareTo(indexOfNextColumn)>0){
                for (int i = currentRow - 1; i > nextRow; i--) {
                    if (positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn-(currentRow-i))) + i).getPiece() != null) return false;
                }
            } else {
                for (int i = currentRow - 1; i > nextRow; i--) {
                    if (positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn+(currentRow-i))) + i).getPiece() != null) return false;
                }
            }
        } else {
            if (indexOfCurrentColumn.compareTo(indexOfNextColumn)>0){
                for (int i = currentRow + 1; i < nextRow; i++) {
                    if (positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn-(i-currentRow))) + i).getPiece() != null) return false;
                }
            } else {
                for (int i = currentRow + 1; i < nextRow; i++) {
                    if (positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn+(i-currentRow))) + i).getPiece() != null) return false;
                }
            }
        }

        return true;
    }
}
