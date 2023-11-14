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

    public void buttonAction(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (trySetSquare(buttons.indexOf(button))&&AIActive) AIturn();
    }

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

    public void restart(){
        for (Button button:buttons) {
            button.setText("");
            button.setDisable(false);
        }
        AIActive=true;
        welcomeText.setText("");
        model.restart();
    }

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

    public void updateScores(){
        p1Score.setText(Integer.toString(model.playerWins));
        p2Score.setText(Integer.toString(model.AIWins));
        drawScore.setText(Integer.toString(model.draws));
    }

    public void AIturn(){
        int i = model.getAIMove();
        trySetSquare(i);
    }

}