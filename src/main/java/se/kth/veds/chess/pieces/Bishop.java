package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Bishop extends Piece {
  /**
   * The bishop piece in Chess.
   *
   * @param chess Chess.
   * @param player Player.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Bishop(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  /**
   * Adds the bishops moving pattern,
   * the bishop can move diagonally.
   */
  public void moveConstructor() {
    this.loopAndAddMoves(1, 1);
    this.loopAndAddMoves(-1, 1);
    this.loopAndAddMoves(1, -1);
    this.loopAndAddMoves(-1, -1);
  }
}
