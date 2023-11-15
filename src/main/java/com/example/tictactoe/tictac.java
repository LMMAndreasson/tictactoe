package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class tictac {

    public ArrayList<Integer> board;

    int PLAYER1 = 1;
    int PLAYER2 =-1;
    int currPlayer =PLAYER1;

    int playerWins = 0;

    int AIWins = 0;

    int draws = 0;

    int winner =-2;
    boolean gameOver=false;

    Random random = new Random();

    public tictac(){
        board = new ArrayList<>(Collections.nCopies(9,0));
    }

    //sets a square to the active players number
    public void setSquare(int i){
        board.set(i, currPlayer);
    }

    //changes who is the active player
    public void changeTurn(){
        currPlayer=currPlayer*-1;
    }

    //Checks if a square is open
    public boolean canSetSquare (int i){
        if (board.get(i)==0){
            return true;
        }
        else return false;
    }

    //Generates a random valid placement
    public int getAIMove(){
        if (!board.contains(0)) throw new RuntimeException("Can't find legal move");
        boolean b = false;
        int i=-1;
        while (!b){
            i=random.nextInt(0,8);
            b=canSetSquare(i);
        }
        return i;
    }

    //checks if the active player has won, or if there is a tie
    public void checkWin(){
        //checks draw
        if (!board.contains(0)) winner=0;
        //Rows
        for (int i=0; i<9; i+=3){
            if (Math.abs(board.get(i)+board.get(i+1)+ board.get(i+2))==3) {
                winner=currPlayer;
            }
        }

        //Columns
        for (int i=0; i<3; i++){
            if (Math.abs(board.get(i)+board.get(i+3)+board.get(i+6))==3){
                winner=currPlayer;
            }
        }
        //Diagonals
        if (Math.abs(board.get(0)+board.get(4)+board.get(8))==3){
            winner=currPlayer;
        }
        if (Math.abs(board.get(2)+board.get(4)+board.get(6))==3){
            winner=currPlayer;
        }
        //if winner = -2 no winner found. -1 is AI, 0 is draw, 1 is player
        if (winner!=-2) gameOver();
    }

    //sets score and tells controller game is over
    public void gameOver(){
        gameOver=true;
        if (winner==0) draws++;
        else if (winner==PLAYER2) AIWins++;
        else if (winner==PLAYER1) playerWins++;
    }

    //cleans up for a new game
    public void restart(){
        Collections.fill(board,0);
        currPlayer=PLAYER1;
        gameOver=false;
        winner=-2;
    }

    //fetches string to use for current players square placement
    public String getCurrentLabel(){
        if (currPlayer==PLAYER1) return "O";
        else return "X";
    }
}


