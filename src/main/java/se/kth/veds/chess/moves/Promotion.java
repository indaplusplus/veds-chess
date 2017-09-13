package se.kth.veds.chess.moves;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Move;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.Queen;

public class Promotion extends Move {
  public Promotion(Piece piece, int x, int y) {
    super(piece, x, y);
  }

  public void move() {
    Chess chess = getPiece().getChess();
    //To be changed in the future. I just assume the player wants another queen (another one).
    Queen queen = new Queen(this.getPiece().getChess(), this.getPiece().getPlayer(), this.getX(), this.getYcoordinate());
    chess.addPiece(queen);
    chess.removePiece(this.getPiece());
  }
}
