package home.henry.mathandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onGamePanel(View fromView){

    Intent myIntent = new Intent(MainActivity.this, GamePanelActivity.class);
    MainActivity.this.  startActivity(myIntent);
    }

    protected void onReportPanel(View fromView){

        Intent myIntent = new Intent(MainActivity.this, ReportPanelActivity.class);
        MainActivity.this.  startActivity(myIntent);
    }
}
