package xadrezdonotlim.view;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.util.BoardUtil;

public class BoardView {

    public static final String R = "\u001B[0m";
    public static final String BLACK_BACKGROUND_WHITE_TEXT = "\u001B[40m\u001B[97m";
    public static final String WHITE_BACKGROUND_BLACK_TEXT = "\u001B[107m\u001B[30m";
    private static final String W = WHITE_BACKGROUND_BLACK_TEXT + "       " + R;
    private static final String w = WHITE_BACKGROUND_BLACK_TEXT + " " + R;
    private static final String B = BLACK_BACKGROUND_WHITE_TEXT + "       " + R;
    private static final String b = BLACK_BACKGROUND_WHITE_TEXT + " " + R;


    private static final String[] terminalBoard = new String[25];

    //Com o maior respeito do mundo a quem pegou meu código pra ver do github mas aqui foi pro caralho tudo repara n
    public static void updateBoard(Board board) {
        try{
            terminalBoard[0] = b + W + B + W + B + W + B + W + B;
            terminalBoard[1] = buildViewLineWithPieces(board, 8);
            terminalBoard[2] = BLACK_BACKGROUND_WHITE_TEXT + "8" + R + W + B + W + B + W + B + W + B;
            terminalBoard[3] = w + B + W + B + W + B + W + B + W;
            terminalBoard[4] = buildViewLineWithPieces(board, 7);
            terminalBoard[5] = WHITE_BACKGROUND_BLACK_TEXT + "7" + R + B + W + B + W + B + W + B + W;
            terminalBoard[6] = b + W + B + W + B + W + B + W + B;
            terminalBoard[7] = buildViewLineWithPieces(board, 6);
            terminalBoard[8] = BLACK_BACKGROUND_WHITE_TEXT + "6" + R + W + B + W + B + W + B + W + B;
            terminalBoard[9] = w + B + W + B + W + B + W + B + W;
            terminalBoard[10] = buildViewLineWithPieces(board, 5);
            terminalBoard[11] = WHITE_BACKGROUND_BLACK_TEXT + "5" + R + B + W + B + W + B + W + B + W;
            terminalBoard[12] = b + W + B + W + B + W + B + W + B;
            terminalBoard[13] = buildViewLineWithPieces(board, 4);
            terminalBoard[14] = BLACK_BACKGROUND_WHITE_TEXT + "4" + R + W + B + W + B + W + B + W + B;
            terminalBoard[15] = w + B + W + B + W + B + W + B + W;
            terminalBoard[16] = buildViewLineWithPieces(board, 3);
            terminalBoard[17] = WHITE_BACKGROUND_BLACK_TEXT + "3" + R + B + W + B + W + B + W + B + W;
            terminalBoard[18] = b + W + B + W + B + W + B + W + B;
            terminalBoard[19] = buildViewLineWithPieces(board, 2);
            terminalBoard[20] = BLACK_BACKGROUND_WHITE_TEXT + "2" + R + W + B + W + B + W + B + W + B;
            terminalBoard[21] = w + B + W + B + W + B + W + B + W;
            terminalBoard[22] = buildViewLineWithPieces(board, 1);
            terminalBoard[23] = WHITE_BACKGROUND_BLACK_TEXT + "1" + R + B + W + B + W + B + W + B + W;
            terminalBoard[24] = b + WHITE_BACKGROUND_BLACK_TEXT + "a      " + R +
                    BLACK_BACKGROUND_WHITE_TEXT + "b      " + R +
                    WHITE_BACKGROUND_BLACK_TEXT + "c      " + R +
                    BLACK_BACKGROUND_WHITE_TEXT + "d      " + R +
                    WHITE_BACKGROUND_BLACK_TEXT + "e      " + R +
                    BLACK_BACKGROUND_WHITE_TEXT + "f      " + R +
                    WHITE_BACKGROUND_BLACK_TEXT + "g      " + R +
                    BLACK_BACKGROUND_WHITE_TEXT + "h      " + R;

            printBoard();
        } catch (Exception e) {
            System.out.println("Houve um erro no tabuleiro: " + e.getMessage());
        }
    }

    private static String buildViewLineWithPieces(Board board, int row) {
        if(row % 2 == 0) {
            return String.format(b + WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R,
                    colorPieces(BoardUtil.getRowPieces(row, board)));
        } else {
            return String.format(w + BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R +
                            BLACK_BACKGROUND_WHITE_TEXT + "  %s" + R + BLACK_BACKGROUND_WHITE_TEXT + "  " + R +
                            WHITE_BACKGROUND_BLACK_TEXT + "  %s" + R + WHITE_BACKGROUND_BLACK_TEXT + "  " + R,
                    colorPieces(BoardUtil.getRowPieces(row, board)));
        }
    }

    private static Object[] colorPieces(PieceInterface[] pieces) {
        Object[] colorPieces = new String[pieces.length];
        int i = 0;
        for (PieceInterface piece : pieces) {
            if(piece!=null) {
                colorPieces[i] = (piece.getColor()== ColorEnum.WHITE.getCode()) ?
                        WHITE_BACKGROUND_BLACK_TEXT + " " + piece.getPieceCode() + " " + R:
                        BLACK_BACKGROUND_WHITE_TEXT + " " + piece.getPieceCode() + " " + R;
            } else {
                colorPieces[i] = "   ";
            }
            i++;
        }
        return colorPieces;
    }

    private static void printBoard() {
        for (String line : terminalBoard) {
            System.out.println(line);
        }
    }



//    private void updateBoard(HashMap<String, PieceInterface> newBoard) {
//
//    }
}
