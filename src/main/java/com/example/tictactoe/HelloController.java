package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

    @FXML
    private Label p1Score, p2Score, drawScore;

    tictac model = new tictac();

    private boolean AIActive = true;

    @FXML
    private ArrayList<Button> buttons;

    public void initialize(){
        buttons= new ArrayList<>(Arrays.asList(button0, button1, button2, button3, button4, button5, button6, button7, button8));
    }

    //Runs when clicking a square
    public void buttonAction(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (trySetSquare(buttons.indexOf(button))&&AIActive) AIturn();
    }

    //Checks if a square is valid, and sets it if it is. If it does, it returns true which lets the AI do a move.
    public boolean trySetSquare(int i){
        if (model.canSetSquare(i)) {
            model.setSquare(i);
            buttons.get(i).setText(model.getCurrentLabel());
            model.checkWin();
            if (model.gameOver){
                gameEnd();
            }
            model.changeTurn();
            return true;
        }
        else return false;
    }

    //Resets the board for a new game
    public void restart(){
        for (Button button:buttons) {
            button.setText("");
            button.setDisable(false);
        }
        AIActive=true;
        welcomeText.setText("");
        model.restart();
    }

    //Shows who won and stops the game
    public void gameEnd(){
        updateScores();
        for (Button b:buttons
             ) { b.setDisable(true);
        }
        if (model.winner==0){
            welcomeText.setText("Draw!");
        }
        if (model.winner==-1){
            welcomeText.setText("Player 2 wins!");
        }
        if (model.winner==1){
            welcomeText.setText("Player 1 wins!");
        }
        AIActive=false;
    }

    //Updates the score display
    public void updateScores(){
        p1Score.setText(Integer.toString(model.playerWins));
        p2Score.setText(Integer.toString(model.AIWins));
        drawScore.setText(Integer.toString(model.draws));
    }

    //Makes the AI do one random move
    public void AIturn(){
        int i = model.getAIMove();
        trySetSquare(i);
    }

}