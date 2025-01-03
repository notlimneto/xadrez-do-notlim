package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.Board;
import xadrezdonotlim.enumeration.ColorEnum;
import xadrezdonotlim.enumeration.PositionIdentifiersEnum;
import xadrezdonotlim.util.BoardUtil;
import xadrezdonotlim.validation.KingValidation;
import xadrezdonotlim.validation.PawnValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class Pawn implements PieceInterface, Cloneable{
    private final char color;
    private final char pieceCode;

    @Setter
    private String square;

    @Setter
    private String enPassantColumn;

    @Setter
    private boolean hasEnPassant;

    public Pawn(char color, String square) {
        this.color = color;
        this.pieceCode = 'P';
        this.hasEnPassant = false;
        this.square = square;
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return PawnValidation.moveValidation(board, currentPosition, nextPosition, color, hasEnPassant);
    }

    public boolean isMovePossibleConsideringSelfCheck(Board board, String currentPosition, String nextPosition) {
        if (!PawnValidation.moveValidation(board, currentPosition, nextPosition, color, hasEnPassant)) return false;

        return !BoardUtil.isMoveCausingSelfCheck(board, currentPosition, nextPosition);
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

            if (color == ColorEnum.WHITE.getValue()) {
                board.getWhitePieces().remove(currentPosition);
                board.getWhitePieces().put(nextPosition, pieceMoved);

                if (positionMap.get(nextColumn + (nextRow - 1)) != null)
                    board.getBlackPieces().remove(nextColumn + (nextRow - 1));
            } else {
                board.getBlackPieces().remove(currentPosition);
                board.getBlackPieces().put(nextPosition, pieceMoved);

                if (positionMap.get(nextColumn + (nextRow + 1)) != null)
                    board.getWhitePieces().remove(nextColumn + (nextRow + 1));
            }

            positionMap.put(nextPosition, pieceMoved);
            pieceMoved.setSquare(nextPosition);
            positionMap.put(nextColumn + ((color == ColorEnum.WHITE.getValue()) ? nextRow - 1 : nextRow + 1), null);
            pieceMoved.setHasEnPassant(false);
        } else {
            if(Math.abs(nextRow - currentRow) > 1) {
                var leftPiece = ((indexOfCurrentColumn - 1 >= 0) ? positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn - 1)) + nextRow) : null);
                var rightPiece = ((indexOfCurrentColumn + 1 <= 7) ? positionMap.get(String.valueOf(columns.charAt(indexOfCurrentColumn + 1)) + nextRow): null);

                if (leftPiece != null && leftPiece.getClass() == Pawn.class && leftPiece.getColor() != pieceMoved.getColor()) {
                    ((Pawn) leftPiece).setHasEnPassant(true);
                    ((Pawn) leftPiece).setEnPassantColumn(currentColumn);
                }
                if (rightPiece != null && rightPiece.getClass() == Pawn.class && rightPiece.getColor() != pieceMoved.getColor()) {
                    ((Pawn) rightPiece).setHasEnPassant(true);
                    ((Pawn) rightPiece).setEnPassantColumn(currentColumn);
                }
            }

            positionMap.put(currentPosition, null);
            if (board.isOriginalBoard() && ((color == ColorEnum.WHITE.getValue()) ? nextRow==8 : nextRow==1)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Para qual peça quer promover? T - Torre; C - Cavalo; B - Bispo; D - Dama");

                String promotionPieceChoice = scanner.nextLine();

                while (!promotionPieceChoice.equals("T") && !promotionPieceChoice.equals("C") && !promotionPieceChoice.equals("B") && !promotionPieceChoice.equals("D")) {
                    System.out.println("Peça de promoção inválida");
                    promotionPieceChoice = scanner.nextLine();
                }

                PieceInterface piece;

                if (promotionPieceChoice.equals("T")) piece = new Rook(color, nextPosition);
                else if (promotionPieceChoice.equals("C")) piece = new Knight(color, nextPosition);
                else if (promotionPieceChoice.equals("B")) piece = new Bishop(color, nextPosition);
                else piece = new Queen(color, nextPosition);

                updatePieceMaps(board, piece, currentPosition, nextPosition);
                positionMap.put(nextPosition, piece);

                piece.setSquare(nextPosition);
            } else {
                updatePieceMaps(board, pieceMoved, currentPosition, nextPosition);
                positionMap.put(nextPosition, pieceMoved);

                pieceMoved.setSquare(nextPosition);
            }

        }
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

    public boolean hasMove(Board board) {
        String columns = PositionIdentifiersEnum.COLUMNS.getValues();
        String rows = PositionIdentifiersEnum.ROWS.getValues();

        Integer indexOfColumn = columns.indexOf(square.substring(0, 1));
        Integer indexOfRow = rows.indexOf(square.substring(1));

        boolean isColumnInLeftBound = indexOfColumn - 1 >= 0;
        boolean isColumnInRightBound = indexOfColumn + 1 < 8;

        boolean isRowInRightBoundByOne = indexOfRow + 1 < 8;
        boolean isRowInRightBoundByTwo = indexOfRow + 2 < 8;

        List<String> possibleMoves = new ArrayList<>();

        if (isRowInRightBoundByOne) {
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn)) + rows.charAt(indexOfRow + 1));
            if(isColumnInLeftBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn - 1)) + rows.charAt(indexOfRow + 1));
            if(isColumnInRightBound) possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn + 1)) + rows.charAt(indexOfRow + 1));
        }

        if (isRowInRightBoundByTwo) {
            possibleMoves.add(String.valueOf(columns.charAt(indexOfColumn)) + rows.charAt(indexOfRow + 2));
        }

        for (String possibleMove : possibleMoves) {
            if (isMovePossibleConsideringSelfCheck(board, square, possibleMove)) return true;
        }

        return false;
    }

    @Override
    public Pawn clone() {
        try {
            return (Pawn) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
