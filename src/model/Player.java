package model;

public enum Player {
  RED("red"), BLACK("black"), TIE("tie");

  private String representation;

  Player(String representation) {
    this.representation = representation;
  }

  public String getRepresentation() {
    return representation;
  }
}
