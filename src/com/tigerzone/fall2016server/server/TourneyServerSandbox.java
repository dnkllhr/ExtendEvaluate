package com.tigerzone.fall2016server.server;


import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.tournament.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TourneyServerSandbox {

    int numOfPlayers;
    int portNum;

    Challenge challenge;
    List<TournamentPlayer> tournamentPlayers;

    Game game;

    public TourneyServerSandbox(int portNum, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.portNum = portNum;
        this.tournamentPlayers = new ArrayList<>();
    }

    public TourneyServerSandbox(int portNum) {
        this.portNum = portNum;
        this.tournamentPlayers = new ArrayList<>();
    }

    public boolean isTournamentReady(){
        return (this.tournamentPlayers.size() == this.numOfPlayers);

    }

    public void gamePlay() throws IOException
    {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();

        String inputLine, outputLine;
        GameProtocol tp = new GameProtocol();
        //outputLine = tp.game(null);
        //connection.getOut().println(outputLine);



    }

    public void login() throws IOException {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        String inputLine, outputLine;
        LoginProtocol lp = new LoginProtocol();
        outputLine = lp.login(null);
        connection.getOut().println(outputLine);

        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = lp.login(inputLine);
            connection.getOut().println(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    System.out.println("Player has been welcomed to the system");
                    break;
                }
                if (outputLine.equals("NOPE GOOD BYE")) {
                    connection.getOut().println(outputLine);
                    System.out.println("Server says goodbye inside server");
                    connection.getOut().close();
                    connection.getIn().close();
                    connection.getClientSocket().close();
                    connection.getServerSocket().close();
                    break;
                }
            }
        }

    public Connection createConnection(int portNum) throws IOException {
        return new Connection(portNum);
    }


    public void isLoginSuccessful() throws IOException {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();


        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);

        //serverOutput.println(outputLine);
        connection.getOut().println(outputLine);

        //while ((inputLine = serverInput.readLine()) != null) {
        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            //serverOutput.println(outputLine);
            connection.getOut().println(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    addPLayerToPlayerToList(connection, tp.getUser());
                    startGame();
                }

                if (outputLine.equals("NOPE GOODBYE")) {
                    System.out.println("Server says goodbye inside server");
                    connection.getIn().close();
                    connection.getOut().close();
                    clientSocket.close();
                    serverSocket.close();
                }
            }
        connection.getIn().close();
        connection.getOut().close();
        }

    public void addPLayerToPlayerToList(Connection connection, String userName){
        TournamentPlayer tournamentPlayer = new TournamentPlayer(userName, connection);
        this.tournamentPlayers.add(tournamentPlayer);
    }

    //This class is for testing purposes only
    public void startGame(){
        System.out.println("in game method");
        TileStackGenerator stackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = stackGenerator.createTilesFromTextFile(123456789);
        TournamentPlayer player1 = tournamentPlayers.get(0);
        Game game = new Game(1, player1,  player1, tileStack, null);
        game.start();
        Connection player1Connection = player1.getConnection();

        Deque<String> writeQueue = game.getReadQueue();

        String player1Message = "";
        try{

            while ((player1Message = player1Connection.getIn().readLine()) != null) {
                System.out.println("Reading input" + player1Message);

                writeQueue.push(player1Message);
            }
        }catch(IOException e){

        }
    }

}