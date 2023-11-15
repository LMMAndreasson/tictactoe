package com.example.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tictacTest {

    private tictac model;

    @BeforeEach
    public void setup(){
        model=new tictac();
    }

    @Test
    public void cantOverwriteSquare(){
        model.setSquare(3);
        assertFalse(model.canSetSquare(3));
    }

    @Test
    public void canWriteOnEmpty(){
        model.canSetSquare(3);
        model.setSquare(3);
        assertTrue(model.board.get(3)==1);
    }

    @Test
    public void willGetDraw(){
        model.board=new ArrayList<>(List.of(1,-1,1,
                                             1,-1,1,
                                             -1,1,-1));
        model.checkWin();
        assertTrue(model.winner==0);
    }

    @Test
    public void player1Wins(){
        model.board=new ArrayList<>(List.of(1,1,1,
                1,-1,-1,
                -1,1,-1));
        model.checkWin();
        assertTrue(model.winner==1);
    }

    @Test
    public void player2Wins(){
        model.board=new ArrayList<>(List.of(1,-1,-1,
                1,-1,-1,
                -1,1,-1));
        model.changeTurn();
        //The model checks who wins by checking the current players turn when
        //a 3 in a row is achieved, so we need to change the turn here to emulate proper play.
        model.checkWin();
        assertTrue(model.winner==-1);
    }


}