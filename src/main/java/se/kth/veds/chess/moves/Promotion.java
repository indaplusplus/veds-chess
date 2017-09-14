package se.kth.veds.chess.moves;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.Queen;

public class Promotion extends Move {
  /**
   * Promotion, move which happends if a pawn reaches the other side of board.
   * The pawn is then upgraded to any other piece besides king or another pawn.
   *
   * @param piece Piece.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Promotion(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  /**
   * For the time being the upgrade is limited to the queen (since it's the most common upgrade).
   * This will be reworked when a proper UI is in place.
   *
   * Creates another piece at same position, then removes the pawn.
   */
  public void move() {
    Chess chess = getPiece().getChess();
    //To be changed in the future. I just assume the player wants another queen (another one).
    Queen queen = new Queen(this.getPiece().getChess(), this.getPiece().getPlayer(), this.getX(), this.getYcoordinate());
    chess.addPiece(queen);
    chess.removePiece(this.getPiece());
  }
}
