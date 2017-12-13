package home.henry.mathandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import home.henry.math.AdditionQuestion;

public class GamePanelActivity extends AppCompatActivity {
    private int TOTAL_QUESTION = 20;
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private EditText answerEditText;
    private AdditionQuestion additionQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_panel);

        questionNumberTextView = (TextView) this.findViewById(R.id.QUESTIONNUMBER);
        questionTextView = (TextView) this.findViewById(R.id.QUESTION);
        answerEditText = (EditText) this.findViewById(R.id.ANSWER);


        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        Log.i("TEST", "log is working");

        additionQuestion = new AdditionQuestion(1);

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_NEXT) {

                    int userAnswer = Integer.parseInt(answerEditText.getText().toString());

                    boolean isCorrect = additionQuestion.checkAnswer(userAnswer);
                    Log.i("TEST", "is correct? " + isCorrect);

                    answerEditText.setText("");

                    answerEditText.requestFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                    if (isCorrect) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Excellent!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                        additionQuestion.nextQuestion();
                        questionTextView.setText(additionQuestion.getQuestion());
                        questionNumberTextView.setText(additionQuestion.getQuestionNumber());
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Try Again!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                    if (additionQuestion.getQuestionNum() == 6) {
                        Intent myIntent = new Intent(GamePanelActivity.this, ReportPanelActivity.class);
                        GamePanelActivity.this.startActivity(myIntent);
                    }
                    return true;
                }


                return false;
            }
        });

        /*answerEditText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.i("TEST", "key is " + i);

                if (keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP ) {



                }

                return false;
            }
        });*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        questionTextView.setText(additionQuestion.getQuestion());
        questionNumberTextView.setText(additionQuestion.getQuestionNumber());
    }


}
