package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Queen extends Piece {
  /**
   * The queen piece in Chess.
   *
   * @param chess Chess.
   * @param player Player.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Queen(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  /**
   * Adds the queens moving pattern,
   * the queen can move horizontally, vertically and diagonally.
   */
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
