package konnov.commr.vk.calculator;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final byte NUMBER_OF_POSSIBLE_ELEMENTS = 40;
    private Solvator solvator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonBack = (Button) findViewById(R.id.button_back);
        buttonBack.setEnabled(false); // TODO back button
        //passing the activity, output text id, number of possible elements, the fragment and a dbhelper class
        solvator = new Solvator(this, (TextView) findViewById(R.id.output), NUMBER_OF_POSSIBLE_ELEMENTS,
                (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment), new DBHelper(this));
    }


    public void onClick(View v){
        solvator.button_clicked(v);
    }


}
