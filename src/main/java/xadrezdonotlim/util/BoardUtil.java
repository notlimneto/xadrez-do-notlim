package xadrezdonotlim.util;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;

import java.util.ArrayList;
import java.util.List;

public class BoardUtil {
    public static PieceInterface[] getRowPieces(int row, Board board) {
        char[] columnsArray  = PositionIdentifiersEnum.COLUMNS.getValues().toCharArray();
        PieceInterface[] rowPieces = new PieceInterface[8];

        for (int i = 0; i < 8; i++){
            rowPieces[i] = board.getBoard().get(String.valueOf(columnsArray[i]) + row).getPiece();
        }

        return rowPieces;
    }
}