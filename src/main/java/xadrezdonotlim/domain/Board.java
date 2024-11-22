package xadrezdonotlim.domain;

import xadrezdonotlim.domain.pieces.Pawn;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;

import java.util.HashMap;

public class Board {

    private static final HashMap<String, PieceInterface> board = new HashMap<>();
    private HashMap<String, PieceInterface> previousBoard;

    public Board() {
        montaTabuleiro();
        previousBoard = null;
    }

    private void montaTabuleiro() {
        String columns = "abcdefgh";
        String lines = "12345678";
        for(int line = 0; line < lines.length(); line++) {
            for(int column = 0; column < columns.length(); column++) {
                if(lines.charAt(line) == '2') {
                    board.put(
                            String.valueOf(columns.charAt(column)) + lines.charAt(line),
                            new Pawn(ColorEnum.WHITE.getCode())
                    );
                } else if(lines.charAt(line) == '7') {
                    board.put(
                            String.valueOf(columns.charAt(column)) + lines.charAt(line),
                            new Pawn(ColorEnum.WHITE.getCode())
                    );
                } else {
                    board.put(
                            String.valueOf(columns.charAt(column)) + lines.charAt(line),
                            null
                    );
                }
            }
        }
    }

    public HashMap<String, PieceInterface> getBoard() {
        return board;
    }

//    public HashMap<String, PieceInterface> whiteMove(String currentPosition, String nextPosition) {
//
//    }
//
//    public HashMap<String, PieceInterface> blackMove(String currentPosition, String nextPosition) {
//
//    }
}
