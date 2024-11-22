package xadrezdonotlim.util;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.PieceInterface;

public class BoardUtil {
    public static PieceInterface[] getRowPieces(int row, Board board) {
        char[] columnsArray  = "abcdefgh".toCharArray();
        PieceInterface[] rowPieces = new PieceInterface[8];

        for (int i = 0; i < 8; i++){
            rowPieces[i] = board.getBoard().get(String.valueOf(columnsArray[i]) + row);
        }

        return rowPieces;
    }
}