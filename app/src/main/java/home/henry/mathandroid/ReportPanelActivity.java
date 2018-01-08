package home.henry.mathandroid;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import home.henry.math.ResultElement;
import home.henry.math.ResultStore;
import home.henry.math.TailoredTimer;

public class ReportPanelActivity extends Activity {

    ResultStore resultStore;
    TextView messageTextView, elapsedTimeLabelTextView, secLabelTextView,
            commentTextView, profileTimeTextView, elapsedTimeTextView,
            totalDoneTextView, thisTimeTextView, thisTimeLabelTextView,
            bestTextView, averageTextView, worstTextView,
            questionTypeTextView;
    RatingBar starRatingBar;

    private String configProfile, configQuestionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_panel);


        configProfile = MainActivity.getProfile(this);
        configQuestionType = MainActivity.getQuestionType(this);
        starRatingBar = (RatingBar) findViewById(R.id.star_rating_bar);
        starRatingBar.setEnabled(false);
        messageTextView = findViewById(R.id.textView_message);
        elapsedTimeLabelTextView = findViewById(R.id.textView_elapsed_time);
        secLabelTextView = findViewById(R.id.textView_sec_label);
        profileTimeTextView = (TextView) findViewById(R.id.textView_profile);
        elapsedTimeTextView = (TextView) findViewById(R.id.textView_elapsed_time);
        totalDoneTextView = findViewById(R.id.textView_total_test_done_result);
        thisTimeLabelTextView = findViewById(R.id.textView_this_time);
        thisTimeTextView = (TextView) findViewById(R.id.textView_this_time_result);
        bestTextView = (TextView) findViewById(R.id.textView_best_result);
        averageTextView = (TextView) findViewById(R.id.textView_average_result);
        worstTextView = (TextView) findViewById(R.id.textView_worst_result);
        commentTextView = (TextView) findViewById(R.id.textView_welldone);
        questionTypeTextView= findViewById(R.id.textView_question_type);

        if (!getIntent().getBooleanExtra("showComment", true)) {
            commentTextView.setVisibility(View.INVISIBLE);
            messageTextView.setVisibility(View.INVISIBLE);
            elapsedTimeLabelTextView.setVisibility(View.INVISIBLE);
            secLabelTextView.setVisibility(View.INVISIBLE);
            thisTimeLabelTextView.setText("Last Time:");


        }

        resultStore = new ResultStore(getApplicationContext().getFilesDir() + "resultDB.json");
        List<ResultElement> resultElementList = resultStore.readFromResultStore();
        List<ResultElement> profileResultElementList = resultStore.getProfileStatistics(configProfile, configQuestionType);
        questionTypeTextView.setText(configQuestionType);
        profileTimeTextView.setText(configProfile);
        elapsedTimeTextView.setText("" + resultStore.getProfileLastTime(profileResultElementList));
        totalDoneTextView.setText(profileResultElementList.size() + (resultElementList.size() > 1 ? " times" : " time"));
        thisTimeTextView.setText(resultStore.getProfileLastTime(profileResultElementList) + " sec");
        bestTextView.setText(resultStore.getProfileBest(profileResultElementList) + " sec");
        averageTextView.setText(resultStore.getProfileAverage(profileResultElementList) + " sec");
        worstTextView.setText(resultStore.getProfileWorst(profileResultElementList) + " sec");
        resultStore.showReport(configProfile);

        starRatingBar.setNumStars(5);
        final float targetStars = 4.5f;
        final Handler handler = new Handler();
        final Runnable ratingRunnable = new Runnable() {
            float currentStars = 0f;

            @Override
            public void run() {
                currentStars += 0.3f;
                Log.i("TEST", "Rating will be " + (double) currentStars);
                starRatingBar.setRating(currentStars);
                handler.postDelayed(this, 90);

                if (currentStars >= targetStars - 0.05f)
                    handler.removeCallbacks(this);

            }
        };
        handler.postDelayed(ratingRunnable, 90);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
