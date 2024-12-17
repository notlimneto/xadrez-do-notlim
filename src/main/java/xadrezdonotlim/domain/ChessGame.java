package xadrezdonotlim.domain;

import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.view.BoardView;
import xadrezdonotlim.view.ChessView;

import java.util.Scanner;

public class ChessGame {

    private final String os = System.getProperty("os.name");
    private final Scanner scanner = new Scanner(System.in);
    private Board board;

    public ChessGame() {
        runGame();
    }

    private void runGame() {
        ChessView.cleanTerminal();
        board = new Board();

        int moves = 0;
        while (moves < 30) {
            if (moves % 2 == 0) {
                whiteMove();
            } else {
                blackMove();
            }
            moves++;
            ChessView.cleanTerminal();
            BoardView.updateBoard(board);
        }
    }

    private void whiteMove(){
        if (BoardUtil.isKingChecked(board, board.getWhiteKing().getSquare(), ColorEnum.WHITE.getValue())) System.out.println("\nRei Branco está em cheque!");
        System.out.println("\n" + BoardView.WHITE_BACKGROUND_BLACK_TEXT + "Brancas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move, ColorEnum.WHITE.getValue())) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
        makeMove(move);
    }

    private void blackMove(){
        if (BoardUtil.isKingChecked(board, board.getBlackKing().getSquare(), ColorEnum.BLACK.getValue())) System.out.println("\nRei Preto está em cheque!");
        System.out.println("\n" + BoardView.BLACK_BACKGROUND_WHITE_TEXT + "Pretas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move, ColorEnum.BLACK.getValue())) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
        makeMove(move);
    }

    private void makeMove(String move) {
        String currentPosition = move.substring(0, 2);
        String nextPosition = move.substring(2);

        board.getBoard().get(currentPosition).makeMove(board, currentPosition, nextPosition);
    }

    private boolean isValidMove(String move, char color){
        if (move.length() != 4) return false;

        String currentPosition = move.substring(0, 2);
        String nextPosition = move.substring(2);

        if (board.getBoard().get(currentPosition) == null || board.getBoard().get(currentPosition).getColor() != color) {
            return false;
        } else {
            PieceInterface pieceOnCurrentPosition = board.getBoard().get(currentPosition);
            boolean validMoveSyntax = (PositionIdentifiersEnum.COLUMNS.getValues().contains(nextPosition.substring(0,1)) &&
                    PositionIdentifiersEnum.ROWS.getValues().contains(nextPosition.substring(1)));
            if (!validMoveSyntax) return false;
            else return pieceOnCurrentPosition.isMovePossible(board, currentPosition, nextPosition);
        }
    }
}
