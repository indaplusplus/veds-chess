package se.kth.veds.chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChessPieceAmountTest {
  /**
   * Test if the right amount of pieces was added via the loadDefaultBoard method.
   */
  @Test
  public void testCorrectAmountPieces() {
    Chess chess = new Chess();
    chess.loadDefaultBoard();
    assertEquals(32, chess.getChessPieces().size());
  }
}