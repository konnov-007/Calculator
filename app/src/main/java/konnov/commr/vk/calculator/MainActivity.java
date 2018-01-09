package konnov.commr.vk.calculator;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Solvator solvator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //passing the activity, output text id, number of possible elements, the fragment and a dbhelper class
        solvator = new Solvator(this,
                (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment), new DBHelper(this));
    }


    public void onClick(View v){
        solvator.button_clicked(v);
    }


}
