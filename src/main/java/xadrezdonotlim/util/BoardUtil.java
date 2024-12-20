package xadrezdonotlim.util;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.Pawn;
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

    public static boolean isMoveCausingSelfCheck(Board board, String currentPosition, String nextPosition) {
        Board customBoard = board.getBoardCopy(board.getWhitePieces(), board.getBlackPieces());
        var pieceMoved = customBoard.getBoard().get(currentPosition);
        pieceMoved.makeMove(customBoard, currentPosition, nextPosition);

        if(pieceMoved.getColor() == ColorEnum.WHITE.getValue()) {
            return BoardUtil.isKingChecked(customBoard, customBoard.getWhiteKing().getSquare(), pieceMoved.getColor());
        } else {
            return BoardUtil.isKingChecked(customBoard, customBoard.getBlackKing().getSquare(), pieceMoved.getColor());
        }
    }

    public static void unsetEnPassant (Board board, char color) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String row = color == ColorEnum.WHITE.getValue() ? "5" : "4";
        var positionMap = board.getBoard();
        for (char column : columns.toCharArray()) {
            var piece = positionMap.get(column + row);
            if (piece != null && piece.getClass() == Pawn.class && piece.getColor() == color) ((Pawn) piece).setHasEnPassant(false);
        }
    }
}