package xadrezdonotlim.validation;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.Pawn;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

import java.util.HashMap;

public class PawnValidation {

    public static boolean moveValidation(Board board, String currentPosition, String nextPosition,
                                         char color, boolean hasMoved, boolean hasEnPassant) {
        var positionMap = board.getBoard();
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexCurrentColumn = columns.indexOf(currentColumn);
        Integer indexNextColumn = columns.indexOf(nextColumn);

        if (indexNextColumn-indexCurrentColumn>1 || indexNextColumn-indexCurrentColumn<-1) return false;

        if (color== ColorEnum.WHITE.getValue()) {
            if(!whiteMoveValidation(currentColumn, currentRow, nextColumn,
                    nextRow, positionMap, indexCurrentColumn, indexNextColumn, hasMoved)) return false;
        } else {
            if(!blackMoveValidation(currentColumn, currentRow, nextColumn,
                    nextRow, positionMap, indexCurrentColumn, indexNextColumn, hasMoved)) return false;
        }

        if (positionMap.get(nextPosition) != null) {
            if (positionMap.get(nextPosition).getColor() == color) {
                return false;
            } else {
                if(currentColumn.equals(nextColumn)) return false;
                else return true;
            }
        } else if (indexCurrentColumn.compareTo(indexNextColumn)!=0) {
            if (!hasEnPassant) return false;
            else {
                Pawn pieceOnCurrentPosition = (Pawn) positionMap.get(currentPosition);
                return nextColumn.equals(pieceOnCurrentPosition.getEnPassantColumn());
            }
        } else {
            return true;
        }
    }

    public static boolean whiteMoveValidation(String currentColumn, Integer currentRow, String nextColumn,
                                              Integer nextRow, HashMap<String, PieceInterface> positionMap,
                                              Integer indexCurrentColumn, Integer indexNextColumn,
                                              boolean hasMoved) {

        if (nextRow.compareTo(currentRow)<=0) return false;

        if (nextRow-currentRow>2) return false;

        if (nextRow-currentRow==2 && hasMoved) return false;

        if (nextRow-currentRow==2 && positionMap.get(currentColumn + (currentRow+1))!=null) return false;

        if (nextRow-currentRow>1 && indexCurrentColumn.compareTo(indexNextColumn)!=0) return false;

        return true;
    }

    public static boolean blackMoveValidation(String currentColumn, Integer currentRow, String nextColumn,
                                              Integer nextRow, HashMap<String, PieceInterface> positionMap,
                                              Integer indexCurrentColumn, Integer indexNextColumn,
                                              boolean hasMoved) {

        if (currentRow.compareTo(nextRow)<=0) return false;

        if (currentRow-nextRow>2) return false;

        if (currentRow-nextRow==2 && hasMoved) return false;

        if (currentRow-nextRow==2 && positionMap.get(currentColumn + (currentRow-1))!=null) return false;

        if (currentRow-nextRow>1 && indexCurrentColumn.compareTo(indexNextColumn)!=0) return false;

        return true;
    }
}
