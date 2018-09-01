package model;

import java.util.ArrayList;

public class Connect4Model implements Connect4Operations {

  private ArrayList<ArrayList<Player>> board;

  public Connect4Model() {
    board = new ArrayList<ArrayList<Player>>();
  }

  public void startGame(int rows, int columns) throws IllegalArgumentException {
    if (rows <= 0) {
      throw new IllegalArgumentException("invalid number of rows.");
    }

    if (columns <= 0) {
      throw new IllegalArgumentException("invalid number of columns.");
    }

    if (rows < 4 && columns < 4) {
      throw new IllegalArgumentException("invalid combination of rows and columns. impossible to"
          + " win.");
    }

    for (int i = 0; i < board.size(); i++) {
      board.get(i).clear();
    }

    for (int i = 0; i < rows; i++) {
      ArrayList<Player> row = new ArrayList<Player>();

      for (int j = 0; j < columns; j++) {
        row.add(null);
      }

      board.add(row);
    }
  }

  public void takeTurn(Player p, int column) throws IllegalArgumentException {
    for (int i = board.size() - 1; i >= 0; i--) {
      if (board.get(i).get(column) == null) {
        board.get(i).set(column, p);
        return;
      }
    }

    throw new IllegalArgumentException("column is full.");
  }

  public Player isGameOver() {
    boolean isTie = true;
    Player previous = null;
    int longestRun = 0;

    // check for horizontal win
    for (int row = 0; row < board.size(); row++) {
      for (int column = 0; column < board.get(row).size(); column++) {
        Player current = board.get(row).get(column);

        if (current == null) {
          isTie = false;
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
      }

      previous = null;
      longestRun = 0;
    }

    // check for vertical win
    for (int column = 0; column < board.get(0).size(); column++) {
      for (int row = 0; row < board.size(); row++) {
        Player current = board.get(row).get(column);

        if (current == null) {
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
      }

      previous = null;
      longestRun = 0;
    }

    // check for diagonal win, northwest to southeast, bottom half of grid
    for (int row = 0; row < board.size() - 3; row++) {
      int rowi = row;
      int columni = 0;

      while (rowi < board.size() && columni < board.get(0).size()) {
        Player current = board.get(rowi).get(columni);

        if (current == null) {
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
        rowi++;
        columni++;
      }

      previous = null;
      longestRun = 0;
    }

    // check for diagonal win, southwest to northeast, bottom half of grid
    for (int row = 0; row < board.size() - 3; row++) {
      int rowi = row;
      int columni = board.get(0).size() - 1;

      while (rowi < board.size() && columni >= 0) {
        Player current = board.get(rowi).get(columni);

        if (current == null) {
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
        rowi++;
        columni--;
      }

      previous = null;
      longestRun = 0;
    }

    // check for diagonal win, northwest to southeast, top half of grid
    for (int column = 1; column < board.get(0).size() - 3; column++) {
      int rowi = 0;
      int columni = column;

      while (rowi < board.size() && columni < board.get(0).size()) {
        Player current = board.get(rowi).get(columni);

        if (current == null) {
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
        rowi++;
        columni++;
      }

      previous = null;
      longestRun = 0;
    }

    // check for diagonal win, southwest to northeast, top half of grid
    for (int column = board.get(0).size() - 2; column >= 3; column--) {
      int rowi = 0;
      int columni = column;

      while (rowi < board.size() && columni >= 0) {
        Player current = board.get(rowi).get(columni);

        if (current == null) {
          longestRun = 0;
        } else if (previous == null || previous == current) {
          longestRun++;
        } else {
          longestRun = 1;
        }

        if (longestRun == 4) {
          return current;
        }

        previous = current;
        rowi++;
        columni--;
      }

      previous = null;
      longestRun = 0;
    }

    if (isTie) {
      return Player.TIE;
    } else {
      return null;
    }
  }

  public String getGameState() {
    String result = "  ";

    for (int i = 0; i < board.get(0).size(); i++) {
      result += (i + 1) + "   ";
    }

    result += "\n";

    for (int i = 0; i < board.size(); i++) {
      result += drawRow(i);
      result += "\n";
    }

    return result;
  }

  private String drawRow(int rowNum) {
    String result = "| ";

    for (int i = 0; i < board.get(0).size(); i++) {
      if (board.get(rowNum).get(i) == Player.RED) {
        result += "O | ";
      } else if (board.get(rowNum).get(i) == Player.BLACK) {
        result += "X | ";
      } else {
        result += "  | ";
      }
    }

    return result;
  }
}
