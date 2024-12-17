package xadrezdonotlim.domain;

import lombok.Getter;
import xadrezdonotlim.domain.pieces.*;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.view.BoardView;

import java.util.HashMap;

@Getter
public class Board {

    private final HashMap<String, PieceInterface> board = new HashMap<>();
    private final HashMap<String, PieceInterface> whitePieces = new HashMap<>();
    private final HashMap<String, PieceInterface> blackPieces = new HashMap<>();

    private King whiteKing;
    private King blackKing;

    public Board() {
        montaTabuleiro();
        BoardView.updateBoard(this);
    }

    private void montaTabuleiro() {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        for(char row : rows.toCharArray()) {
            for (char column : columns.toCharArray()) {
                if (row == '1' || row == '8') {
                    char pieceColor = (row == '1') ? ColorEnum.WHITE.getValue() : ColorEnum.BLACK.getValue();
                    PieceInterface piece = getPiece(row, column, pieceColor);

                    board.put(
                            String.valueOf(column) + row,
                            piece
                    );

                    if (pieceColor == ColorEnum.WHITE.getValue()) whitePieces.put(String.valueOf(column) + row, piece);
                    else blackPieces.put(String.valueOf(column) + row, piece);
                } else if (row == '2' || row == '7') {
                    char pieceColor = (row == '2') ? ColorEnum.WHITE.getValue() : ColorEnum.BLACK.getValue();
                    var pawn = new Pawn(pieceColor, String.valueOf(column) + row);

                    board.put(
                            String.valueOf(column) + row,
                            pawn
                    );

                    if (pieceColor == ColorEnum.WHITE.getValue()) whitePieces.put(String.valueOf(column) + row, pawn);
                    else blackPieces.put(String.valueOf(column) + row, pawn);
                } else {
                    board.put(
                            String.valueOf(column) + row,
                            null
                    );
                }
            }
        }
    }

    private PieceInterface getPiece(char row, char column, char pieceColor) {
        PieceInterface piece;

        if (column == 'd') piece = new Queen(pieceColor, String.valueOf(column) + row);
        else if (column == 'e') {
            piece = new King(pieceColor, String.valueOf(column) + row);
            if (pieceColor == ColorEnum.WHITE.getValue()) this.whiteKing = (King) piece;
            else this.blackKing = (King) piece;
        } else if (column == 'a' || column == 'h') piece = new Rook(pieceColor, String.valueOf(column) + row);
        else if (column == 'b' || column == 'g') piece = new Knight(pieceColor, String.valueOf(column) + row);
        else piece = new Bishop(pieceColor, String.valueOf(column) + row);
        return piece;
    }
}
