package se.kth.veds.chess;

import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Rook;

public class ChessLongCastlingTest {

  /**
   * Tests long castling from a short game.
   */
  @Test
  public void testChessLongCastling() {
    Chess chess = new Chess();
    chess.loadDefaultBoard();

    int[][] dataSet =  {
        {1,7,0,5},
        {1,1,1,2},
        {3,6,3,4},
        {0,1,0,2},
        {2,7,4,5},
        {2,1,2,2},
        {3,7,3,6},
        {4,1,4,2},
        {4,7,2,7},
    };
    this.testGameData(chess, dataSet);

    Piece king = chess.getPieceAtPosition(2,7);
    Piece rook = chess.getPieceAtPosition(3,7);

    assertTrue(king instanceof King && rook instanceof Rook);
  }

  private void testGameData(Chess chess, int[][] dataSet) {
    for (int[] input : dataSet) {
      Piece piece = chess.getPieceAtPosition(input[0], input[1]);
      chess.move(piece, input[2], input[3]);
    }
  }
}
