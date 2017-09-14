package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class NormalMove extends Move {
  /**
   * Normal move, just move the piece to the specified coordinate.
   *
   * @param piece Piece
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public NormalMove(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  /**
   * Moves the piece.
   */
  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
  }
}
