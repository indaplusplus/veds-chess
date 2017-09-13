package se.kth.veds.chess;

public abstract class Move {
  private int xcoordinate;
  private int ycoordinate;
  private Piece piece;

  /**
   * @param piece Piece.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   */
  public Move(Piece piece, int x, int y) {
    this.piece = piece;
    this.xcoordinate = x;
    this.ycoordinate = y;
  }

  /**
   *
   */
  public abstract void move();

  /**
   * Getter for the piece which the move is for.
   * @return Piece.
   */
  public Piece getPiece() {
    return this.piece;
  }

  /**
   * Getter for the X-coordinate.
   * @return X-coordinate
   */
  public int getX() {
    return this.xcoordinate;
  }

  /**
   * Getter for the Y-coordinate.
   * @return Y-coordinate
   */
  public int getYcoordinate() {
    return this.ycoordinate;
  }
}
