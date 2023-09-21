package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int score=0;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";
    TextView questionsTextView;
    Button ansA,ansB,ansC,ansD,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView totalQuestionTextView=findViewById(R.id.totalQuestion);
        questionsTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submit=findViewById(R.id.submitBtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total Questions : "+totalQuestion);
        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

    Button clickedButton=(Button) v;
    if (clickedButton.getId()==R.id.submitBtn){
        if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]))
        {
            score++;
        }
        currentQuestionIndex++;
        loadNewQuestion();

    }else {
        selectedAnswer=clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.DKGRAY);
    }

    }
    void loadNewQuestion(){
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }

        questionsTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus="";
        if (score>totalQuestion*0.60){
            passStatus="Passesd";
        }else {
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+"out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface,i)-> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }

}