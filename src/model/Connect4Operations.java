package model;

public interface Connect4Operations {

  void startGame(int rows, int columns) throws IllegalArgumentException;

  void takeTurn(Player p, int column) throws IllegalArgumentException;

  Player isGameOver();

  String getGameState();
}
