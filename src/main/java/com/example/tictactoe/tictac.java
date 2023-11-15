package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

public class tictac {

    public ArrayList<Integer> board;
    int currPlayer =1;

    int playerWins = 0;

    int AIWins = 0;

    int draws = 0;

    int winner =-2;
    boolean gameOver=false;

    Random random = new Random();

    public tictac(){
        board = new ArrayList<>(Collections.nCopies(9,0));
    }

    public void setSquare(int i){
        board.set(i, currPlayer);
    }

    public void changeTurn(){
        currPlayer=currPlayer*-1;
    }

    public boolean canSetSquare (int i){
        if (board.get(i)==0){
            return true;
        }
        else return false;
    }

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

    public void gameOver(){
        gameOver=true;
        if (winner==0) draws++;
        else if (winner==-1) AIWins++;
        else if (winner==1) playerWins++;
    }

    public void restart(){
        Collections.fill(board,0);
        currPlayer=1;
        gameOver=false;
        winner=-2;
    }

    public String getCurrentLabel(){
        if (currPlayer==1) return "O";
        else return "X";
    }
}


