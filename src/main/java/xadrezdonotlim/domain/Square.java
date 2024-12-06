package xadrezdonotlim.domain;

import lombok.Getter;
import lombok.Setter;
import xadrezdonotlim.domain.pieces.PieceInterface;

@Getter
public class Square {

    @Setter
    private PieceInterface piece;

    private int numberOfBlackPiecesObserving;
    private int numberOfWhitePiecesObserving;

    Square(PieceInterface piece) {
        this.piece = piece;
    }

    public void addWhiteObserver() {
        numberOfWhitePiecesObserving++;
    }

    public void removeWhiteObserver() {
        numberOfWhitePiecesObserving--;
    }

    public void addBlackObserver() {
        numberOfBlackPiecesObserving++;
    }

    public void removeBlackObserver() {
        numberOfBlackPiecesObserving--;
    }
}
