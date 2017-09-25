package se.kth.veds.chess;

import java.util.ArrayList;
import se.kth.veds.chess.enums.GameState;
import se.kth.veds.chess.enums.PlayerColor;
import se.kth.veds.chess.moves.Attack;
import se.kth.veds.chess.moves.EnPassantMove;
import se.kth.veds.chess.pieces.Bishop;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Knight;
import se.kth.veds.chess.pieces.Pawn;
import se.kth.veds.chess.pieces.Queen;
import se.kth.veds.chess.pieces.Rook;

public class Chess {
  public static final int BOARD_SIZE_X = 8;
  public static final int BOARD_SIZE_Y = 8;
  private Player currentPlayer;
  private Player playerOne;
  private Player playerTwo;
  private ArrayList<Piece> chessPieces;

  /**
   * Chess implementation for the slightly popular game Chess.
   */
  public Chess() {
    this.setPlayerOne(new Player(PlayerColor.WHITE));
    this.setPlayerTwo(new Player(PlayerColor.BLACK));
    this.setCurrentPlayer(this.getPlayerOne());
    this.setChessPieces(new ArrayList<Piece>());
  }

  /**
   * Load the default pieces for a standard game of chess.
   */
  public void loadDefaultBoard() {
    //White
    for (int i = 0; i < Chess.BOARD_SIZE_X; i++) {
      this.addPiece(new Pawn(this, this.getPlayerOne(), i, 6));
    }
    this.addPiece(new Bishop(this, this.getPlayerOne(), 2, 7));
    this.addPiece(new Bishop(this, this.getPlayerOne(), 5, 7));
    this.addPiece(new Rook(this, this.getPlayerOne(), 0, 7));
    this.addPiece(new Rook(this, this.getPlayerOne(), 7, 7));
    this.addPiece(new Knight(this, this.getPlayerOne(), 1, 7));
    this.addPiece(new Knight(this, this.getPlayerOne(), 6, 7));
    this.addPiece(new Queen(this, this.getPlayerOne(), 3, 7));
    this.addPiece(new King(this, this.getPlayerOne(), 4, 7));

    //Black
    for (int i = 0; i < Chess.BOARD_SIZE_X; i++) {
      this.addPiece(new Pawn(this, this.getPlayerTwo(), i, 1));
    }
    this.addPiece(new Bishop(this, this.getPlayerTwo(), 2, 0));
    this.addPiece(new Bishop(this, this.getPlayerTwo(), 5, 0));
    this.addPiece(new Rook(this, this.getPlayerTwo(), 0, 0));
    this.addPiece(new Rook(this, this.getPlayerTwo(), 7, 0));
    this.addPiece(new Knight(this, this.getPlayerTwo(), 1, 0));
    this.addPiece(new Knight(this, this.getPlayerTwo(), 6, 0));
    this.addPiece(new Queen(this, this.getPlayerTwo(), 3, 0));
    this.addPiece(new King(this, this.getPlayerTwo(), 4, 0));
  }

  /**
   * Switch the current player.
   */
  private void turn() {
    if (this.getCurrentPlayer() == this.getPlayerOne()) {
      this.setCurrentPlayer(this.getPlayerTwo());
    } else {
      this.setCurrentPlayer(this.getPlayerOne());
    }
  }

  /**
   * Determines if a player is checkmated.
   * @param player Player
   * @return If the player is checkmated.
   */
  public boolean isCheckmate(Player player) {
    King king = this.getPlayersKing(player);
    return this.isCheck(king) && this.isStalemate(player);
  }

  /**
   * Finds the players king in the list of pieces.
   * @param player Player.
   * @return King.
   */
  public King getPlayersKing(Player player) {
    for (Piece piece : this.getChessPieces()) {
      if (piece.getPlayer().getColor() == player.getColor()) {
        if (piece instanceof King) {
          return (King)piece;
        }
      }
    }
    return null;
  }

  /**
   * Determines if a player can't do any moves (aka stalemate).
   * @param player Player
   * @return If the player is stalemate.
   */
  public boolean isStalemate(Player player) {
    for (Piece piece : this.getChessPieces()) {
      if (piece.getPlayer().getColor() == player.getColor()) {
        piece.refreshPossibleMoves();
        for (Move move : piece.getMoves()) {
          if (!this.enemyCanAttackPosition(move.getX(), move.getYcoordinate(), player)) {
            return false;
          }
        }
      }
    }
    return true;
  }


  /**
   * Determines if the King is in check or not.
   * @param king King
   * @return If the King is in check.
   */
  public boolean isCheck(King king) {
    return this.enemyCanAttackPiece(king);
  }

  /**
   * Determines if a enemy can attack a certain piece.
   * @param allyPiece Piece.
   * @return If the enemy can attack the specified piece.
   */
  public boolean enemyCanAttackPiece(Piece allyPiece) {
    return this.enemyCanAttackPosition(allyPiece.getX(), allyPiece.getY(), allyPiece.getPlayer());
  }

  /**
   * Determines if a enemy can attack a certain position.
   * @param x X-coordinate.
   * @param y Y-coordinate.
   * @return If a enemy can attack the position.
   */
  public boolean enemyCanAttackPosition(int x, int y, Player player) {
    for (Piece piece : this.getChessPieces()) {
      if (piece.getPlayer().getColor() != player.getColor()) {
        piece.refreshPossibleMoves();
        for (Move move : piece.getMoves()) {
          if (move.getX() == x && move.getYcoordinate() == y) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Determines if the move is invalid or not.
   * If successful it handles logic such as moving pieces,
   * handling turns and updating a players game state.
   *
   * @param piece Piece.
   * @param newX X-coordinate the piece wants to move to.
   * @param newY Y-coordinate the piece wants to move to.
   * @return If the move was successful or not.
   */
  public boolean move(Piece piece, int newX, int newY) {
    piece.refreshPossibleMoves();
    Move move = piece.getMoveAtPosition(newX, newY);
    if (move != null) {
      if (this.isCheck(this.getPlayersKing(piece.getPlayer())) && !(piece instanceof King)) {
        return false;
      }
      move.move();
      /*
       * If another move other than "En Passant move" has been made by a Pawn makes the piece not vulnerable.
       */
      if (piece instanceof Pawn) {
        if (!(move instanceof EnPassantMove)) {
          ((Pawn)piece).setVulnerableToEnPassant(false);
        }
      }
      piece.setPieceMoved(true);
      this.updatePlayerGameState(this.getCurrentPlayer());
      this.turn();
      return true;
    }
    return false;
  }

  /**
   * Updates the players game state depending on if the player is in check,
   * stalemate, checkmate or if the game is just ongoing.
   * @param player Player
   */
  private void updatePlayerGameState(Player player) {
    King king = this.getPlayersKing(player);
    if (this.isCheck(king)) {
      player.setState(GameState.CHECK);
    } else if (this.isStalemate(player)) {
      player.setState(GameState.STALEMATE);
    } else if (this.isCheckmate(player)) {
      player.setState(GameState.CHECKMATE);
    } else {
      player.setState(GameState.ONGOING);
    }
  }

  /**
   * Fetches the piece at coordinate. Null if none exists.
   * @param x X-coordinate
   * @param y Y-coordinate
   * @return Piece located at coordinate.
   */
  public Piece getPieceAtPosition(int x, int y) {
    for (Piece piece : this.getChessPieces()) {
      if (piece.getX() == x && piece.getY() == y) {
        return piece;
      }
    }
    return null;
  }

  /**
   * Adds a piece to the set of pieces.
   * @param piece Piece
   */
  public void addPiece(Piece piece) {
    this.getChessPieces().add(piece);
  }

  /**
   * Removes a piece from the set of pieces.
   */
  public void removePiece(Piece piece) {
    this.getChessPieces().remove(piece);
  }

  /**
   * Setter for the chess pieces on the board.
   * @param pieces List of pieces.
   */
  private void setChessPieces(ArrayList<Piece> pieces) {
    this.chessPieces = pieces;
  }

  /**
   * Getter for the chess pieces on the board.
   * @return Chess pieces on the board.
   */
  public ArrayList<Piece> getChessPieces() {
    return this.chessPieces;
  }

  /**
   * Setter for the current player.
   * @param player Current player
   */
  public void setCurrentPlayer(Player player) {
    this.currentPlayer = player;
  }

  /**
   * Getter for the current player.
   * @return Current player.
   */
  public Player getCurrentPlayer() {
    return this.currentPlayer;
  }

  /**
   * Setter for player one.
   * @param player Player one.
   */
  public void setPlayerOne(Player player) {
    this.playerOne = player;
  }

  /**
   * Getter for player one.
   * @return Player one.
   */
  public Player getPlayerOne() {
    return this.playerOne;
  }

  /**
   * Setter for player two.
   * @param player Player two.
   */
  public void setPlayerTwo(Player player) {
    this.playerTwo = player;
  }

  /**
   * Getter for player two.
   * @return Player two.
   */
  public Player getPlayerTwo() {
    return this.playerTwo;
  }
}
