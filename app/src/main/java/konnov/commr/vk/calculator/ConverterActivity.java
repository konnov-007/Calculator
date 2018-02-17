package konnov.commr.vk.calculator;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
// TODO CONVERTER
public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner converterSpinnerDialogue;
    ArrayAdapter converterArrayAdapter;
    ArrayAdapter spinnerSideAdapter;
    Spinner spinnerLeftSide;
    Spinner spinnerRightSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        converterSpinnerDialogue = (Spinner) findViewById(R.id.converterSpinner);
        spinnerLeftSide = (Spinner) findViewById(R.id.spinnerLeftSide);
        spinnerRightSide = (Spinner) findViewById(R.id.spinnerRightSide);

        //String[] spinnerOptions = getResources().getStringArray(R.array.converter_options);
        converterArrayAdapter = ArrayAdapter.createFromResource(this, R.array.converter_options, R.layout.spinner_layout);
        spinnerSideAdapter = ArrayAdapter.createFromResource(this, R.array.currency_options, android.R.layout.simple_spinner_dropdown_item);
        //        assert converterSpinnerDialogue != null;
        spinnerLeftSide.setAdapter(spinnerSideAdapter);
        spinnerRightSide.setAdapter(spinnerSideAdapter);
        spinnerRightSide.setSelection(1);

        converterSpinnerDialogue.setAdapter(converterArrayAdapter);
        converterSpinnerDialogue.setOnItemSelectedListener(ConverterActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinnerDialogueText = (TextView) view;
        //Toast.makeText(this, "you selected " + spinnerDialogueText.getText(), Toast.LENGTH_SHORT).show();
        updateSpinners(spinnerDialogueText);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Toast.makeText(this, "you selected " + getResources().getStringArray(R.array.converter_options)[0], Toast.LENGTH_SHORT).show();
    }


    private void updateSpinners(TextView spinnerDialogueText){
        if(spinnerDialogueText.getText() == getResources().getStringArray(R.array.converter_options)[0])
            Toast.makeText(this, "you selected " + getResources().getStringArray(R.array.converter_options)[0], Toast.LENGTH_SHORT).show();
    }



}
