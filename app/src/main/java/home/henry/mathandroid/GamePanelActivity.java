package home.henry.mathandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import home.henry.math.AdditionQuestion;
import home.henry.math.ResultElement;
import home.henry.math.ResultStore;
import home.henry.math.TailoredTimer;

public class GamePanelActivity extends Activity {
    private int TOTAL_QUESTION = 5;
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private TextView profileTextView;
    private TextView timerTextView;
    private EditText answerEditText;
    private AdditionQuestion additionQuestion;
    public int seconds = 0;
    public int minutes = 0;

    private String configProfile;
    private int configQuestionsCount;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

//        Resources res = getResources();
//        String[] planets = res.getStringArray(R.array.user_profiles);

        super.onCreate(savedInstanceState);

        configProfile = MainActivity.getProfile(this);
        configQuestionsCount = MainActivity.getNumOfQuestions(this);

        setContentView(R.layout.activity_game_panel);
        questionNumberTextView = (TextView) this.findViewById(R.id.QUESTIONNUMBER);
        questionTextView = (TextView) this.findViewById(R.id.QUESTION);
        profileTextView = (TextView)this.findViewById(R.id.PROFILE);
        answerEditText = (EditText) this.findViewById(R.id.ANSWER);
        answerEditText.setText("");

        profileTextView.setText(configProfile);
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        Log.i("TEST", "log is working");
        additionQuestion = new AdditionQuestion(1);




        TailoredTimer.startTimer();
        final DecimalFormat df=new DecimalFormat("##");
        //Declare the timer
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.TIMER);
                        tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));
                        seconds += 1;
                        if(seconds == 0) {
                            seconds=60;minutes=minutes+1;
                        }
                    }
                });
            }
        }, 0, 1000);


        questionNumberTextView.setText("Q "+ additionQuestion.getQuestionNumber()+" of "+configQuestionsCount+":");

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_NEXT){
                    if(answerEditText.getText().toString().equals("")){
                        answerEditText.setText("0");
                    }
                    int userAnswer = Integer.parseInt(answerEditText.getText().toString());
                    boolean isCorrect = additionQuestion.checkAnswer(userAnswer);
                    Log.i("TEST", "is correct? " + isCorrect);
                    answerEditText.setText("");
                    answerEditText.requestFocus();
                    if (isCorrect) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Excellent!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        if (additionQuestion.getQuestionNum() == configQuestionsCount) {
                            TailoredTimer.stopTimer();
                            ResultStore resultStore = new ResultStore(getApplicationContext().getFilesDir() + "resultDB.json");
                            ResultElement resultElement = new ResultElement(configProfile, new Date(), (double) TailoredTimer.getElapsedTime());
                            resultStore.addResult(resultElement);
                            inputMethodManager.hideSoftInputFromWindow(answerEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                            Intent myIntent = new Intent(GamePanelActivity.this, ReportPanelActivity.class);
                            GamePanelActivity.this.startActivity(myIntent);
                            GamePanelActivity.this.finish();
                        } else {
                            additionQuestion.nextQuestion();
                            questionTextView.setText(additionQuestion.getQuestion());
                            questionNumberTextView.setText("Q "+ additionQuestion.getQuestionNumber()+" of "+configQuestionsCount+":");
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Try Again!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    return true;
                }
                return false;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TEST", "gamepanel is back");
        questionTextView.setText(additionQuestion.getQuestion());
        questionNumberTextView.setText("Q "+ additionQuestion.getQuestionNumber()+" of "+MainActivity.getNumOfQuestions(this)+":");
    }

    @Override
    protected void onPause() {
        super.onPause();
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(answerEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

    }


}