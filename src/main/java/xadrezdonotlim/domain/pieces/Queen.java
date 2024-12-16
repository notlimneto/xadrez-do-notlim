package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.validation.QueenValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Queen implements PieceInterface {
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    public Queen(char color, String square) {
        this.color = color;
        this.pieceCode = 'D';
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return QueenValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(Board board, String currentPosition) {
        List<String> possibleMoves = new ArrayList<>();

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        for (char column : columns.toCharArray()) {
            for (char row : rows.toCharArray()) {
                String position = String.valueOf(column) + row;

                if (QueenValidation.moveValidation(board, currentPosition, position, color)) {
                    possibleMoves.add(position);
                }
            }
        }

        return possibleMoves;
    }
}
