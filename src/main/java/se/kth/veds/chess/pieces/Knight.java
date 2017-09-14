package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Knight extends Piece {
  /**
   * The knight piece in chess.
   * @param chess Chess.
   * @param player Player.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Knight(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  /**
   * Adds knights moving pattern,
   * knights can move two squares away horizontally and one square vertically,
   * or two squares vertically and one square horizontally.
   */
  public void moveConstructor() {
    int[][] moves = {{1,2}, {2,1}, {-1,-2}, {-2,-1}, {1,-2}, {2, -1}, {-2, 1}, {-1, 2}};
    this.addMovesFromArray(moves);
  }
}
