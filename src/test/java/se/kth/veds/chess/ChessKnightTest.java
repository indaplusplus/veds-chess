package se.kth.veds.chess;

import static org.junit.Assert.*;
import org.junit.Test;

public class ChessKnightTest {
  /**
   *
   */
  @Test
  public void knightCheck() {
    Chess chess = new Chess();
    chess.loadDefaultBoard();
    Piece piece = chess.getPieceAtPosition(1, 7);
    assertNotNull(piece);
    chess.move(piece, 0, 5);
    assertNotNull(chess.getPieceAtPosition(0, 5));
  }
}