package com.santosh.brainblaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button Enter;
    Button Btnn0;
    Button Btnn1;
    Button Btnn2;
    Button Btnn3;
    Button playbc;
    int locationofcorrectanswer;
    int score = 0;
    int Numberofquestions = 0;
    TextView scoreTextview;
    TextView timerview;
    TextView result;
    TextView sumtextview;
    ConstraintLayout gamelayout;


    public  void playq (final View view){
        score = 0;
        Numberofquestions = 0;
        timerview.setText("60s");
        scoreTextview.setText(Integer.toString(score) + "/" + Integer.toString(Numberofquestions));
        newquestion();
        playbc.setVisibility(View.INVISIBLE);

        new CountDownTimer(60100, 1000){

            @Override
            public void onTick(long l) {
                timerview.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                result.setTextColor(Color.DKGRAY);
                result.setText("Finished..");
                result.setBackgroundColor(Color.WHITE);
                playbc.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void Choose(View view){
        if(Integer.toString(locationofcorrectanswer).equals(view.getTag().toString())){
            result.setText("Correct :)");
            result.setBackgroundResource(R.drawable.rounding);
            result.setTextColor(Color.RED);
            score++;
        }else{
            result.setText("Wrong :(");
            result.setBackgroundResource(R.drawable.round);
            result.setTextColor(Color.YELLOW);
        }
        Numberofquestions++;
        scoreTextview.setText(Integer.toString(score)+"/"+Integer.toString(Numberofquestions));
        newquestion();

    }
private void startafter(View view){

}
    public void start(View view){
        Enter.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playq(findViewById(R.id.timing));
        startafter(playbc);
    }

    public void newquestion() {
        Random rand = new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumtextview.setText(Integer.toString(a)+ "+" +Integer.toString(b));

        locationofcorrectanswer = rand.nextInt(4);
        answers.clear();

        for(int i=0; i<4; i++) {
            if (i == locationofcorrectanswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        Btnn0.setText(Integer.toString(answers.get(0)));
        Btnn1.setText(Integer.toString(answers.get(1)));
        Btnn2.setText(Integer.toString(answers.get(2)));
        Btnn3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumtextview = findViewById(R.id.sum);
        Enter = findViewById(R.id.btn);
        Btnn0 = (Button) findViewById(R.id.btn1);
        Btnn1 = (Button) findViewById(R.id.btn2);
        Btnn2 = (Button) findViewById(R.id.btn3);
        Btnn3 = (Button) findViewById(R.id.btn4);
        playbc = (Button) findViewById(R.id.playb);
        result = (TextView) findViewById(R.id.decision);
        scoreTextview = (TextView) findViewById(R.id.scored);
        timerview = (TextView) findViewById(R.id.timing);
        gamelayout = findViewById(R.id.Gamelayout);
        Enter.setVisibility(View.VISIBLE);
        gamelayout.setVisibility(View.INVISIBLE);
        playbc.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(); }

    private void showAlertDialog() {
        final AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to leave?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish the activity
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}