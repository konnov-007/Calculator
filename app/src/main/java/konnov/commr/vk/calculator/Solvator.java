package konnov.commr.vk.calculator;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Solvator {
    private byte number_of_possible_elements = 40;
    private TextView output;
    private String string = "";
    private enum Signs {NONE, PLUS, MINUS, MULTIPLY, DIVIDE}
    private Signs[] sign;
    private double[] number;
    private int i = 0;
    private String temporalString = "";
    private boolean previousQueryCalculated = false;
    private HistoryFragment historyFragment;
    private String historyString;
    private DBHelper dbHelper;
    private Activity mainActivity;

    Solvator(Activity activity, HistoryFragment historyFragment, DBHelper dbHelper){
        this.historyFragment = historyFragment;
        this.dbHelper = dbHelper;
        sign = new Signs[number_of_possible_elements];
        mainActivity = activity;
        output = (TextView) activity.findViewById(R.id.output);
        number = new double[number_of_possible_elements];
    }

    public void button_clicked(View v){
        try {
            switch (v.getId()) {
                case R.id.button_one:
                    if(isFirstCharValid("1")) {
                        string = string + "1";
                        temporalString = temporalString + "1";
                    }
                    outputInput();
                    break;
                case R.id.button_two:
                    if(isFirstCharValid("2")) {
                        string = string + "2";
                        temporalString = temporalString + "2";
                    }
                    outputInput();
                    break;
                case R.id.button_three:
                    if(isFirstCharValid("3")) {
                        string = string + "3";
                        temporalString = temporalString + "3";
                    }
                    outputInput();
                    break;
                case R.id.button_four:
                    if(isFirstCharValid("4")) {
                        string = string + "4";
                        temporalString = temporalString + "4";
                    }
                    outputInput();
                    break;
                case R.id.button_five:
                    if(isFirstCharValid("5")) {
                        string = string + "5";
                        temporalString = temporalString + "5";
                    }
                    outputInput();
                    break;
                case R.id.button_six:
                    if(isFirstCharValid("6")) {
                        string = string + "6";
                        temporalString = temporalString + "6";
                    }
                    outputInput();
                    break;
                case R.id.button_seven:
                    if(isFirstCharValid("7")) {
                        string = string + "7";
                        temporalString = temporalString + "7";
                    }
                    outputInput();
                    break;
                case R.id.button_eight:
                    if(isFirstCharValid("8")) {
                        string = string + "8";
                        temporalString = temporalString + "8";
                    }
                    outputInput();
                    break;
                case R.id.button_nine:
                    if(isFirstCharValid("9")) {
                        string = string + "9";
                        temporalString = temporalString + "9";
                    }
                    outputInput();
                    break;
                case R.id.button_zero:
                    if(isFirstCharValid("0")) {
                        string = string + "0";
                        temporalString = temporalString + "0";
                    }
                    outputInput();
                    break;
                case R.id.button_dot:
                    if(previousQueryCalculated){
                        previousQueryCalculated = false;
                        clearAll();
                    }
                    if (!temporalString.contains(".") && !temporalString.isEmpty()) {
                        string = string + ".";
                        temporalString = temporalString + ".";
                        outputInput();
                    }
                    break;
                case R.id.button_plus:
                    if(previousQueryCalculated)
                        previousQueryCalculated = false;
                    number[i] = Double.parseDouble(temporalString);
                    sign[i] = Signs.PLUS;
                    string = string + " + ";
                    i++;
                    temporalString = "";
                    outputInput();
                    break;
                case R.id.button_minus:
                    if(previousQueryCalculated)
                        previousQueryCalculated = false;
                    number[i] = Double.parseDouble(temporalString);
                    sign[i] = Signs.MINUS;
                    string = string + " - ";
                    i++;
                    temporalString = "";
                    outputInput();
                    break;
                case R.id.button_divide:
                    if(previousQueryCalculated)
                        previousQueryCalculated = false;
                    number[i] = Double.parseDouble(temporalString);
                    sign[i] = Signs.DIVIDE;
                    string = string + " / ";
                    i++;
                    temporalString = "";
                    outputInput();
                    break;
                case R.id.button_multiply:
                    if(previousQueryCalculated)
                        previousQueryCalculated = false;
                    number[i] = Double.parseDouble(temporalString);
                    sign[i] = Signs.MULTIPLY;
                    string = string + " * ";
                    i++;
                    temporalString = "";
                    outputInput();
                    break;
                case R.id.button_clear:
                    clearAll();
                    break;
                case R.id.button_equals:
                    number[i] = Double.parseDouble(temporalString);
                    solve();
                    break;
                case R.id.button_back:
                    buttonBackClicked();
                    break;
                case R.id.button_history:
                    disableButtons();
                    historyFragment.update();
                    break;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void solve() {
        historyString = string;
        boolean startAgain = false;

        Signs[] sign;
        double[] number;

        String[] stringForEachElement = string.split(" ");
        sign = new Signs[stringForEachElement.length];
        number = new double[stringForEachElement.length];
        int signIndex = 0;
        int numberIndex = 0;
        for(int i = 0; i < stringForEachElement.length; i++){
            switch (stringForEachElement[i]) {
                case "+":
                    sign[signIndex] = Signs.PLUS;
                    signIndex++;
                    break;
                case "-":
                    sign[signIndex] = Signs.MINUS;
                    signIndex++;
                    break;
                case "*":
                    sign[signIndex] = Signs.MULTIPLY;
                    signIndex++;
                    break;
                case "/":
                    sign[signIndex] = Signs.DIVIDE;
                    signIndex++;
                    break;
                default:
                    number[numberIndex] = Double.parseDouble(stringForEachElement[i]);
                    numberIndex++;
                    break;
            }
        }





        for(int i = 0; i <= this.i; i++){
            if(startAgain) {
                i = 0;
                startAgain = false;
            }
            if(sign[i] == Signs.DIVIDE) {
                number[i] = number[i] / number[i+1];
                for(int j = i; j<this.i; j++) {
                    if(j+2 <= this.i) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                this.i--;
                startAgain = true;
            }
            else if (sign[i] == Signs.MULTIPLY) {
                number[i] = number[i] * number[i+1];
                for(int j = i; j<this.i; j++) {
                    if(j+2 <= this.i) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                this.i--;
                startAgain = true;
            }
        }
        startAgain = false;

        for(int i = 0; i <= this.i; i++){
            if(startAgain) {
                i = 0;
                startAgain = false;
            }
            if(sign[i] == Signs.MINUS) {
                number[i] = number[i] - number[i+1];
                for(int j = i; j<this.i; j++) {
                    if(j+2 <= this.i) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                this.i--;
                startAgain = true;
            }
            else if(sign[i] == Signs.PLUS) {
                number[i] = number[i] + number[i+1];

                for(int j = i; j<this.i; j++) {
                    if(j+2 <= this.i) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                this.i--;
                startAgain = true;
            }
        }
        if(number[0]%1 == 0) {
            output.setText(String.format("%.0f", number[0]));
            historyString = historyString + " = " + String.valueOf(String.format("%.0f", number[0]));
            string = String.format("%.0f", number[0]);
        }
        else {
            output.setText(String.valueOf(number[0]));
            historyString = historyString + " = " + String.valueOf(number[0]);
            string = String.valueOf(number[0]);
        }
        previousQueryCalculated = true;
        dbHelper.insertData(historyString);
        historyString = "";
    }




    private void outputInput(){
        output.setText(string);

    }



    private void clearAll(){
        string = "";
        temporalString = "";
        output.setText("");
        for(int i = 0; i < number_of_possible_elements; i++)
            number[i] = 0;
        for(int i = 0; i < number_of_possible_elements; i++)
            sign[i] = Signs.NONE;
        i = 0;
    }




    private void disableButtons(){
        Button buttonone = (Button) mainActivity.findViewById(R.id.button_one);
        Button buttontwo = (Button) mainActivity.findViewById(R.id.button_two);
        Button buttonthree = (Button) mainActivity.findViewById(R.id.button_three);
        Button buttonfour = (Button) mainActivity.findViewById(R.id.button_four);
        Button buttonfive = (Button) mainActivity.findViewById(R.id.button_five);
        Button buttonsix = (Button) mainActivity.findViewById(R.id.button_six);
        Button buttonseven = (Button) mainActivity.findViewById(R.id.button_seven);
        Button buttoneight = (Button) mainActivity.findViewById(R.id.button_eight);
        Button buttonnine = (Button) mainActivity.findViewById(R.id.button_nine);
        Button buttonzero = (Button) mainActivity.findViewById(R.id.button_zero);
        Button buttondot = (Button) mainActivity.findViewById(R.id.button_dot);
        Button buttonplus = (Button) mainActivity.findViewById(R.id.button_plus);
        Button buttonminus = (Button) mainActivity.findViewById(R.id.button_minus);
        Button buttonmult = (Button) mainActivity.findViewById(R.id.button_multiply);
        Button buttondiv = (Button) mainActivity.findViewById(R.id.button_divide);

        if(buttonone.getVisibility() == View.VISIBLE) {
            buttonone.setVisibility(View.INVISIBLE);
            buttontwo.setVisibility(View.INVISIBLE);
            buttonthree.setVisibility(View.INVISIBLE);
            buttonfour.setVisibility(View.INVISIBLE);
            buttonfive.setVisibility(View.INVISIBLE);
            buttonsix.setVisibility(View.INVISIBLE);
            buttonseven.setVisibility(View.INVISIBLE);
            buttoneight.setVisibility(View.INVISIBLE);
            buttonnine.setVisibility(View.INVISIBLE);
            buttonzero.setVisibility(View.INVISIBLE);
            buttondot.setVisibility(View.INVISIBLE);
            buttonplus.setVisibility(View.INVISIBLE);
            buttonminus.setVisibility(View.INVISIBLE);
            buttonmult.setVisibility(View.INVISIBLE);
            buttondiv.setVisibility(View.INVISIBLE);
        }
        else{
            buttonone.setVisibility(View.VISIBLE);
            buttontwo.setVisibility(View.VISIBLE);
            buttonthree.setVisibility(View.VISIBLE);
            buttonfour.setVisibility(View.VISIBLE);
            buttonfive.setVisibility(View.VISIBLE);
            buttonsix.setVisibility(View.VISIBLE);
            buttonseven.setVisibility(View.VISIBLE);
            buttoneight.setVisibility(View.VISIBLE);
            buttonnine.setVisibility(View.VISIBLE);
            buttonzero.setVisibility(View.VISIBLE);
            buttondot.setVisibility(View.VISIBLE);
            buttonplus.setVisibility(View.VISIBLE);
            buttonminus.setVisibility(View.VISIBLE);
            buttonmult.setVisibility(View.VISIBLE);
            buttondiv.setVisibility(View.VISIBLE);
        }
    }



    private boolean isFirstCharValid(String characterForZeroFirstCharacterCondition){ // method that makes sure that the first digit in the number isn't 0 and if it is we replace zero with the number we pass in the method argument
        if(previousQueryCalculated){ //check if query was already calculated so we can clear it
            previousQueryCalculated = false;
            clearAll(); //clear all if the user clicked any number after he got the answer
        }

        if(temporalString.length() == 1 && temporalString.charAt(0) == '0'){ // check if we input zero as the first character
            temporalString = characterForZeroFirstCharacterCondition;
            string = string.substring(0, string.length()-1) + characterForZeroFirstCharacterCondition; //replace zero with the character we pass
            return false;
        }
        else
            return true;
    }




    private void buttonBackClicked(){
        if(string != null && string.length()>0) {
            if (string.indexOf("*") == string.length() - 2 || string.indexOf("/") == string.length() - 2 || string.indexOf("-") == string.length() - 2 || string.indexOf("+") == string.length() - 2)
                string = string.substring(0, string.length() - 3); //removing three last characters in the string in case the back button was clicked on " + " or " - " or " * " or " / "
            else{
                if(string.length() == 1)
                    string = ""; //assigning the string to empty if it contained only one character prior to clicking the back button
                else
                    string = string.substring(0, string.length() - 1); //removing one digit of a number
                if (string.indexOf("*") == string.length() - 1 || string.indexOf("/") == string.length() - 1 || string.indexOf("-") == string.length() - 1 || string.indexOf("+") == string.length() - 1)
                    string = string + " "; //give it some space if the last deleted number was just before the sign
            }
        }
        outputInput();
    }

}


