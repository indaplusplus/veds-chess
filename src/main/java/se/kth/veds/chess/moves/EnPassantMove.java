package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.Pawn;

public class EnPassantMove extends Move {
  /**
   * 'En Passant move' - When a pawn moves two tiles forward,
   * thus making itself vulnerable to en passant.
   *
   * @param piece Piece.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public EnPassantMove(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  /**
   * Moves the tile and makes itself vulnerable to en passant.
   */
  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
    ((Pawn)this.getPiece()).setVulnerableToEnPassant(true);
  }
}
