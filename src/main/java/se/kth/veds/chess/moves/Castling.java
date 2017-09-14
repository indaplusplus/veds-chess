package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Rook;

public class Castling extends Move {
  private Piece rook;
  private boolean isShortCastling;

  /**
   * Castling,
   * a move where the king and one of it's towers under certain conditions
   *
   * @param king King.
   * @param rook Tower
   * @param isShortCastling If it's a short castling (otherwise it's a long castling).
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Castling(King king, Rook rook, boolean isShortCastling, int x, int y) {
    super(king, x, y);
    this.rook = rook;
    this.isShortCastling = isShortCastling;
  }

  /**
   * Logic for castling.
   */
  public void move() {
    this.getPiece().setX(this.getX());
    if (this.isShortCastling) {
      this.rook.setX(this.getX() - 1);
    } else {
      this.rook.setX(this.getX() + 1);
    }
    this.getPiece().setPieceMoved(true);
    this.rook.setPieceMoved(true);
  }
}
