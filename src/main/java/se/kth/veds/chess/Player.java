package se.kth.veds.chess;

import se.kth.veds.chess.enums.GameState;
import se.kth.veds.chess.enums.PlayerColor;

public class Player {
  private String name;
  private PlayerColor color;
  private GameState state;

  public Player(PlayerColor color) {
    this.setColor(color);
    this.setState(GameState.ONGOING);
  }

  public void setState(GameState state) {
    this.state = state;
  }

  public GameState getState() {
    return this.state;
  }

  /**
   *
   * @param color Player color.
   */
  public void setColor(PlayerColor color) {
    this.color = color;
  }

  /**
   *
   * @return Player color.
   */
  public PlayerColor getColor() {
    return this.color;
  }

  /**
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   *
   * @return
   */
  public String getName() {
    return this.name;
  }
}
