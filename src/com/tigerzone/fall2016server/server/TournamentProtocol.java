package com.tigerzone.fall2016server.server;

/**
 * Created by lenovo on 11/17/2016.
 */

import java.util.HashMap;

public class TournamentProtocol {
    private static final int ENTER = 0;
    private static final int LOGIN = 1;
    private static final int USERNAME = 2;
    private static final int PASSWORD = 3;
    private static final int WAITING = 4;

    private int state = ENTER;
    private int enterAttempts = 0;
    private int loginAttempts = 0;
    private String user = null;

    private HashMap<String, String> credentials = new HashMap<>();

    public TournamentProtocol() { }

    public String login(String input) {
        String output = null;
        credentials.put("PLAYER1", "PASSWORD1"); //dummy credentials
        credentials.put("PLAYER2", "PASSWORD2"); //dummy credentials
        String tournamentPass = "TIGERZONE";

        if (state == ENTER) {
            output = "TOURNAMENT PASSWORD?";
            state = LOGIN;
        } else if (state == LOGIN) {
            if (input.equals(tournamentPass) && enterAttempts < 3) {
                output = "USERNAME?";
                state = USERNAME;
            } else if (enterAttempts < 3){
                output = "NOPE TRY AGAIN";
                state = LOGIN;
                enterAttempts++;
            } else {
                output = "NOPE GOODBYE";
                enterAttempts = 0;
                state = ENTER;
            }
        } else if (state == USERNAME) {
            if (credentials.containsKey(input)) {
                output = "PASSWORD?";
                user = input;
                state = PASSWORD;
            } else {
                output = "NOPE TRY AGAIN";
                state = USERNAME;
            }
        } else if (state == PASSWORD && loginAttempts < 3) {
            if (input.equals(credentials.get(user))) {
                output = "WELCOME " + user + " PLEASE WAIT FOR THE NEXT CHALLENGE";
                state = WAITING;
                //alert(); //alert begin?
            } else if (loginAttempts < 3){
                output = "NOPE TRY AGAIN";
                loginAttempts++;
                state = PASSWORD;
            } else {
                output = "NOPE GOODBYE";
                loginAttempts=0;
                state = ENTER;
            }
        }
        return output;
    }



}