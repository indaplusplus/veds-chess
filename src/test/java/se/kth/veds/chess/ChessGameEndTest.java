package se.kth.veds.chess;

import static org.junit.Assert.*;

import org.junit.Test;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Rook;

public class ChessGameEndTest {
  @Test
  public void checkmateCheck() {
    Chess chess = new Chess();
    chess.addPiece(new Rook(chess, chess.getPlayerOne(), 0, 0));
    chess.addPiece(new Rook(chess, chess.getPlayerOne(), 1, 1));
    chess.addPiece(new King(chess, chess.getPlayerTwo(), 0, 7));
    assertTrue(chess.isCheckmate(chess.getPlayerTwo()));
  }

  @Test
  public void stalemateCheck() {
    Chess chess = new Chess();
    chess.addPiece(new Rook(chess, chess.getPlayerOne(), 1, 7));
    chess.addPiece(new Rook(chess, chess.getPlayerOne(), 7, 1));
    chess.addPiece(new King(chess, chess.getPlayerTwo(), 0, 0));
    assertFalse(chess.isCheckmate(chess.getPlayerTwo()));
    assertTrue(chess.isStalemate(chess.getPlayerTwo()));
  }
}