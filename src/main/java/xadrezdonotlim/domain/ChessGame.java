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
        while (true) {
            if (moves % 2 == 0) {
                if (whiteMove()) break;
            } else {
                if (blackMove()) break;
            }
            moves++;
            ChessView.cleanTerminal();
            BoardView.updateBoard(board);
        }
    }

    private boolean whiteMove(){
        if (BoardUtil.isKingChecked(board, board.getWhiteKing().getSquare(), ColorEnum.WHITE.getValue())){
            if (BoardUtil.isOutOfMoves(board, ColorEnum.WHITE.getValue())) {
                System.out.println("\nVitória das " + BoardView.BLACK_BACKGROUND_WHITE_TEXT + "pretas" + BoardView.R + "!");
                return true;
            }
            else System.out.println("\nRei " + BoardView.WHITE_BACKGROUND_BLACK_TEXT + "Branco" + BoardView.R + " está em cheque!");
        } else if (BoardUtil.isOutOfMoves(board, ColorEnum.WHITE.getValue())) {
            System.out.println("\nEmpate por afogamento!");
            return true;
        }

        System.out.println("\n" + BoardView.WHITE_BACKGROUND_BLACK_TEXT + "Brancas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move, ColorEnum.WHITE.getValue())) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
        makeMove(move);
        BoardUtil.unsetEnPassant(board, ColorEnum.WHITE.getValue());
        return false;
    }

    private boolean blackMove(){
        if (BoardUtil.isKingChecked(board, board.getBlackKing().getSquare(), ColorEnum.BLACK.getValue())) {
            if (BoardUtil.isOutOfMoves(board, ColorEnum.BLACK.getValue())) {
                System.out.println("\nVitória das " + BoardView.WHITE_BACKGROUND_BLACK_TEXT + "brancas" + BoardView.R + "!");
                return true;
            }
            else System.out.println("\nRei"  + BoardView.BLACK_BACKGROUND_WHITE_TEXT + "Preto" + BoardView.R +  "está em cheque!");
        } else if (BoardUtil.isOutOfMoves(board, ColorEnum.BLACK.getValue())) {
            System.out.println("\nEmpate por afogamento!");
            return true;
        }

        System.out.println("\n" + BoardView.BLACK_BACKGROUND_WHITE_TEXT + "Pretas" + BoardView.R + " fazem lance:");
        String move = scanner.nextLine();
        while (!isValidMove(move, ColorEnum.BLACK.getValue())) {
            System.out.println("Movimento inválido, tente novamente: ");
            move = scanner.nextLine();
        }
        makeMove(move);
        BoardUtil.unsetEnPassant(board, ColorEnum.BLACK.getValue());
        return false;
    }

    private void makeMove(String move) {
        String currentPosition = move.substring(0, 2);
        String nextPosition = move.substring(2);

        board.getBoard().get(currentPosition).makeMove(board, currentPosition, nextPosition);
    }

    public boolean isValidMove(String move, char color){
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
            if(!pieceOnCurrentPosition.isMovePossible(board, currentPosition, nextPosition)) return false;
            return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
        }
    }
}
