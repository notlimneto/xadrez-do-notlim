package xadrezdonotlim.domain;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.pieces.PieceInterface;

@Setter
@Getter
public class Square {

    private PieceInterface piece;

    private static int numberOfBlackPiecesObserving;
    private static int numberOfWhitePiecesObserving;

    Square(PieceInterface piece) {
        this.piece = piece;
    }
}
