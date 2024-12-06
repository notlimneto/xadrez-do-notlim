package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.validation.KingValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class King implements PieceInterface {
    private final char color;
    private final char pieceCode;
    private boolean isChecked;

    public King(char color) {
        this.color = color;
        this.pieceCode = 'R';
        this.isChecked = false;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return KingValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(Board board, String currentPosition) {
        List<String> possibleMoves = new ArrayList<>();

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        for (char column : columns.toCharArray()) {
            for (char row : rows.toCharArray()) {
                String position = String.valueOf(column) + row;

                if (KingValidation.moveValidation(board, currentPosition, position, color)) {
                    possibleMoves.add(position);
                }
            }
        }

        return possibleMoves;
    }

    public void makePieceAdjustmentsOnMove(){

    }

    public void updateWatchedSquares(Board board, String currentPosition) {

    }

    public void setKingChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
