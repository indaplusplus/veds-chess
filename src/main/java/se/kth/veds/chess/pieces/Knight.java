package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;

public class Knight extends Piece {
  public Knight(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  public void moveConstructor() {
    int[][] moves = {{1,2}, {2,1}, {-1,-2}, {-2,-1}, {1,-2}, {2, -1}, {-2, 1}, {-1, 2}};
    this.addMovesFromArray(moves);
  }
}
