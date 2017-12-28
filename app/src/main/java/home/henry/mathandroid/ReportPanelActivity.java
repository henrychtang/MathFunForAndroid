package home.henry.mathandroid;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import home.henry.math.Config;
import home.henry.math.ResultElement;
import home.henry.math.ResultStore;
import home.henry.math.TailoredTimer;

public class ReportPanelActivity extends Activity {

    ResultStore resultStore;
    TextView profileTimeTextView, elapsedTimeTextView, totalDoneTextView, thisTimeTextView, bestTextView, averageTextView, worstTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_panel);

        final RatingBar starRatingBar = (RatingBar) findViewById(R.id.star_rating_bar);
        profileTimeTextView = (TextView) findViewById(R.id.textView_profile);
        elapsedTimeTextView = (TextView) findViewById(R.id.textView_elapsed_time);
        totalDoneTextView = (TextView) findViewById(R.id.textView_total_test_done_result3);
        thisTimeTextView = (TextView) findViewById(R.id.textView_this_time_result);
        bestTextView = (TextView) findViewById(R.id.textView_best_result);
        averageTextView = (TextView) findViewById(R.id.textView_average_result);
        worstTextView = (TextView) findViewById(R.id.textView_worst_result);

        resultStore = new ResultStore(getApplicationContext().getFilesDir() + "resultDB.json");
        List<ResultElement> resultElementList = resultStore.readFromResultStore();
        List<ResultElement> profileResultElementList = resultStore.getProfileStatistics(Config.getPROFILE());
        DecimalFormat df = new DecimalFormat("#.##");
        profileTimeTextView.setText(Config.getPROFILE());
        elapsedTimeTextView.setText("" + df.format(TailoredTimer.getElapsedTime()));
        totalDoneTextView.setText(profileResultElementList.size() + (resultElementList.size() > 1 ? " times" : " time"));
        thisTimeTextView.setText(df.format(TailoredTimer.getElapsedTime()) + " sec");
        bestTextView.setText(resultStore.getProfileBest(profileResultElementList) + " sec");
        averageTextView.setText(resultStore.getProfileAverage(profileResultElementList) + " sec");
        worstTextView.setText(resultStore.getProfileWorst(profileResultElementList) + " sec");
        resultStore.showReport(Config.getPROFILE());

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
}
