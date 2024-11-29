package xadrezdonotlim.domain;

import lombok.Getter;
import xadrezdonotlim.domain.pieces.*;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.view.BoardView;

import java.util.HashMap;

@Getter
public class Board {

    private final HashMap<String, Square> board = new HashMap<>();

    public Board() {
        montaTabuleiro();
        BoardView.updateBoard(this);
    }

    private void montaTabuleiro() {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();
        for(int row = 0; row < rows.length(); row++) {
            for (int column = 0; column < columns.length(); column++) {
                if (rows.charAt(row) == '1' || rows.charAt(row) == '8') {
                    char pieceColor = (rows.charAt(row) == '1') ? ColorEnum.WHITE.getCode() : ColorEnum.BLACK.getCode();

                    if (columns.charAt(column) == 'd') {
                        board.put(
                                String.valueOf(columns.charAt(column)) + rows.charAt(row),
                                new Square(new Queen(pieceColor))
                        );
                    } else if (columns.charAt(column) == 'e') {
                        board.put(
                                String.valueOf(columns.charAt(column)) + rows.charAt(row),
                                new Square(new King(pieceColor))
                        );
                    } else if (columns.charAt(column) == 'a' || columns.charAt(column) == 'h') {
                        board.put(
                                String.valueOf(columns.charAt(column)) + rows.charAt(row),
                                new Square(new Rook(pieceColor))
                        );
                    } else if (columns.charAt(column) == 'b' || columns.charAt(column) == 'g') {
                        board.put(
                                String.valueOf(columns.charAt(column)) + rows.charAt(row),
                                new Square(new Knight(pieceColor))
                        );
                    } else {
                        board.put(
                                String.valueOf(columns.charAt(column)) + rows.charAt(row),
                                new Square(new Bishop(pieceColor))
                        );
                    }
                } else if (rows.charAt(row) == '2') {
                    board.put(
                            String.valueOf(columns.charAt(column)) + rows.charAt(row),
                            new Square(new Pawn(ColorEnum.WHITE.getCode()))
                    );
                } else if (rows.charAt(row) == '7') {
                    board.put(
                            String.valueOf(columns.charAt(column)) + rows.charAt(row),
                            new Square(new Pawn(ColorEnum.BLACK.getCode()))
                    );
                } else {
                    board.put(
                            String.valueOf(columns.charAt(column)) + rows.charAt(row),
                            new Square(null)
                    );
                }
            }
        }
    }

    private boolean isCheckmate() {
        return false;
    }
}
