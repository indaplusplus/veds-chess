package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Bishop extends Piece {
  public Bishop(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  public void moveConstructor() {
    this.loopAndAddMoves(1, 1);
    this.loopAndAddMoves(-1, 1);
    this.loopAndAddMoves(1, -1);
    this.loopAndAddMoves(-1, -1);
  }
}
