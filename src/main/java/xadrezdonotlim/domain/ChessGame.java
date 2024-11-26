package xadrezdonotlim.domain;

import xadrezdonotlim.domain.pieces.PieceInterface;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.view.BoardView;

import java.util.Scanner;

public class ChessGame {

    private final String os = System.getProperty("os.name");
    private final Scanner scanner = new Scanner(System.in);
    private Board board;

    public ChessGame() {
        runGame();
    }

    private void runGame() {
        limpaTerminal();
        board = new Board();

        int moves = 0;
        while (moves < 30) {
            if (moves % 2 == 0) {
                whiteMove();
            } else {
                blackMove();
            }
            moves++;
            limpaTerminal();
            BoardView.updateBoard(board);
        }
    }

    private void whiteMove(){
        System.out.println("\n" + BoardView.WHITE_BACKGROUND_BLACK_TEXT + "Brancas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move)) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
        makeMove(move);

    }

    private void blackMove(){
        System.out.println("\n" + BoardView.BLACK_BACKGROUND_WHITE_TEXT + "Pretas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move)) {
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

    private boolean isValidMove(String move){
        if (move.length() != 4) return false;

        String currentPosition = move.substring(0, 2);
        String nextPosition = move.substring(2);

        if (board.getBoard().get(currentPosition) == null) {
            return false;
        } else {
            PieceInterface pieceOnCurrentPosition = board.getBoard().get(currentPosition);
            boolean validMoveSyntax = (PositionIdentifiersEnum.COLUMNS.getValues().contains(nextPosition.substring(0,1)) &&
                    PositionIdentifiersEnum.ROWS.getValues().contains(nextPosition.substring(1)));
            if (!validMoveSyntax) return false;
            else return pieceOnCurrentPosition.isMovePossible(board, currentPosition, nextPosition);
        }
    }

    private void limpaTerminal() {
        try {
            if (os.contains("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch (final Exception e)
        {
            System.out.println("Erro ao limpar terminal: " + e.getMessage());
        }
    }
}
