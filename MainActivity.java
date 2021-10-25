package com.ivanivsky.ufcscoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ivanivsky.ufcscoring.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    /**
     * Set the initial values
     */
    int totalScoreBlue = 0;
    int totalScoreRed = 0;
    int blueRound = 1;
    int redRound = 1;
    String na = "NA";
    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Controller for handling the points for Blue fighter
     */

    public void scoreBlueController(int points) {
        totalScoreBlue = totalScoreBlue + points;
        if (blueRound == 1) {
            displayScoreBlueRoundOne(points);
        }

        if (blueRound == 2) {
            displayScoreBlueRoundTwo(points);
        }

        if (blueRound == 3) {
            displayScoreBlueRoundThree(points);
            displayTotalPointsBlue();
            blueRound = 999;
        }

        if (redRound > 3 && blueRound > 3) {
            finalResultController();
        }

        blueRound += 1;
    }

    /**
     * Controller for handling the points for Red fighter
     */

    public void scoreRedController(int points) {
        totalScoreRed = totalScoreRed + points;
        if (redRound == 1) {
            displayScoreRedRoundOne(points);
        }

        if (redRound == 2) {
            displayScoreRedRoundTwo(points);
        }

        if (redRound == 3) {
            displayScoreRedRoundThree(points);
            displayTotalPointsRed();
            redRound = 999;
        }

        if (redRound > 3 && blueRound > 3) {
            finalResultController();
        }

        if (redRound > 3 && blueRound > 3) {
            finalResultController();
        }

        redRound += 1;
    }

    /**
     * Controller for handling the logic if Blue wins the fight by KO, TKO, or Submission
     */

    public void finishBlueController(String finish) {
        winner = "blue";
        if (blueRound == 1) {
            displayFinishBlueRoundOne(finish);
            displayFinishBlueRoundTwo(na);
            displayFinishBlueRoundThree(na);
            displayFinishRedRoundTwo(na);
            displayFinishRedRoundThree(na);
            if (finish == "KO") {
                displayFinishRedRoundOne("KO'd");
            } else if (finish == "TKO") {
                displayFinishRedRoundOne("TKO'd");
            } else {
                displayFinishRedRoundOne("Submitted");
            }
        }

        if (blueRound == 2) {
            displayFinishBlueRoundTwo(finish);
            displayFinishBlueRoundThree(na);
            displayFinishRedRoundThree(na);
            if (finish == "KO") {
                displayFinishRedRoundTwo("KO'd");
            } else if (finish == "TKO") {
                displayFinishRedRoundTwo("TKO'd");
            } else {
                displayFinishRedRoundTwo("Submitted");
            }
        }

        if (blueRound == 3) {
            displayFinishBlueRoundThree(finish);
            if (finish == "KO") {
                displayFinishRedRoundThree("KO'd");
            } else if (finish == "TKO") {
                displayFinishRedRoundThree("TKO'd");
            } else {
                displayFinishRedRoundThree("Submitted");
            }
        }

        displayTotalPointsNA();
        displayFinalResultFinish(finish);
    }

    /**
     * Controller for handling the logic if Red wins the fight by KO, TKO, or Submission
     */

    public void finishRedController(String finish) {
        winner = "red";
        if (redRound == 1) {
            displayFinishRedRoundOne(finish);
            displayFinishRedRoundTwo(na);
            displayFinishRedRoundThree(na);
            displayFinishBlueRoundTwo(na);
            displayFinishBlueRoundThree(na);
            if (finish == "KO") {
                displayFinishBlueRoundOne("KO'd");
            } else if (finish == "TKO") {
                displayFinishBlueRoundOne("TKO'd");
            } else {
                displayFinishBlueRoundOne("Submitted");
            }
        }

        if (redRound == 2) {
            displayFinishRedRoundTwo(finish);
            displayFinishRedRoundThree(na);
            displayFinishBlueRoundThree(na);
            if (finish == "KO") {
                displayFinishBlueRoundTwo("KO'd");
            } else if (finish == "TKO") {
                displayFinishBlueRoundTwo("TKO'd");
            } else {
                displayFinishBlueRoundTwo("Submitted");
            }
        }

        if (redRound == 3) {
            displayFinishRedRoundThree(finish);
            if (finish == "KO") {
                displayFinishBlueRoundThree("KO'd");
            } else if (finish == "TKO") {
                displayFinishBlueRoundThree("TKO'd");
            } else {
                displayFinishBlueRoundThree("Submitted");
            }
        }
        displayTotalPointsNA();
        displayFinalResultFinish(finish);
    }

    /**
     * Logic and display for the Final Result section
     */

    public void finalResultController() {
        String blueStatus;
        String redStatus;

        if (totalScoreRed > totalScoreBlue) {
            redStatus = "Winner";
            blueStatus = "Loser";
        } else if (totalScoreRed < totalScoreBlue) {
            redStatus = "Loser";
            blueStatus = "Winner";
        } else {
            redStatus = "Tied";
            blueStatus = "Tied";
        }

        TextView finalResultRedView = (TextView) findViewById(R.id.red_finalresult_value);
        finalResultRedView.setText(redStatus + " By Points");

        TextView finalResultBlueView = (TextView) findViewById(R.id.blue_finalresult_value);
        finalResultBlueView.setText(blueStatus + " By Points");

    }

    /**
     * The next several methods display the values of each round for both fighters
     */

    public void displayScoreRedRoundOne(int score) {
        TextView scoreView = (TextView) findViewById(R.id.red_round1_value);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreRedRoundTwo(int score) {
        TextView scoreView = (TextView) findViewById(R.id.red_round2_value);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreRedRoundThree(int score) {
        TextView scoreView = (TextView) findViewById(R.id.red_round3_value);
        scoreView.setText(String.valueOf(score));
    }

    public void displayFinishRedRoundOne(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.red_round1_value);
        scoreView.setText(finish);
    }

    public void displayFinishRedRoundTwo(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.red_round2_value);
        scoreView.setText(finish);
    }

    public void displayFinishRedRoundThree(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.red_round3_value);
        scoreView.setText(finish);
    }

    public void displayFinishBlueRoundOne(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round1_value);
        scoreView.setText(finish);
    }

    public void displayFinishBlueRoundTwo(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round2_value);
        scoreView.setText(finish);
    }

    public void displayFinishBlueRoundThree(String finish) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round3_value);
        scoreView.setText(finish);
    }

    public void displayScoreBlueRoundOne(int score) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round1_value);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreBlueRoundTwo(int score) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round2_value);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreBlueRoundThree(int score) {
        TextView scoreView = (TextView) findViewById(R.id.blue_round3_value);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Next few methods displays the Total Points for both fighters
     */

    public void displayTotalPointsBlue() {
        TextView finalScore = (TextView) findViewById(R.id.blue_totalpts_value);
        finalScore.setText(String.valueOf(totalScoreBlue));

    }

    public void displayTotalPointsRed() {
        TextView finalScore = (TextView) findViewById(R.id.red_totalpts_value);
        finalScore.setText(String.valueOf(totalScoreRed));

    }

    public void displayTotalPointsNA() {
        TextView totalPointsBlue = (TextView) findViewById(R.id.blue_totalpts_value);
        totalPointsBlue.setText("NA");

        TextView totalPointsRed = (TextView) findViewById(R.id.red_totalpts_value);
        totalPointsRed.setText("NA");
    }

    /**
     * Displays the Final Results if a fight is finished by KO, TKO, or Submission
     */

    public void displayFinalResultFinish(String finish) {
        if (winner == "blue") {
            TextView blueFinalResult = (TextView) findViewById(R.id.blue_finalresult_value);
            blueFinalResult.setText("Winner By " + finish);

            TextView redFinalResult = (TextView) findViewById(R.id.red_finalresult_value);
            redFinalResult.setText("Loser By " + finish);
        } else {
            TextView blueFinalResult = (TextView) findViewById(R.id.blue_finalresult_value);
            blueFinalResult.setText("Loser By " + finish);

            TextView redFinalResult = (TextView) findViewById(R.id.red_finalresult_value);
            redFinalResult.setText("Winner By " + finish);
        }
    }

    /**
     * Listeners for each of the button clicks.
     */

    public void blueTenPointsListener(View view) {
        int points = 10;
        scoreBlueController(points);
    }

    public void blueNinePointsListener(View view) {
        int points = 9;
        scoreBlueController(points);
    }

    public void blueEightPointsListener(View view) {
        int points = 8;
        scoreBlueController(points);
    }

    public void blueKOListener(View view) {
        String ko = "KO";
        finishBlueController(ko);
    }

    public void blueTKOListener(View view) {
        String tko = "TKO";
        finishBlueController(tko);
    }

    public void blueSubListener(View view) {
        String submission = "Submission";
        finishBlueController(submission);
    }

    public void redTenPointsListener(View view) {
        int points = 10;
        scoreRedController(points);
    }

    public void redNinePointsListener(View view) {
        int points = 9;
        scoreRedController(points);
    }

    public void redEightPointsListener(View view) {
        int points = 8;
        scoreRedController(points);
    }

    public void redKOListener(View view) {
        String ko = "KO";
        finishRedController(ko);
    }

    public void redTKOListener(View view) {
        String tko = "TKO";
        finishRedController(tko);
    }

    public void redSubmissionListener(View view) {
        String sub = "Submission";
        finishRedController(sub);
    }

    /**
     * When the reset button is clicked, the scores, rounds, and TextViews are reset.
     */

    public void resetScores(View view) {
        totalScoreBlue = 0;
        totalScoreRed = 0;
        blueRound = 1;
        redRound = 1;
        TextView resetBlueRoundOne = (TextView) findViewById(R.id.blue_round1_value);
        resetBlueRoundOne.setText(" ");

        TextView resetBlueRoundTwo = (TextView) findViewById(R.id.blue_round2_value);
        resetBlueRoundTwo.setText(" ");

        TextView resetBlueRoundThree = (TextView) findViewById(R.id.blue_round3_value);
        resetBlueRoundThree.setText(" ");

        TextView resetRedRoundOne = (TextView) findViewById(R.id.red_round1_value);
        resetRedRoundOne.setText(" ");

        TextView resetRedRoundTwo = (TextView) findViewById(R.id.red_round2_value);
        resetRedRoundTwo.setText(" ");

        TextView resetRedRoundThree = (TextView) findViewById(R.id.red_round3_value);
        resetRedRoundThree.setText(" ");

        TextView resetBlueTotalPoints = (TextView) findViewById(R.id.blue_totalpts_value);
        resetBlueTotalPoints.setText(" ");

        TextView resetRedTotalPoints = (TextView) findViewById(R.id.red_totalpts_value);
        resetRedTotalPoints.setText(" ");

        TextView resetBlueFinalResults = (TextView) findViewById(R.id.blue_finalresult_value);
        resetBlueFinalResults.setText(" ");

        TextView resetRedFinalResults = (TextView) findViewById(R.id.red_finalresult_value);
        resetRedFinalResults.setText(" ");

    }
}