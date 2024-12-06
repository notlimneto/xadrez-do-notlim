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
        for(char row : rows.toCharArray()) {
            for (char column : columns.toCharArray()) {
                if (row == '1' || row == '8') {
                    char pieceColor = (row == '1') ? ColorEnum.WHITE.getCode() : ColorEnum.BLACK.getCode();

                    if (column == 'd') {
                        board.put(
                                String.valueOf(column) + row,
                                new Square(new Queen(pieceColor))
                        );
                    } else if (column == 'e') {
                        board.put(
                                String.valueOf(column) + row,
                                new Square(new King(pieceColor))
                        );
                    } else if (column == 'a' || column == 'h') {
                        board.put(
                                String.valueOf(column) + row,
                                new Square(new Rook(pieceColor))
                        );
                    } else if (column == 'b' || column == 'g') {
                        board.put(
                                String.valueOf(column) + row,
                                new Square(new Knight(pieceColor))
                        );
                    } else {
                        board.put(
                                String.valueOf(column) + row,
                                new Square(new Bishop(pieceColor))
                        );
                    }
                } else if (row == '2') {
                    board.put(
                            String.valueOf(column) + row,
                            new Square(new Pawn(ColorEnum.WHITE.getCode()))
                    );
                } else if (row == '7') {
                    board.put(
                            String.valueOf(column) + row,
                            new Square(new Pawn(ColorEnum.BLACK.getCode()))
                    );
                } else {
                    board.put(
                            String.valueOf(column) + row,
                            new Square(null)
                    );
                }
            }
        }

        for(char row : rows.toCharArray()) {
            for (char column : columns.toCharArray()) {
                String currentSquare = String.valueOf(column) + row;
                System.out.println(currentSquare);
                if (board.get(currentSquare).getPiece() != null) {
                    board.get(currentSquare).getPiece().updateWatchedSquares(this, currentSquare);
                }
            }
        }
    }
}
