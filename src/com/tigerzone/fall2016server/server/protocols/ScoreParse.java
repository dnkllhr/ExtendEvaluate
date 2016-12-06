package com.tigerzone.fall2016server.server.protocols;

import com.tigerzone.fall2016server.tournament.Game;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.util.Scanner;

/**
 * Created by Aidan on 12/3/2016.
 */
public class ScoreParse  {

    private static int whiteSpaceCounter(final String testCode){
        int whiteSpaces = 0;
        if(testCode != null){
            for(int i = 0; i < testCode.length(); i++){
                if(Character.isWhitespace(testCode.charAt(i))){
                    whiteSpaces++;
                }
            }
        }
        return whiteSpaces;
    }


    public static boolean parseForCorrectness(String s, Game game, TournamentPlayer player1, TournamentPlayer player2) {
        if(whiteSpaceCounter(s) != 8){
            return false;
        }
        Scanner scanner = new Scanner(s);
        scanner.next();
        if(scanner.nextInt() != game.getGameID()){
            return false;
        }
        scanner.next();
        scanner.next();
        if(scanner.next() == player1.getUsername()){
            if(scanner.nextInt() == game.getPlayer1FinalScore()){
                scanner.next();
                if(scanner.next() == player2.getUsername()){
                    if(scanner.nextInt() == game.getPlayer2FinalScore()){
                        return true;
                    }
                }
            }
        }
        else if(scanner.next() == player2.getUsername()){
            if(scanner.nextInt() == game.getPlayer2FinalScore()){
                scanner.next();
                if(scanner.next() == player1.getUsername()){
                    if(scanner.nextInt() == game.getPlayer1FinalScore()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
