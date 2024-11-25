package xadrezdonotlim.domain;

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
        System.out.println(BoardView.WHITE_BACKGROUND_BLACK_TEXT + "Brancas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move)) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
    }

    private void blackMove(){
        System.out.println(BoardView.BLACK_BACKGROUND_WHITE_TEXT + "Pretas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move)) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
    }

    private boolean isValidMove(String move){
        if (move.length() != 4) return false;

        String currentPosition = move.substring(0, 2);
        String desiredPosition = move.substring(2);

        if (board.getBoard().get(currentPosition) == null) {
            return false;
        } else {
            return (PositionIdentifiersEnum.COLUMNS.getValues().contains(desiredPosition.substring(0,1)) &&
                    PositionIdentifiersEnum.ROWS.getValues().contains(desiredPosition.substring(1)));
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
