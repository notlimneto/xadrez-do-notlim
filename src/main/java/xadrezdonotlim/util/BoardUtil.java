package xadrezdonotlim.util;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

public class BoardUtil {
    public static PieceInterface[] getRowPieces(int row, Board board) {
        char[] columnsArray  = PositionIdentifiersEnum.COLUMNS.getValues().toCharArray();
        PieceInterface[] rowPieces = new PieceInterface[8];

        for (int i = 0; i < 8; i++){
            rowPieces[i] = board.getBoard().get(String.valueOf(columnsArray[i]) + row);
        }

        return rowPieces;
    }

    public static boolean isKingChecked(Board board, String kingPosition, char color) {
        if (color == ColorEnum.WHITE.getValue()) {
            for (PieceInterface piece : board.getBlackPieces().values()) {
                if (piece.isMovePossible(board, piece.getSquare(), kingPosition)) {
                    return true;
                }
            }
        } else {
            for (PieceInterface piece : board.getWhitePieces().values()) {
                if (piece.isMovePossible(board, piece.getSquare(), kingPosition)) {
                    return true;
                }
            }
        }
        return false;
    }
}