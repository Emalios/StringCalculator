package fr.emalios.calculator;

import java.util.Arrays;
import java.util.List;

public class Calculator {

    public String add(String number) {
        if(number.isEmpty()) {
            return "0";
        }
        int result = 0;
        String error = validate(number);
        String separator = ",";

        if (number.startsWith("//")){
            separator = (number.substring(number.indexOf("//")+2,number.indexOf("\n")));
            List<String> regexArray = Arrays.asList("|", "?", "+", "\\", ".", "^", "[", "]", "$", "&", ".");

            if (regexArray.contains(separator)){
                separator=("\\"+separator);
            }

            number = number.substring(number.indexOf("\n")+1);
        }

        if (error.equals("")){
            String[] newlineSplit = number.split("\n");

            for (String part : newlineSplit) {
                String[] split = part.split(separator);
                result += Arrays.stream(split).mapToInt(Integer::parseInt).sum();
            }

            return String.valueOf(result);
        } else {
            return error;
        }
    }

    private String validate(String number){
        StringBuilder error = new StringBuilder();

        if (number.contains(",\n")){
            if (error.length() > 0) error.append("\n");
            error.append("Number expected but '\\n' found at position ").append(number.indexOf(",\n"));
        }

        if (number.endsWith(",")){
            if (error.length() > 0) error.append("\n");
            error.append("Number expected but EOF found.");
        }

        if (number.contains("-")){
            if (error.length() > 0) error.append("\n");
            error.append(detectNegative(number));
        }

        if (number.contains(",,")){
            if (error.length() > 0) error.append("\n");
            error.append("Number expected but ',' found at position ").append(number.indexOf(",,")+1).append("");
        }

        return error.toString();
    }

    private String detectNegative(String number){
        String numberVal = number;
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index != -1){
            index = numberVal.indexOf("-");
            if (index != -1){

                String value1 = String.valueOf(numberVal.charAt(index));
                String value2 = String.valueOf(numberVal.charAt(index+1));

                if (result.length() > 0){
                    result.append(", ").append(value1).append(value2);
                } else {
                    result.append(value1).append(value2);
                }
                index++;
                numberVal = numberVal.substring(index);
            }
        }
        if (result.length() != 0) {
            result.insert(0, "Negative not allowed : ");
        }
        return result.toString();
    }

}
