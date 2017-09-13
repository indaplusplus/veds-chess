package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class Attack extends Move {
  private Piece pieceAttacked;

  /**
   *
   * @param piece
   * @param pieceAttacked
   * @param x
   * @param y
   */
  public Attack(Piece piece, Piece pieceAttacked, int x, int y) {
    super(piece, x, y);
    this.pieceAttacked = pieceAttacked;
  }

  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
    System.out.println(String.format("Attack (%d, %d)", this.pieceAttacked.getX(), this.pieceAttacked.getY()));
    this.getPiece().getChess().removePiece(this.pieceAttacked);
  }
}
