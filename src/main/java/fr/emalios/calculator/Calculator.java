package fr.emalios.calculator;

public class Calculator {

    public String add(String number) {
        if(number.isEmpty()) {
            return "0";
        }
        int result = 0;
        String[] newlineSplit = number.split("\n");
        for (String part : newlineSplit) {
            String[] split = part.split(",");
            for (String s : split) {
                result += Integer.parseInt(s);
            }
        }
        return String.valueOf(result);
    }

}
