package konnov.commr.vk.calculator;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Solver solver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //passing the activity, the fragment and a dbhelper class
        solver = new Solver(this,
                (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment), new DBHelper(this));

        if(savedInstanceState != null){
            solver.setValuesAfterRotation(savedInstanceState.getString("eachNumberString"),
                    savedInstanceState.getString("inputString"), savedInstanceState.getInt("calculatorState"));
        }

    }

    public void onClick(View v){ //if any button was clicked
        solver.button_clicked(v);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<Object> listOfValuesToSave = solver.getValuesBeforeRotation();
        outState.putString("eachNumberString", (String)listOfValuesToSave.get(0));
        outState.putString("inputString", (String)listOfValuesToSave.get(1));
        outState.putInt("calculatorState", (Integer)listOfValuesToSave.get(2));
    }

}
