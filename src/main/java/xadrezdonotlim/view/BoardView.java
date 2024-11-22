package xadrezdonotlim.view;

import xadrezdonotlim.domain.Board;
import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.util.BoardUtil;

public class BoardView{

    private Board board;

    public static final String RESET = "\u001B[0m";
    public static final String BLACK_BACKGROUND_WHITE_TEXT = "\u001B[40m\u001B[97m";
    public static final String WHITE_BACKGROUND_BLACK_TEXT = "\u001B[107m\u001B[30m";
    public static final String B = BLACK_BACKGROUND_WHITE_TEXT;
    public static final String W = BLACK_BACKGROUND_WHITE_TEXT;

    private final String[] terminalBoard = new String[25];

    BoardView() {
        this.board = new Board();
    }

    private void updateBoard() {
        this.terminalBoard[0] = "#   ###   ###   ###   ###";
        this.terminalBoard[1] = String.format("# %s #%s# %s #%s# %s #%s# %s #%s#", colorPieces(BoardUtil.getRowPieces(8, board)));
        this.terminalBoard[2] = "8   ###   ###   ###   ###";
        this.terminalBoard[3] = " ###   ###   ###   ###   ";
        this.terminalBoard[4] = String.format(" #%s# %s #%s# %s #%s# %s #%s# %s ", colorPieces(BoardUtil.getRowPieces(7, board)));
        this.terminalBoard[5] = "7###   ###   ###   ###   ";
        this.terminalBoard[6] = "#   ###   ###   ###   ###";
        this.terminalBoard[7] = String.format("# %s #%s# %s #%s# %s #%s# %s #%s#", colorPieces(BoardUtil.getRowPieces(6, board)));
        this.terminalBoard[8] = "6   ###   ###   ###   ###";
        this.terminalBoard[9] = " ###   ###   ###   ###   ";
        this.terminalBoard[10] = String.format(" #%s# %s #%s# %s #%s# %s #%s# %s ", colorPieces(BoardUtil.getRowPieces(5, board)));
        this.terminalBoard[11] = "5###   ###   ###   ###   ";
        this.terminalBoard[12] = "#   ###   ###   ###   ###";
        this.terminalBoard[13] = String.format("# %s #%s# %s #%s# %s #%s# %s #%s#", colorPieces(BoardUtil.getRowPieces(4, board)));
        this.terminalBoard[14] = "4   ###   ###   ###   ###";
        this.terminalBoard[15] = " ###   ###   ###   ###   ";
        this.terminalBoard[16] = String.format(" #%s# %s #%s# %s #%s# %s #%s# %s ", colorPieces(BoardUtil.getRowPieces(3, board)));
        this.terminalBoard[17] = "3###   ###   ###   ###   ";
        this.terminalBoard[18] = "#   ###   ###   ###   ###";
        this.terminalBoard[19] = String.format("# %s #%s# %s #%s# %s #%s# %s #%s#", colorPieces(BoardUtil.getRowPieces(2, board)));
        this.terminalBoard[20] = "2   ###   ###   ###   ###";
        this.terminalBoard[21] = " ###   ###   ###   ###   ";
        this.terminalBoard[22] = String.format(" #%s# %s #%s# %s #%s# %s #%s# %s ", colorPieces(BoardUtil.getRowPieces(1, board)));
        this.terminalBoard[23] = "1###   ###   ###   ###   ";
        this.terminalBoard[24] = " a  b##c  d##e  f##g  h##";
    }

    private Object[] colorPieces(PieceInterface[] pieces) {
        Object[] colorPieces = new String[pieces.length];
        int i = 0;
        for (PieceInterface piece : pieces) {
            if(piece!=null) {
                colorPieces[i] = (piece.getColor()== ColorEnum.WHITE.getCode()) ?
                        WHITE_BACKGROUND_BLACK_TEXT + piece.getPieceCode() :
                        BLACK_BACKGROUND_WHITE_TEXT + piece.getPieceCode();
            } else {
                colorPieces[i] = "-";
            }
            i++;
        }
        return colorPieces;
    }



//    private void updateBoard(HashMap<String, PieceInterface> newBoard) {
//
//    }
}
