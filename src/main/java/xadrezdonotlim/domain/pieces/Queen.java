package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.validation.QueenValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Queen implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Queen(char color) {
        this.color = color;
        this.pieceCode = 'D';
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

    public void updateWatchedSquares(Board board, String currentPosition) {

    }

    public void makePieceAdjustmentsOnMove(){

    }
}
