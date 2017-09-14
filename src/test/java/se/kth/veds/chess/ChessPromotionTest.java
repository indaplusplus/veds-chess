package se.kth.veds.chess;

import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Pawn;

public class ChessPromotionTest {
  /**
   * Runs a short chess game where a pawn reaches the other side of board.
   * Successful: Pawn gets changed to another piece (other than pawn or king).
   */
  @Test
  public void testPromotion() {
    Chess chess = new Chess();
    chess.loadDefaultBoard();

    int[][] dataSet =  {
        {6,7,4,7},
        {7,6,7,4},
        {1,1,1,2},
        {6,6,6,4},
        {7,1,7,3},
        {6,4,7,3},
        {6,0,5,2},
        {7,3,7,2},
        {7,0,6,0},
        {7,2,7,1},
        {0,1,0,2},
        {7,1,7,0},
    };
    this.testGameData(chess, dataSet);
    Piece changedPiece = chess.getPieceAtPosition(7, 0);
    assertTrue(!(changedPiece instanceof Pawn || changedPiece instanceof King));
  }

  private void testGameData(Chess chess, int[][] dataSet) {
    for (int[] input : dataSet) {
      Piece piece = chess.getPieceAtPosition(input[0], input[1]);
      chess.move(piece, input[2], input[3]);
    }
  }
}
