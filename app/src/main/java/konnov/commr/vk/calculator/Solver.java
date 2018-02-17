package konnov.commr.vk.calculator;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Solver {
    private TextView outputTextView;
    private String inputString = "";
    private enum Signs {PLUS, MINUS, MULTIPLY, DIVIDE}
    private String eachNumberString = "";
    private HistoryFragment historyFragment;
    private DBHelper dbHelper;
    private Activity mainActivity;
    private int calculatorState = 1; // 0 - number clicked, 1 - operation clicked, 2 - query was calculated
    private Toast toast;


    Solver(Activity activity, HistoryFragment historyFragment, DBHelper dbHelper){
        this.historyFragment = historyFragment;
        this.dbHelper = dbHelper;
        mainActivity = activity;
        outputTextView = (TextView) activity.findViewById(R.id.output);
    }

    void button_clicked(View v){
        try {
            switch (v.getId()) {
                case R.id.button_one:
                    if(isNumberTooLarge()) //check if the number didn't exceeded 18 digits
                        return;
                    if(isFirstCharValid("1")) {
                        inputString = inputString + "1";
                        eachNumberString = eachNumberString + "1";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_two:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("2")) {
                        inputString = inputString + "2";
                        eachNumberString = eachNumberString + "2";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_three:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("3")) {
                        inputString = inputString + "3";
                        eachNumberString = eachNumberString + "3";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_four:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("4")) {
                        inputString = inputString + "4";
                        eachNumberString = eachNumberString + "4";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_five:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("5")) {
                        inputString = inputString + "5";
                        eachNumberString = eachNumberString + "5";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_six:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("6")) {
                        inputString = inputString + "6";
                        eachNumberString = eachNumberString + "6";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_seven:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("7")) {
                        inputString = inputString + "7";
                        eachNumberString = eachNumberString + "7";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_eight:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("8")) {
                        inputString = inputString + "8";
                        eachNumberString = eachNumberString + "8";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_nine:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("9")) {
                        inputString = inputString + "9";
                        eachNumberString = eachNumberString + "9";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_zero:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid("0")) {
                        inputString = inputString + "0";
                        eachNumberString = eachNumberString + "0";
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_dot:
                    if(isNumberTooLarge())
                        return;
                    if (!eachNumberString.contains(".") && !eachNumberString.isEmpty() && calculatorState == 0) {
                        inputString = inputString + ".";
                        eachNumberString = eachNumberString + ".";
                        outputString();
                    }
                    break;
                case R.id.button_plus:
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString = inputString + " + ";
                        eachNumberString = "";
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_minus:
                    if(inputString.isEmpty()){
                        inputString = inputString + "-";
                        outputString();
                        calculatorState = 1;
                    }
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString = inputString + " - ";
                        eachNumberString = "";
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_divide:
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString = inputString + " / ";
                        eachNumberString = "";
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_multiply:
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString = inputString + " * ";
                        eachNumberString = "";
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_bracket_left:
                    inputString = inputString + "(";
                    outputString();
                    break;
                case R.id.button_bracket_right:
                    inputString = inputString + ")";
                    outputString();
                    break;
                case R.id.button_clear:
                    clearAll();
                    break;
                case R.id.button_equals:
                    if(calculatorState == 0)
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
        if(!inputString.contains("("))
            SolveNoBracketsAlgorithm();
        else
            SolveAlgorithnWithBrackets();
    }


    private void SolveAlgorithnWithBrackets(){
        // TODO algorithm with brackets
    }

    private void SolveNoBracketsAlgorithm(){
        String historyString = inputString;
        boolean startAgain = false;

        Signs[] sign;
        double[] number;

        String[] stringForEachElement = inputString.split(" ");
        sign = new Signs[stringForEachElement.length];
        number = new double[stringForEachElement.length];
        int signIndex = 0;
        int numberIndex = 0;
        for (String element : stringForEachElement) {
            switch (element) {
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
                    number[numberIndex] = Double.parseDouble(element);
                    numberIndex++;
                    break;
            }
        }

        int numberOfOperations = stringForEachElement.length/2;

        for(int i = 0; i <= numberOfOperations; i++){
            if(startAgain) {
                i = 0;
                startAgain = false;
            }
            if(sign[i] == Signs.DIVIDE) {
                number[i] = number[i] / number[i+1];
                for(int j = i; j<numberOfOperations; j++) {
                    if(j+2 <= numberOfOperations) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                numberOfOperations--;
                startAgain = true;
            }
            else if (sign[i] == Signs.MULTIPLY) {
                number[i] = number[i] * number[i+1];
                for(int j = i; j<numberOfOperations; j++) {
                    if(j+2 <= numberOfOperations) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                numberOfOperations--;
                startAgain = true;
            }
        }
        startAgain = false;

        for(int i = 0; i <= numberOfOperations; i++){
            if(startAgain) {
                i = 0;
                startAgain = false;
            }
            if(sign[i] == Signs.MINUS) {
                number[i] = number[i] - number[i+1];
                for(int j = i; j<numberOfOperations; j++) {
                    if(j+2 <= numberOfOperations) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                numberOfOperations--;
                startAgain = true;
            }
            else if(sign[i] == Signs.PLUS) {
                number[i] = number[i] + number[i+1];

                for(int j = i; j<numberOfOperations; j++) {
                    if(j+2 <= numberOfOperations) {
                        sign[j] = sign[j + 1];
                        number[j + 1] = number[j + 2];
                    }
                }
                numberOfOperations--;
                startAgain = true;
            }
        }
        if(number[0]%1 == 0) {
            outputTextView.setText(String.format("%.0f", number[0]));
            historyString = historyString + " = " + String.valueOf(String.format("%.0f", number[0]));
            inputString = String.format("%.0f", number[0]);
        }
        else {
            outputTextView.setText(String.valueOf(number[0]));
            historyString = historyString + " = " + String.valueOf(number[0]);
            inputString = String.valueOf(number[0]);
        }
        calculatorState = 2;
        dbHelper.insertData(historyString);

    }




    private void outputString(){
        outputTextView.setText(inputString);

    }



    private void clearAll(){
        inputString = "";
        eachNumberString = "";
        outputTextView.setText("");
        calculatorState = 1;
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
        if(calculatorState == 2){ //check if query was already calculated so we can clear it
            clearAll(); //clear all if the user clicked any number after he got the answer
        }

        if(eachNumberString.length() == 1 && eachNumberString.charAt(0) == '0'){ // check if we input zero as the first character
            eachNumberString = characterForZeroFirstCharacterCondition;
            inputString = inputString.substring(0, inputString.length()-1) + characterForZeroFirstCharacterCondition; //replace zero with the character we pass
            calculatorState = 0;
            return false;
        }
        else
            return true;
    }




    private void buttonBackClicked(){
        if(inputString.length() == 1 || (inputString.length() == 2 && inputString.charAt(0) =='-')) {
            clearAll();
            outputString();
            return;
        }
        if(inputString.length()>0) {
            if (inputString.charAt(inputString.length()-1) == ' '){
                inputString = inputString.substring(0, inputString.length() - 3); //removing three last characters in the inputString in case the back button was clicked on " + " or " - " or " * " or " / "
                eachNumberString = getTheLastNumberInString(inputString);
                calculatorState = 0;
            }
            else{
                inputString = inputString.substring(0, inputString.length() - 1); //removing one digit of a number
                eachNumberString = getTheLastNumberInString(inputString);
                if(inputString.charAt(inputString.length()-1) == ' '){
                    eachNumberString = "";
                    calculatorState = 1;
                }
            }
        }
        outputString();
    }

    private String getTheLastNumberInString(String str){
        String number[] = str.split(" ");
        return number[number.length-1];
    }


    private boolean isNumberTooLarge(){
        if(eachNumberString.length()>=18){
            if(toast != null)
                toast.cancel();
            toast = Toast.makeText(mainActivity, R.string.number_too_large_warning, Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        return false;
    }

    public void setValuesAfterRotation(String eachNumberString, String inputString, int calculatorState){ //update the value after device was rotated
        this.eachNumberString = eachNumberString;
        this.inputString = inputString;
        this.calculatorState = calculatorState;
        outputTextView.setText(inputString);
    }

    public ArrayList<Object> getValuesBeforeRotation(){
        ArrayList<Object> listOfValues = new ArrayList<>();
        listOfValues.add(eachNumberString);
        listOfValues.add(inputString);
        listOfValues.add(calculatorState);
        return listOfValues;
    }

}


