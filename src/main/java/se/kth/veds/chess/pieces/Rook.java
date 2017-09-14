package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Rook extends Piece {
  /**
   * The rook piece in Chess.
   *
   * @param chess Chess.
   * @param player Player.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Rook(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  /**
   * Adds the rooks moving pattern,
   * the rook can move horizontally and vertically.
   */
  public void moveConstructor() {
    this.loopAndAddMoves(0, 1);
    this.loopAndAddMoves(0, -1);
    this.loopAndAddMoves(1, 0);
    this.loopAndAddMoves(-1, 0);
  }
}
