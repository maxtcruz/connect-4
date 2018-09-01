package controller;

import java.io.IOException;
import java.util.Scanner;
import model.Connect4Operations;
import model.Player;

public class Connect4Controller implements Connect4ControllerOperations {

  private Readable rd;
  private Appendable out;
  private Scanner scan;
  private Connect4Operations model;

  public Connect4Controller(Readable rd, Appendable out, Connect4Operations model) {
    this.rd = rd;
    this.out = out;
    if (this.rd != null) {
      scan = new Scanner(rd);
    }
    this.model = model;
  }

  public void playGame(int rows, int columns) throws IllegalStateException,
      IllegalArgumentException {
    if (this.rd == null || this.out == null) {
      throw new IllegalStateException("could not start game.");
    }

    try {
      model.startGame(rows, columns);
    } catch (IllegalArgumentException e) {
      throw e;
    }

    Player currentTurn = Player.RED;

    while (model.isGameOver() == null) {
      try {
        out.append(model.getGameState());
      } catch (IOException e) {
        // will never execute
      }

      int column = -1;

      while (column == -1) {
        String columnAttempt = scan.next();
        int columnNum = getToken(columnAttempt);

        if (columnNum == -1) {
          try {
            out.append("Enter a valid column number.\n");
          } catch (IOException e) {
            // will never execute
          }
        } else {
          column = columnNum;
        }
      }

      if (currentTurn == Player.RED) {
        try {
          model.takeTurn(Player.RED, column - 1);
        } catch (IllegalArgumentException e) {
          try {
            out.append("Invalid move.\n");
          } catch (IOException e1) {
            // will never execute
          }
          continue;
        }

        currentTurn = Player.BLACK;
      } else {
        try {
          model.takeTurn(Player.BLACK, column - 1);
        } catch (IllegalArgumentException e) {
          try {
            out.append("Invalid move.\n");
          } catch (IOException e1) {
            // will never execute
          }
          continue;
        }

        currentTurn = Player.RED;
      }
    }

    try {
      out.append(model.getGameState());
    } catch (IOException e) {
      // will never execute
    }

    if (model.isGameOver() == Player.TIE) {
      try {
        out.append("Tie.\n");
      } catch (IOException e) {
        // will never execute
      }
    } else {
      try {
        out.append("The winner is " + model.isGameOver().getRepresentation() + "!\n");
      } catch (IOException e) {
        // will never execute
      }
    }
  }

  private int getToken(String token) {
    int tokenNum;

    try {
      tokenNum = Integer.parseInt(token);
    } catch (NumberFormatException e) {
      return -1;
    }

    return tokenNum;
  }
}
