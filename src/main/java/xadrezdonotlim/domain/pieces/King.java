package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
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

    @Setter
    private String square;

    public King(char color, String square) {
        this.color = color;
        this.pieceCode = 'R';
        this.isChecked = false;
        this.square = square;
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

    public void setKingChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
