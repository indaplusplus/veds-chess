package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class EnPassantAttack extends Move {
  private Piece pieceAttacked;

  /**
   * Attack move for en passant.
   *
   * @param piece Ally piece.
   * @param pieceAttacked Enemy piece.
   * @param x X-coordinate
   * @param y y-coordinate.
   */
  public EnPassantAttack(Piece piece, Piece pieceAttacked, int x, int y) {
    super(piece, x, y);
    this.pieceAttacked = pieceAttacked;
  }

  /**
   * Move the ally piece to it's new coordinates,
   * remove piece which is attacked.
   */
  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
    this.getPiece().getChess().removePiece(this.pieceAttacked);
  }
}
