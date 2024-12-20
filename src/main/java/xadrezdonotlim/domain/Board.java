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
        buildBoard();
        BoardView.updateBoard(this);
    }

    private Board(HashMap<String, PieceInterface> whitePieces, HashMap<String, PieceInterface> blackPieces) {
        buildCustomBoard(whitePieces, blackPieces);
    }

    public Board getBoardCopy(HashMap<String, PieceInterface> whitePieces, HashMap<String, PieceInterface> blackPieces) {
        return new Board(whitePieces, blackPieces);
    }

    private void buildBoard() {
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

    private void buildCustomBoard(HashMap<String, PieceInterface> whitePieces, HashMap<String, PieceInterface> blackPieces) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        for(char row : rows.toCharArray()) {
            for (char column : columns.toCharArray()) {
                String position = String.valueOf(column) + row;
                if (whitePieces.containsKey(position)) {
                    var pieceCopy = addPieceCopy(whitePieces, whitePieces.get(position).getPieceCode(), position);
                    this.whitePieces.put(String.valueOf(column) + row, pieceCopy);
                } else if (blackPieces.containsKey(position)) {
                    var pieceCopy = addPieceCopy(blackPieces, blackPieces.get(position).getPieceCode(), position);
                    this.blackPieces.put(String.valueOf(column) + row, pieceCopy);
                } else {
                    board.put(position, null);
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

    private PieceInterface addPieceCopy(HashMap<String, PieceInterface> pieces, char pieceCode, String position) {
        var piece = pieces.get(position);
        if (pieceCode == 'P') board.put(position, ((Pawn) piece).clone());
        else if (pieceCode == 'R') {
            board.put(position, ((King) piece).clone());
            if (piece.getColor() == ColorEnum.WHITE.getValue()) this.whiteKing = (King) piece;
            else this.blackKing = (King) piece;
        } else if (pieceCode == 'D') board.put(position, ((Queen) piece).clone());
        else if (pieceCode == 'T') board.put(position, ((Rook) piece).clone());
        else if (pieceCode == 'B') board.put(position, ((Bishop) piece).clone());
        else board.put(position, ((Knight) piece).clone());
        return piece;
    }
}
