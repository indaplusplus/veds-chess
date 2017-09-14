package se.kth.veds.chess.pieces;

import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.Player;
import se.kth.veds.chess.enums.PlayerColor;
import se.kth.veds.chess.moves.Attack;
import se.kth.veds.chess.moves.EnPassantAttack;
import se.kth.veds.chess.moves.EnPassantMove;
import se.kth.veds.chess.moves.NormalMove;
import se.kth.veds.chess.moves.Promotion;

public class Pawn extends Piece {

  private boolean enPassant;

  /**
   * Setter for if the Pawn is vulnerable to en passant.
   * @param enPassant Vulnerable to en passant.
   */
  public void setVulnerableToEnPassant(boolean enPassant) {
    this.enPassant = enPassant;
  }

  /**
   * Getter for if the Pawn is vulnerable to en passant.
   * @return Vulnerable to en passant.
   */
  public boolean vulnerableToEnPassant() {
    return this.enPassant;
  }

  public Pawn(Chess chess, Player player, int x, int y) {
    super(chess, player, x, y);
  }

  /**
   * Adds the pawns moving pattern.
   */
  public void moveConstructor() {
    Piece piece;
    if ((piece = this.getChess().getPieceAtPosition(
        this.getX(), this.getY() + this.playerSideModifier())) == null) {
      int promotionYAxis = (this.getPlayer().getColor() == PlayerColor.WHITE) ? 0 : 7;
      if (this.getY() + this.playerSideModifier() == promotionYAxis) {
        this.addMove(new Promotion(this, this.getX(), this.getY() + this.playerSideModifier()));
      } else {
        this.addMove(new NormalMove(this, this.getX(), this.getY() + this.playerSideModifier()));
      }
    }

    if ((piece = this.getChess().getPieceAtPosition(
        this.getX(), this.getY() + 2 * this.playerSideModifier())) == null) {
      if (!this.hasPieceMoved()) {
        this.addMove(new EnPassantMove(
            this, this.getX(), this.getY() + 2 * this.playerSideModifier()));
      }
    }

    if ((piece = this.getChess().getPieceAtPosition(
        this.getX() - 1, this.getY() + this.playerSideModifier())) != null) {
      if (piece.getPlayer().getColor() != this.getPlayer().getColor()) {
        this.addMove(new Attack(
            this, piece, this.getX() - 1, this.getY() + this.playerSideModifier()));
      }
    } else if ((piece = this.getChess().getPieceAtPosition(
        this.getX() + 1, this.getY() + this.playerSideModifier())) != null) {
      if (piece.getPlayer().getColor() != this.getPlayer().getColor()) {
        this.addMove(new Attack(
            this, piece, this.getX() + 1, this.getY() + this.playerSideModifier()));
      }
    }

    //En Passant.
    if ((piece = this.getChess().getPieceAtPosition(
        this.getX() - 1, this.getY())) != null) {
      if (piece.getPlayer().getColor() != this.getPlayer().getColor()
          && this.vulnerableToEnPassant()) {
        this.addMove(new EnPassantAttack(
            this, piece, this.getX() - 1, this.getY() + this.playerSideModifier()));
      }
    } else if ((piece = this.getChess().getPieceAtPosition(
        this.getX() + 1, this.getY() + this.playerSideModifier())) != null) {
      if (piece.getPlayer().getColor() != this.getPlayer().getColor()
          && this.vulnerableToEnPassant()) {
        this.addMove(new EnPassantAttack(
            this, piece, this.getX() + 1, this.getY() + this.playerSideModifier()));
      }
    }
  }
}
