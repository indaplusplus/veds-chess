package se.kth.veds.chess.ui;

import java.util.Scanner;
import se.kth.veds.chess.Chess;
import se.kth.veds.chess.Piece;
import se.kth.veds.chess.pieces.Bishop;
import se.kth.veds.chess.pieces.King;
import se.kth.veds.chess.pieces.Knight;
import se.kth.veds.chess.pieces.Passant;
import se.kth.veds.chess.pieces.Queen;
import se.kth.veds.chess.pieces.Rook;

public class CommandLineInterface {

  private Chess chess;

  public Chess getChess() {
    return this.chess;
  }

  public void setChess(Chess chess) {
    this.chess = chess;
  }

  private char pieceTypeToChar(Piece piece) {
    if (piece instanceof King) {
      return 'K';
    } else if (piece instanceof Passant) {
      return 'P';
    } else if (piece instanceof Knight) {
      return 'H';
    } else if (piece instanceof Bishop) {
      return 'B';
    } else if (piece instanceof Rook) {
      return 'R';
    } else if (piece instanceof Queen) {
      return 'Q';
    }
    return '-';
  }

  private void play() {
    Scanner scanner = new Scanner(System.in);
    while(true) {
      this.printBoard();
      System.out.println(String.format("It's %s turn, current status: %s", this.chess.getCurrentPlayer().getColor(), this.getChess().getCurrentPlayer().getState()));
      int x1 = scanner.nextInt();
      int y1 = scanner.nextInt();
      int x2 = scanner.nextInt();
      int y2 = scanner.nextInt();
      Piece piece = this.getChess().getPieceAtPosition(x1, y1);
      if (piece == null) {
        System.out.println(String.format("No piece at position: (%d, %d)", x1, y1));
      } else {
        if (piece.getPlayer() == this.chess.getCurrentPlayer()) {
          if (this.getChess().move(piece, x2, y2)) {
            System.out.println("Move was successful.");
          } else {
            System.out.println("Illegal move");
          }
        } else {
          System.out.println("You can't move other players pieces!");
        }
      }
    }
  }

  private void printBoard() {
    for (int y = 0; y < Chess.BOARD_SIZE_Y; y++) {
      for (int x = 0; x < Chess.BOARD_SIZE_X; x++) {
        Piece piece = this.getChess().getPieceAtPosition(x, y);
        if (piece != null) {
          System.out.print(this.pieceTypeToChar(piece));
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
    }
  }

  public CommandLineInterface() {
    this.setChess(new Chess());
    this.getChess().loadDefaultBoard();
    this.play();
  }
}
