package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * true: X
     * false: Y
     */
    public boolean turn;
    AppCompatButton[][] grid;
    TextView textView;
    AppCompatButton resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turn = true;
        grid = new AppCompatButton[3][3];
        grid[0][0] = findViewById(R.id.button00);
        grid[0][1] = findViewById(R.id.button01);
        grid[0][2] = findViewById(R.id.button02);
        grid[1][0] = findViewById(R.id.button10);
        grid[1][1] = findViewById(R.id.button11);
        grid[1][2] = findViewById(R.id.button12);
        grid[2][0] = findViewById(R.id.button20);
        grid[2][1] = findViewById(R.id.button21);
        grid[2][2] = findViewById(R.id.button22);
        textView = findViewById(R.id.textView);
        resetButton = findViewById(R.id.reset);
    }

    /**
     * @param button an instance of class AppCompatButton
     * @return whether the button (box in the 3x3 grid) is empty
     */
    private boolean boxIsEmpty(AppCompatButton button) {
        return !button.getText().equals("X") && !button.getText().equals("O");
    }

    /**
     * The function that gets called when the reset button is pressed
     *
     * @param v the button of class View
     */
    public void reset(View v) {
        clearAllBoxes();
        enableAllBoxes();
        textView.setText("");
        turn = true;
    }

    /**
     * Clears the 3x3 grid
     */
    private void clearAllBoxes() {
        grid[0][0].setText("");
        grid[0][1].setText("");
        grid[0][2].setText("");
        grid[1][0].setText("");
        grid[1][1].setText("");
        grid[1][2].setText("");
        grid[2][0].setText("");
        grid[2][1].setText("");
        grid[2][2].setText("");
    }

    /**
     * Enables all boxes in the 3x3 grid
     */
    private void enableAllBoxes() {
        grid[0][0].setEnabled(true);
        grid[0][1].setEnabled(true);
        grid[0][2].setEnabled(true);
        grid[1][0].setEnabled(true);
        grid[1][1].setEnabled(true);
        grid[1][2].setEnabled(true);
        grid[2][0].setEnabled(true);
        grid[2][1].setEnabled(true);
        grid[2][2].setEnabled(true);
    }

    /**
     * Disables all boxes in the 3x3 grid
     */
    private void disableAllBoxes() {
        grid[0][0].setEnabled(false);
        grid[0][1].setEnabled(false);
        grid[0][2].setEnabled(false);
        grid[1][0].setEnabled(false);
        grid[1][1].setEnabled(false);
        grid[1][2].setEnabled(false);
        grid[2][0].setEnabled(false);
        grid[2][1].setEnabled(false);
        grid[2][2].setEnabled(false);
    }

    /**
     * The function that gets called when a button (box) in the 3x3 grid is pressed.
     * It updates the text accordingly and disables the button.
     * Afterwards, it acts according to the current game state.
     *
     * @param v the button of class View
     */
    public void clicked(View v) {
        AppCompatButton button = (AppCompatButton) v;
        String text;
        if (boxIsEmpty(button)) {
            if (turn) {
                button.setTextColor(Color.parseColor("blue"));
                button.setText("X");
            } else {
                button.setTextColor(Color.parseColor("red"));
                button.setText("O");
            }
            button.setEnabled(false);
            int gameState = getGameState();
            if (gameState == 1) {
                if (turn) {
                    text = "X won!";
                } else {
                    text = "O won!";
                }
                textView.setText(text);
                disableAllBoxes();
            } else if (gameState == 2) {
                text = "Tie";
                textView.setText(text);
            }
        }
        turn = !turn;
    }

    private boolean equals(AppCompatButton b1, AppCompatButton b2) {
        return b1.getText().toString().equals(b2.getText().toString());
    }

    /**
     * @return the state of the game:
     * 0 - the game is ongoing
     * 1 - somebody won
     * 2 - tie
     */
    public int getGameState() {
        if ((equals(grid[0][0], grid[0][1]) &&
                equals(grid[0][0], grid[0][2]) &&
                (!boxIsEmpty(grid[0][0]))) ||

                (equals(grid[1][0], grid[1][1]) &&
                        equals(grid[1][0], grid[1][2]) &&
                        (!boxIsEmpty(grid[1][0]))) ||

                (equals(grid[2][0], grid[2][1]) &&
                        equals(grid[2][0], grid[2][2]) &&
                        (!boxIsEmpty(grid[2][0]))) ||

                (equals(grid[0][0], grid[1][0]) &&
                        equals(grid[0][0], grid[2][0]) &&
                        (!boxIsEmpty(grid[0][0]))) ||

                (equals(grid[0][1], grid[1][1]) &&
                        equals(grid[0][1], grid[2][1]) &&
                        (!boxIsEmpty(grid[0][1]))) ||

                (equals(grid[0][2], grid[1][2]) &&
                        equals(grid[0][2], grid[2][2]) &&
                        (!boxIsEmpty(grid[0][2]))) ||

                (equals(grid[0][0], grid[1][1]) &&
                        equals(grid[0][0], grid[2][2]) &&
                        (!boxIsEmpty(grid[0][0]))) ||

                (equals(grid[0][2], grid[1][1]) &&
                        equals(grid[0][2], grid[2][0]) &&
                        (!boxIsEmpty(grid[0][2])))) {
            return 1; // somebody won
        }

        if (boxIsEmpty(grid[0][0]) ||
                boxIsEmpty(grid[0][1]) ||
                boxIsEmpty(grid[0][2]) ||
                boxIsEmpty(grid[1][0]) ||
                boxIsEmpty(grid[1][1]) ||
                boxIsEmpty(grid[1][2]) ||
                boxIsEmpty(grid[2][0]) ||
                boxIsEmpty(grid[2][1]) ||
                boxIsEmpty(grid[2][2])
        ) {
            return 0; // game is ongoing
        }

        return 2; // it's a tie
    }
}