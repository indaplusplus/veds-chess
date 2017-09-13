package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class NormalMove extends Move {
  public NormalMove(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
  }
}
