package se.kth.veds.chess;

import java.util.ArrayList;
import java.util.List;
import se.kth.veds.chess.enums.PlayerColor;
import se.kth.veds.chess.moves.Attack;
import se.kth.veds.chess.moves.NormalMove;


public abstract class Piece {
  private int xcoordinate;
  private int ycoordinate;
  private Player player;
  private Chess chess;
  private List<Move> moves;
  private boolean hasMoved = false;

  /**
   * Base for pieces in the game chess.
   *
   * @param chess Chess.
   * @param player Player.
   * @param x The piece x-coordinate.
   * @param y The piece y-coordinate.
   */
  public Piece(Chess chess, Player player, int x, int y) {
    this.setChess(chess);
    this.setPlayer(player);
    this.setMoves(new ArrayList<Move>());
    this.setX(x);
    this.setY(y);
  }

  /**
   * Refreshes the list of possible moves for a Piece.
   */
  public void refreshPossibleMoves() {
    this.clearMoves();
    this.moveConstructor();
  }

  /**
   * Determines which way a player has to move to reach the the enemies side of the chess board.
   * @return Direction to move to reach the enemies side of the board.
   */
  public int playerSideModifier() {
    return (this.getPlayer().getColor() == PlayerColor.BLACK) ? 1 : -1;
  }

  /**
   * Abstract function used to initialize moves within a piece which extends the class Piece.
   */
  public abstract void moveConstructor();

  /**
   * Fetches the possible move at given position.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   * @return Move.
   */
  public Move getMoveAtPosition(int x, int y) {
    for (Move move : this.getMoves()) {
      if (move.getX() == x && move.getYcoordinate() == y) {
        return move;
      }
    }
    return null;
  }

  /**
   * Add move to the list of allowed moves.
   * @param move Move.
   */
  public void addMove(Move move) {
    if (!this.getMoves().contains(move) && !this.outOfBound(move.getX(), move.getYcoordinate())) {
      this.getMoves().add(move);
    }
  }

  /**
   * Checks if a position is outside the chess board (out of bound).
   * @param x X-coordinate value.
   * @param y Y-coordinate value
   * @return If the position is outside the chess board.
   */
  public boolean outOfBound(int x, int y) {
    if (x >= 0 &&  x <= (Chess.BOARD_SIZE_X - 1)) {
      if (y >= 0 && y <= (Chess.BOARD_SIZE_Y - 1)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Adds moves from a list containing another list,
   * inside contains a offset in both x and y.
   * These values are then added to the pieces own x and y value,
   * and then evaluated for which move is appropriate (normal move or attack).
   */
  public void addMovesFromArray(int[][] moves) {
    for (int[] move : moves) {
      Piece piece = this.getChess().getPieceAtPosition(
          this.getX() + move[0], this.getY() + move[1]);
      if (piece == null) {
        this.addMove(new NormalMove(this, this.getX() + move[0], this.getY() + move[1]));
      } else if (piece.getPlayer().getColor() != this.getPlayer().getColor()) {
        this.addMove(new Attack(this, piece, this.getX() + move[0], this.getY() + move[1]));
      }
    }
  }

  /**
   * Follows the path between specified by direction with dx and dy until out of bound,
   * then adds appropriate moves along the way (normal move/attack).
   */
  public void loopAndAddMoves(int dx, int dy) {
    int x = this.getX();
    int y = this.getY();
    Piece piece;

    do {
      x += dx;
      y += dy;
      piece = this.getChess().getPieceAtPosition(x,y);
      if (piece == null) {
        this.addMove(new NormalMove(this, x, y));
      } else if (piece.getPlayer().getColor() == this.getPlayer().getColor()) {
        break;
      } else if (piece.getPlayer().getColor() != this.getPlayer().getColor()) {
        this.addMove(new Attack(this, piece, x, y));
        break;
      }
    } while (piece == null && !this.outOfBound(x, y));
  }

  /**
   * Clear all moves.
   */
  private void clearMoves() {
    this.getMoves().clear();
  }

  /**
   * Setter for player.
   * @param player Player.
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Getter for Player.
   * @return Player.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Getter for Chess.
   * @return Chess.
   */
  public Chess getChess() {
    return this.chess;
  }

  /**
   * Setter for Chess.
   * @param chess Chess.
   */
  public void setChess(Chess chess) {
    this.chess = chess;
  }

  /**
   * Get a list of allowed moved for the piece.
   * @return List of moves.
   */
  public List<Move> getMoves() {
    return this.moves;
  }

  /**
   * Setter for the moves list.
   * @param moves List.
   */
  public void setMoves(List<Move> moves) {
    this.moves = moves;
  }

  /**
   * Getter for if the piece has moved during the game.
   * @return If the piece has moved or not.
   */
  public boolean hasPieceMoved() {
    return this.hasMoved;
  }

  /**
   * Setter for if the piece has moved during the game.
   * @param hasMoved If the piece has moved or not.
   */
  public void setPieceMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  /**
   * Getter for the X value.
   * @return X-coordinate value.
   */
  public int getX() {
    return this.xcoordinate;
  }

  /**
   * Getter for the Y value.
   * @return Y-coordinate value.
   */
  public int getY() {
    return this.ycoordinate;
  }

  /**
   * Setter for the X value.
   * @param x X-coordinate value.
   */
  public void setX(int x) {
    this.xcoordinate = x;
  }

  /**
   * Setter for Y value.
   * @param y Y-coordinate value.
   */
  public void setY(int y) {
    this.ycoordinate = y;
  }
}
