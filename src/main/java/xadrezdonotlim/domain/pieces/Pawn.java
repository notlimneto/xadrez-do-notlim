package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.validation.PawnValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class Pawn implements PieceInterface{
    private final char color;
    private final char pieceCode;
    private boolean hasMoved;

    @Setter
    private String enPassantColumn;

    @Setter
    private boolean hasEnPassant;

    public Pawn(char color) {
        this.color = color;
        this.pieceCode = 'P';
        this.hasMoved = false;
        this.hasEnPassant = false;
    }

    @Override
    public void makeMove(Board board, String currentPosition, String nextPosition) {
        var positionMap = board.getBoard();
        Pawn pieceMoved = (Pawn) positionMap.get(currentPosition);

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);
        Integer currentRow = Integer.valueOf(currentPosition.substring(1));

        String nextColumn = nextPosition.substring(0, 1);
        Integer nextRow = Integer.valueOf(nextPosition.substring(1));

        Integer indexOfCurrentColumn = columns.indexOf(currentColumn);

        if(isMoveEnPassant(board, currentPosition, nextPosition)) {
            positionMap.put(currentPosition, null);
            positionMap.put(nextPosition, pieceMoved);
            positionMap.put(nextColumn + ((color == ColorEnum.WHITE.getCode()) ? nextRow - 1 : nextRow + 1), null);
            pieceMoved.setHasEnPassant(false);
        } else {
            if(currentRow - nextRow > 1 || nextRow - currentRow > 1) {
                var leftPiece = (Pawn) ((indexOfCurrentColumn - 1 >= 0) ? positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn - 1)) + nextRow) : null);
                var rightPiece = (Pawn) ((indexOfCurrentColumn + 1 <= 7) ? positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn + 1)) + nextRow): null);

                if (leftPiece != null) {
                    leftPiece.setHasEnPassant(true);
                    leftPiece.setEnPassantColumn(currentColumn);
                }
                if (rightPiece != null) {
                    rightPiece.setHasEnPassant(true);
                    rightPiece.setEnPassantColumn(currentColumn);
                }
            }

            positionMap.put(currentPosition, null);
            if((color == ColorEnum.WHITE.getCode()) ? nextRow==8 : nextRow==1) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Para qual peça quer promover? T - Torre; C - Cavalo; B - Bispo; D - Dama");

                String promotionPiece = scanner.nextLine();

                while (!promotionPiece.equals("T") && !promotionPiece.equals("C") && !promotionPiece.equals("B") && !promotionPiece.equals("D")) {
                    System.out.println("Peça de promoção inválida");
                    promotionPiece = scanner.nextLine();
                }

                switch (promotionPiece) {
                    case "T":  positionMap.put(nextPosition, new Rook(color));
                    case "C":  positionMap.put(nextPosition, new Knight(color));
                    case "B":  positionMap.put(nextPosition, new Bishop(color));
                    case "D":  positionMap.put(nextPosition, new Queen(color));
                }
            } else positionMap.put(nextPosition, pieceMoved);

        }
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return PawnValidation.moveValidation(board, currentPosition, nextPosition, color, hasMoved, hasEnPassant);
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        List<String> possibleMoves = new ArrayList<>();

        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String column = currentPosition.substring(0, 1);
        Integer row = Integer.valueOf(currentPosition.substring(1));
        var positionMap = board.getBoard();

        return possibleMoves;
    }

    private boolean isMoveEnPassant(Board board, String currentPosition, String nextPosition) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();

        String currentColumn = currentPosition.substring(0, 1);

        String nextColumn = nextPosition.substring(0, 1);

        Integer indexCurrentColumn = columns.indexOf(currentColumn);
        Integer indexNextColumn = columns.indexOf(nextColumn);

        if (!hasEnPassant) return false;
        else {
            return board.getBoard().get(nextPosition) == null && indexCurrentColumn.compareTo(indexNextColumn) != 0;
        }
    }

    public void makePieceAdjustmentsOnMove(){
        this.hasMoved = true;
    }
}
