package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;
import se.kth.veds.chess.enums.PlayerColor;
import se.kth.veds.chess.moves.Castling;

public class King extends Piece {
  public King(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  public void moveConstructor() {
    int[][] moves = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    this.addMovesFromArray(moves);

    //Castling
    if (!this.hasPieceMoved()) {
      int playersBacklineY = (this.getPlayer().getColor() == PlayerColor.WHITE) ? 7 : 0;
      Piece rookOne = this.getChess().getPieceAtPosition(0, playersBacklineY);
      Piece rookTwo = this.getChess().getPieceAtPosition(7, playersBacklineY);
      if (rookOne instanceof Rook) {
        for (int i = 1; i < Math.abs(this.getX() - rookOne.getX()); i++) {
          if (this.getChess().getPieceAtPosition(this.getX() - i, playersBacklineY) != null || this.getChess().enemyCanAttackPosition(this.getX() - i, playersBacklineY)) {
            break;
          }
          this.addMove(new Castling(this, rookOne, false, this.getX() - 2, playersBacklineY));
        }
      }
      if (rookTwo instanceof Rook) {
        for (int i = 1; i < Math.abs(this.getX() - rookTwo.getX()); i++) {
          if (this.getChess().getPieceAtPosition(this.getX() + i, playersBacklineY) != null || this.getChess().enemyCanAttackPosition(this.getX() + i, playersBacklineY)) {
            break;
          }
          this.addMove(new Castling(this, rookTwo, true, this.getX() + 2, playersBacklineY));
        }
      }
    }
  }
}
