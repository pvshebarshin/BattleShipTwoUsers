package game.support;

import server.Controller;

import java.net.ServerSocket;
import java.net.Socket;


public class Game {
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static Controller serverController;
    public static Controller clientController;
}
