package se.kth.veds.chess;

import se.kth.veds.chess.enums.GameState;
import se.kth.veds.chess.enums.PlayerColor;

public class Player {
  private PlayerColor color;
  private GameState state;

  /**
   * Constructor for player, sets the player color plus the default game state (ONGOING).
   * @param color Player color.
   */
  public Player(PlayerColor color) {
    this.setColor(color);
    this.setState(GameState.ONGOING);
  }

  /**
   * Setter for the players game state.
   * @param state GameState.
   */
  public void setState(GameState state) {
    this.state = state;
  }

  /**
   * Getter for the players game state.
   * @return GameState.
   */
  public GameState getState() {
    return this.state;
  }

  /**
   * Setter for the players color.
   * @param color PlayerColor.
   */
  public void setColor(PlayerColor color) {
    this.color = color;
  }

  /**
   * Getter for the players color.
   * @return PlayerColor.
   */
  public PlayerColor getColor() {
    return this.color;
  }
}
