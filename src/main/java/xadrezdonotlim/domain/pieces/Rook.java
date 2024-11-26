package xadrezdonotlim.domain.pieces;

import lombok.Getter;
import xadrezdonotlim.domain.Board;

import java.util.List;

@Getter
public class Rook implements PieceInterface {
    private final char color;
    private final char pieceCode;

    public Rook(char color) {
        this.color = color;
        this.pieceCode = 'T';
    }

    public boolean isMovePossible(Board board, String currentPosition, String nextPosition) {
        return false;
    }

    public List<String> getPossibleMoves(String currentPosition, Board board) {
        return null;
    }

    public void makePieceAdjustmentsOnMove(){

    }
}