package main;

import controller.Connect4Controller;
import controller.Connect4ControllerOperations;
import java.io.InputStreamReader;
import model.Connect4Model;
import model.Connect4Operations;

public class Main {

  public static void main(String[] args) {

    Connect4Operations model = new Connect4Model();
    Connect4ControllerOperations controller = new
        Connect4Controller(new InputStreamReader(System.in), System.out, model);

    controller.playGame(6, 7);
  }

}
