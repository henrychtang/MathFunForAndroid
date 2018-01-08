package home.henry.mathandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final Spinner profileSpinner = findViewById(R.id.profileSpinner);
        ArrayAdapter<CharSequence> profileAdapter = ArrayAdapter.createFromResource(this,
                R.array.user_profiles, android.R.layout.simple_spinner_item);
        profileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] profiles = getResources().getStringArray(R.array.user_profiles);
        profileSpinner.setAdapter(profileAdapter);
        profileSpinner.setSelection(getProfileIndex(profiles));

        final Spinner questionTypeSpinner = findViewById(R.id.gameTypeSpinner);
        ArrayAdapter<CharSequence> quesitonTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.question_type, android.R.layout.simple_spinner_item);
        quesitonTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] questionTypes = getResources().getStringArray(R.array.question_type);
        questionTypeSpinner.setAdapter(quesitonTypeAdapter);
        questionTypeSpinner.setSelection(getQuestionTypeIndex(questionTypes));

        final Spinner numberOfQuestionSpinner = findViewById(R.id.numberOfQuestionsSpinner);
        ArrayAdapter<CharSequence> numberOfQuestionAdapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_questions, android.R.layout.simple_spinner_item);
        numberOfQuestionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[] numberOfQuestions = getResources().getStringArray(R.array.number_of_questions);
        numberOfQuestionSpinner.setAdapter(numberOfQuestionAdapter);
        numberOfQuestionSpinner.setSelection(getNumberOfQuestionIndex(numberOfQuestions));

        profileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(SettingActivity.this, profileSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putString("profile", parent.getItemAtPosition(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        questionTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(SettingActivity.this, questionTypeSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putString("question_type", parent.getItemAtPosition(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        numberOfQuestionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(SettingActivity.this, numberOfQuestionSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putString("number_of_questions", parent.getItemAtPosition(position).toString());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



    }
    public void onGamePanel(View fromView) {
        Intent myIntent = new Intent(SettingActivity.this, GamePanelActivity.class);
        SettingActivity.this.startActivity(myIntent);
    }
    private int getProfileIndex(String[] profiles) {

        final String profileName = MainActivity.getProfile(this);

        int i = 0;
        for (String profile : profiles) {
            if (profile.equals(profileName))
                break;
            else
                i++;

        }
        return i;
    }



    private int getQuestionTypeIndex(String[] questionTypes) {
        final String questionType = MainActivity.getQuestionType(this);
        int i = 0;
        for (String type : questionTypes) {
            if (questionType.equals(type)) {
                break;
            } else {
                i++;
            }
        }
        return i;
    }




    private int getNumberOfQuestionIndex(String[] numberOfQuestionsArray) {
        final String numberOfQuestions = MainActivity.getNumberOfQuestions(this);
        int i = 0;
        for (String number : numberOfQuestionsArray) {
            if (number.equals(numberOfQuestions)) {
                break;
            } else {
                i++;
            }
        }
        return i;
    }


}
