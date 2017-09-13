package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Queen extends Piece {
  public Queen(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  public void moveConstructor() {
    this.loopAndAddMoves(1, 1);
    this.loopAndAddMoves(-1, 1);
    this.loopAndAddMoves(1, -1);
    this.loopAndAddMoves(-1, -1);
    this.loopAndAddMoves(0, 1);
    this.loopAndAddMoves(0, -1);
    this.loopAndAddMoves(1, 0);
    this.loopAndAddMoves(-1, 0);
  }
}
