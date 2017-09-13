package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class Castling extends Move {
  private Piece tower;
  private boolean isShortCastling;

  public Castling(Piece piece, Piece tower, boolean isShortCastling, int x, int y) {
    super(piece, x, y);
    this.tower = tower;
    this.isShortCastling = isShortCastling;
  }

  public void move() {
    this.getPiece().setX(this.getX());
    if (this.isShortCastling) {
      tower.setX(this.getX() - 1);
    } else {
      tower.setX(this.getX() + 1);
    }
    tower.setPieceMoved(true);
  }
}
