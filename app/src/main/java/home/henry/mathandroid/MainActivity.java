package home.henry.mathandroid;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import home.henry.math.Config;
import home.henry.math.ConfigStore;
import home.henry.math.ResultStore;

public class MainActivity extends Activity {
    private SpinnerAdapter spinnerAdapter;
    private ImageView imageViewLogo;
    private MediaPlayer mp;
    private ConfigStore configStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScaleAnimation animation = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.start();
        imageViewLogo = findViewById(R.id.imageViewLogo);
        imageViewLogo.setAnimation(animation);

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_profiles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);
        String[] profiles=getResources().getStringArray(R.array.user_profiles);
        spinner.setSelection(getProfileIndex(profiles));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                Config.setPROFILE(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    private int getProfileIndex(String[] profiles) {
        int i=0;
        for(String profile : profiles){
            if(profile.equals(Config.getPROFILE()))
                break;
            else
                i++;

        }
        return i;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO", "main onResume");
        mp = MediaPlayer.create(this, R.raw.music);
        mp.start();

    }

    protected void onGamePanel(View fromView) {
        Intent myIntent = new Intent(MainActivity.this, GamePanelActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    protected void onReportPanel(View fromView) {
        Intent myIntent = new Intent(MainActivity.this, ReportPanelActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onStop() {
        ;
        super.onStop();
        mp.stop();
    }


}
