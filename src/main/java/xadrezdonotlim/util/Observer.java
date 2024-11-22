package xadrezdonotlim.util;

import xadrezdonotlim.domain.pieces.PieceInterface;

import java.util.HashMap;

public interface Observer {
    void updateBoard(HashMap<String, PieceInterface> newBoard);
}
