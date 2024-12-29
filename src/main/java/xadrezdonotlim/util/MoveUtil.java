package xadrezdonotlim.util;

import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

import java.util.ArrayList;
import java.util.List;

public class MoveUtil {
    public static List<String> getDiagnoalMoves(String currentPostion) {
        List<String> diagnoalMoves = new ArrayList<>();

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPostion.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPostion.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);

        Integer columnIterator = indexOfCurrentColumn - 1;

        while (columnIterator >= 0) {
            Integer rowAbove = currentRow + (indexOfCurrentColumn - columnIterator);
            Integer rowBelow = currentRow - (indexOfCurrentColumn - columnIterator);

            if (rowAbove > 0 && rowAbove <= 8) diagnoalMoves.add(String.valueOf(columns.toCharArray()[columnIterator]) + rowAbove);
            if (rowBelow > 0 && rowBelow <= 8) diagnoalMoves.add(String.valueOf(columns.toCharArray()[columnIterator]) + rowBelow);

            columnIterator--;
        }

        columnIterator = indexOfCurrentColumn + 1;

        while (columnIterator < 8) {
            Integer rowAbove = currentRow + (columnIterator - indexOfCurrentColumn);
            Integer rowBelow = currentRow - (columnIterator - indexOfCurrentColumn);

            if (rowAbove > 0 && rowAbove <= 8) diagnoalMoves.add(String.valueOf(columns.toCharArray()[columnIterator]) + rowAbove);
            if (rowBelow > 0 && rowBelow <= 8) diagnoalMoves.add(String.valueOf(columns.toCharArray()[columnIterator]) + rowBelow);
            columnIterator++;
        }

        return diagnoalMoves;
    }

    public static List<String> getLinearMoves(String currentPostion) {
        List<String> linearMoves = new ArrayList<>();

        char[] columns = PositionIdentifiersEnum.COLUMNS.getValues().toCharArray();
        char[] rows = PositionIdentifiersEnum.ROWS.getValues().toCharArray();

        String currentColumn = currentPostion.substring(0, 1);
        String currentRow = currentPostion.substring(1);

        for (int i = 0; i < 8; i++) {
            String columnFixedSquare = currentColumn + rows[i];
            String rowFixedSquare = columns[i] + currentRow;

            if (!columnFixedSquare.equals(currentPostion)) linearMoves.add(columnFixedSquare);
            if (!rowFixedSquare.equals(currentPostion)) linearMoves.add(rowFixedSquare);
        }

        return linearMoves;
    }

    public static String[] getSquaresBetweeenCastles(String nextPosition, char color) {
        if (color == ColorEnum.WHITE.getValue()) {
            return (nextPosition.equals("c1")) ? new String[]{"b1", "c1", "d1"} : new String[]{"f1", "g1"};
        } else {
            return (nextPosition.equals("c8")) ? new String[]{"b8", "c8", "d8"} : new String[]{"f8", "g8"};
        }
    }
}
