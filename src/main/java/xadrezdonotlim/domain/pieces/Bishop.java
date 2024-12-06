package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.MoveUtil;
import xadrezdonotlim.validation.BishopValidation;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bishop implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Bishop(char color) {
        this.color = color;
        this.pieceCode = 'B';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return BishopValidation.moveValidation(board, currentPosition, nextPosition, color);
    }

    public List<String> getPossibleMoves(Board board, String currentPosition) {
        List<String> possibleMoves = new ArrayList<>();

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        for (char column : columns.toCharArray()) {
            for (char row : rows.toCharArray()) {
                String position = String.valueOf(column) + row;

                if (BishopValidation.moveValidation(board, currentPosition, position, color)) {
                    possibleMoves.add(position);
                }
            }
        }

        return possibleMoves;
    }

    public void makePieceAdjustmentsOnMove(){

    }

    private List<String> getPossibleCaptures(String currentPosition) {
        return MoveUtil.getDiagnoalMoves(currentPosition);
    }


    public void updateWatchedSquares(Board board, String currentPosition) {
        var positionMap = board.getBoard();
        List<String> possibleCaptures = getPossibleCaptures(currentPosition);

        for (String captureSquare : possibleCaptures) {
            if (color == ColorEnum.WHITE.getCode()) positionMap.get(captureSquare).addWhiteObserver();
            else positionMap.get(captureSquare).addBlackObserver();
        }
    }
}
