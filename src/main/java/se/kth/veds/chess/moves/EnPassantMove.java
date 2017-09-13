package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.Passant;

public class EnPassantMove extends Move {

  public EnPassantMove(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
    ((Passant)this.getPiece()).setVulnerableToEnPassant(true);
  }
}
