package konnov.commr.vk.calculator;

import org.mariuszgromada.math.mxparser.*;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


class Solver {
    private TextView outputTextView;
    private StringBuilder inputString = new StringBuilder();
    private enum Signs {PLUS, MINUS, MULTIPLY, DIVIDE}
    private StringBuilder eachNumberString =  new StringBuilder();
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
                    if(isFirstCharValid('1')) {
                        inputString.append("1");
                        eachNumberString.append("1");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_two:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('2')) {
                        inputString.append("2");
                        eachNumberString.append("2");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_three:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('3')) {
                        inputString.append("3");
                        eachNumberString.append("3");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_four:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('4')) {
                        inputString.append("4");
                        eachNumberString.append("4");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_five:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('5')) {
                        inputString.append("5");
                        eachNumberString.append("5");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_six:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('6')) {
                        inputString.append("6");
                        eachNumberString.append("6");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_seven:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('7')) {
                        inputString.append("7");
                        eachNumberString.append("7");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_eight:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('8')) {
                        inputString.append("8");
                        eachNumberString.append("8");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_nine:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('9')) {
                        inputString.append("9");
                        eachNumberString.append("9");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_zero:
                    if(isNumberTooLarge())
                        return;
                    if(isFirstCharValid('0')) {
                        inputString.append("0");
                        eachNumberString.append("0");
                        calculatorState = 0;
                    }
                    outputString();
                    break;
                case R.id.button_dot:
                    if(isNumberTooLarge())
                        return;
                    if (!eachNumberString.toString().contains(".") && !eachNumberString.toString().isEmpty() && calculatorState == 0) {
                        inputString.append(".");
                        eachNumberString.append(".");
                        outputString();
                    }
                    break;
                case R.id.button_plus:
                    if(inputString.toString().endsWith("."))
                        inputString.append("0");
                    outputString();
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString.append(" + ");
                        eachNumberString.setLength(0);
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_minus:
                    if(inputString.toString().endsWith("."))
                        inputString.append("0");;
                    outputString();
                    if(inputString.toString().isEmpty() || inputString.toString().endsWith("(")){
                        inputString.append("-");
                        outputString();
                        calculatorState = 1;
                    }
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString.append(" - ");
                        eachNumberString.setLength(0);
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_divide:
                    if(inputString.toString().endsWith("."))
                        inputString.append("0");;
                    outputString();
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString.append(" / ");
                        eachNumberString.setLength(0);
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_multiply:
                    if(inputString.toString().endsWith("."))
                        inputString.append("0");
                    outputString();
                    if(calculatorState == 0 || calculatorState == 2) {
                        inputString.append(" * ");;
                        eachNumberString.setLength(0);
                        outputString();
                        calculatorState = 1;
                    }
                    break;
                case R.id.button_pi:
                    if(inputString.toString().endsWith("."))
                        inputString.append("0 * ");
                    outputString();
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("3.14159265359");
                    outputString();
                    calculatorState = 0;
                    break;
                case R.id.button_ln:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("ln(");
                    outputString();
                    break;
                case R.id.button_pow:
                    if(calculatorState == 0){
                        inputString.append("^");
                        outputString();
                    }
                    break;
                case R.id.button_sqrt:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("sqrt(");
                    outputString();
                    break;
                case R.id.button_factorial:
                    inputString.append("!");
                    outputString();
                    break;
                case R.id.button_sin:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("sin(");
                    outputString();
                    break;
                case R.id.button_cos:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("cos(");
                    outputString();
                    break;
                case R.id.button_tan:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("tan(");
                    outputString();
                    break;
                case R.id.button_ctan:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("ctan(");
                    outputString();
                    break;
                case R.id.button_arcsin:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("arcsin(");
                    outputString();
                    break;
                case R.id.button_arccos:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("arccos(");
                    outputString();
                    break;
                case R.id.button_arctan:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("arctan(");
                    outputString();
                    break;
                case R.id.button_arcctan:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append("arcctan(");
                    outputString();
                    break;
                case R.id.button_bracket_left:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    if(calculatorState == 0)
                        return;
                    inputString.append("(");
                    outputString();
                    break;
                case R.id.button_bracket_right:
                    if(calculatorState == 2)
                        eachNumberString.setLength(0);
                    inputString.append(")");
                    outputString();
                    calculatorState = 0;
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
        inputString.replace(0, inputString.length(), inputString.toString().replaceAll("\\.[^1-9]", ".0"));// ensuring there isn't gonna be a number ending with dot with no number after it       if(!inputString.contains("("))
//            SolveNoBracketsAlgorithm();
//        else
          SolveAlgorithnWithBrackets();
    }


    private void SolveAlgorithnWithBrackets() { //solving via math parser library
        //inputString = "(22 - 6) / (2 - 10 + 50) * (50 * sin(90) - (3^5 + sqrt(81)) - ln(16) + 9^9) + 8!";
        StringBuilder historyString = new StringBuilder(inputString.toString());
        Expression expression = new Expression(inputString.toString());
        double answer = expression.calculate();
        if(answer%1 == 0) {
            outputTextView.setText(String.format("%.0f", answer));
            historyString.append(" = ").append(String.valueOf(String.format("%.0f", answer)));
            inputString.replace(0, inputString.length(), String.format("%.0f", answer));
        }
        else {
            outputTextView.setText("" + answer);
            historyString.append(" = ").append(String.valueOf(answer));
            inputString.replace(0, inputString.length(), String.valueOf(answer));
        }
        calculatorState = 2;
        dbHelper.insertData(historyString.toString());
    }


    private void SolveNoBracketsAlgorithm(){ //my own calculating algorithm. It's no longer used
        StringBuilder historyString = inputString;
        boolean startAgain = false;

        Signs[] sign;
        double[] number;

        String[] stringForEachElement = inputString.toString().split(" ");
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
            historyString.append(" = ").append(String.valueOf(String.format("%.0f", number[0])));
            inputString.replace(0, inputString.length(), String.format("%.0f", number[0]));
        }
        else {
            outputTextView.setText(String.valueOf(number[0]));
            historyString.append(" = ").append(String.valueOf(number[0]));
            inputString = inputString.replace(0, inputString.length(),String.valueOf(number[0]));
        }
        calculatorState = 2;
        dbHelper.insertData(historyString.toString());

    }




    private void outputString(){
        outputTextView.setText(inputString);

    }



    private void clearAll(){
        inputString.setLength(0);
        eachNumberString.setLength(0);
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



    private boolean isFirstCharValid(char characterForZeroFirstCharacterCondition){ // method that makes sure that the first digit in the number isn't 0 and if it is we replace zero with the number we pass in the method argument
        if(calculatorState == 2){ //check if query was already calculated so we can clear it
            clearAll(); //clear all if the user clicked any number after he got the answer
        }

        if(eachNumberString.length() == 1 && eachNumberString.charAt(0) == '0'){ // check if we input zero as the first character
            eachNumberString.replace(0, eachNumberString.length(), String.valueOf(characterForZeroFirstCharacterCondition));
            inputString.setCharAt(inputString.length(), characterForZeroFirstCharacterCondition); //replace zero with the character we pass
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
            if(inputString.toString().contains("NaN"))
                inputString.setLength(0);
            else if (inputString.charAt(inputString.length()-1) == ' '){
                inputString.delete(inputString.length() - 3, inputString.length()); //removing three last characters in the inputString in case the back button was clicked on " + " or " - " or " * " or " / "
                eachNumberString.replace(0, eachNumberString.length(), getTheLastNumberInString(inputString.toString()));
                calculatorState = 0;
            }
            else if(inputString.toString().endsWith("arcctan("))
                inputString.delete(inputString.lastIndexOf("arcctan("), inputString.length());

            else if(inputString.toString().endsWith("arctan("))
                inputString.delete(inputString.lastIndexOf("arctan("), inputString.length());
            else if(inputString.toString().endsWith("arccos("))
                inputString.delete(inputString.lastIndexOf("arccos("), inputString.length());
            else if(inputString.toString().endsWith("arcsin("))
                inputString.delete(inputString.lastIndexOf("arcsin("), inputString.length());

            else if(inputString.toString().endsWith("sqrt("))
                inputString.delete(inputString.lastIndexOf("sqrt("), inputString.length());
            else if(inputString.toString().endsWith("ctan("))
                inputString.delete(inputString.lastIndexOf("ctan("), inputString.length());
            else if(inputString.toString().endsWith("ln("))
                inputString.delete(inputString.lastIndexOf("ln("), inputString.length());

            else if (inputString.toString().endsWith("sin("))
                inputString.delete(inputString.lastIndexOf("sin("), inputString.length());
            else if (inputString.toString().endsWith("cos("))
                inputString.delete(inputString.lastIndexOf("cos("), inputString.length());
            else if (inputString.toString().endsWith("tan("))
                inputString.delete(inputString.lastIndexOf("tan("), inputString.length());

            else {
                inputString.deleteCharAt(inputString.length()); //removing one digit of a number
                eachNumberString.replace(0, eachNumberString.length(), getTheLastNumberInString(inputString.toString()));
                if (inputString.charAt(inputString.length() - 1) == ' ') {
                    eachNumberString.setLength(0);
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
        this.eachNumberString.replace(0, eachNumberString.length(), eachNumberString);
        this.inputString.replace(0, inputString.length(), inputString);
        this.calculatorState = calculatorState;
        outputTextView.setText(inputString);
    }

    public ArrayList<Object> getValuesBeforeRotation(){
        ArrayList<Object> listOfValues = new ArrayList<>();
        listOfValues.add(eachNumberString.toString());
        listOfValues.add(inputString.toString());
        listOfValues.add(calculatorState);
        return listOfValues;
    }

}


































//    private void SolveAlgorithnWithBrackets() {
//        System.out.println("private void SolveAlgorithnWithBrackets() {");
//        System.out.println("THE STRING IS " + inputString);
//        String historyString = inputString;
//        // create a script engine manager
//        ScriptEngineManager factory = new ScriptEngineManager();
//        // create a JavaScript engine
//        ScriptEngine engine = factory.getEngineByName("JavaScript");
//        inputString = inputString.replaceAll("sin", "Math.sin").
//                replaceAll("cos", "Math.cos").
//                replaceAll("tan", "Math.tan").
//                replaceAll("sqrt", "Math.sqrt").
//                replaceAll("pow", "Math.pow").
//                replaceAll("log", "Math.log");
//        Integer integerAnswer = null;
//        Double doubleAnswer = null;
//
//        try {
//            Object answer = engine.eval(inputString);
//            if (answer instanceof Integer)
//                integerAnswer = (Integer) answer;
//            if (answer instanceof Double)
//                doubleAnswer = (Double) answer;
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//        if (integerAnswer != null) {
//            outputTextView.setText(integerAnswer);
//            historyString = historyString + " = " + String.valueOf(integerAnswer);
//            inputString = String.valueOf(integerAnswer);
//        }
//        if(doubleAnswer != null) {
//            outputTextView.setText(String.valueOf(doubleAnswer));
//            historyString = historyString + " = " + String.valueOf(doubleAnswer);
//            inputString = String.valueOf(doubleAnswer);
//        }
//        calculatorState = 2;
//        dbHelper.insertData(historyString);
//
//    }


