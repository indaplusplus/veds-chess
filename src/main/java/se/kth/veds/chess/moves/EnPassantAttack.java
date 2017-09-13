package se.kth.veds.chess.moves;

import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;

public class EnPassantAttack extends Move {
  private Piece pieceAttacked;
  public EnPassantAttack(Piece piece, Piece pieceAttacking, int x, int y) {
    super(piece, x, y);
    this.pieceAttacked = pieceAttacking;
  }

  public void move() {
    this.getPiece().setX(this.getX());
    this.getPiece().setY(this.getYcoordinate());
    this.getPiece().getChess().removePiece(this.pieceAttacked);
  }
}
