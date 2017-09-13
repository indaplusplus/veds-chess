package se.kth.veds.chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChessPieceAmountTest {

  @Test
  public void testCorrectAmountPieces() {
    Chess chess = new Chess();
    chess.loadDefaultBoard();
    assertEquals(32, chess.getChessPieces().size());
  }
}