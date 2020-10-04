package fr.emalios.calculator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {

    public String add(String number) {
        if(number.isEmpty()) {
            return "0";
        }
        int result = 0;
        String error=validate(number);
        String sep = ",";
        if (number.startsWith("//")){
            sep = (number.substring(number.indexOf("//")+2,number.indexOf("\n")));
            List<String> regexArray = Arrays.asList("|", "?", "+", "\\", ".", "^", "[", "]", "$", "&", ".");
            if (regexArray.contains(sep)){
                sep=("\\"+sep);
            }
            number = number.substring(number.indexOf("\n")+1);
        }
        if (error.equals("")){
            String[] newlineSplit = number.split("\n");
            for (String part : newlineSplit) {
                String[] split = part.split(sep);
                result += Arrays.stream(split).mapToInt(Integer::parseInt).sum();
            }
            return String.valueOf(result);
        } else {
            return error;
        }
    }

    private String validate(String number){
        StringBuilder error= new StringBuilder();
        if (number.contains(",\n")){
            if (error.length()>0) error.append("\n");
            error.append("Number expected but '\\n' found at position ").append(number.indexOf(",\n"));
        }
        if (number.endsWith(",")){
            if (error.length()>0) error.append("\n");
            error.append("Number expected but EOF found.");
        }
        if (number.contains("-")){
            if (error.length()>0) error.append("\n");
            error.append(DetectNegative(number));
        }
        if (number.contains(",,")){
            if (error.length()>0) error.append("\n");
            error.append("Number expected but ',' found at position ").append(number.indexOf(",,")+1).append("");
        }
        return error.toString();
    }

    private String DetectNegative(String number){
        String numberVal = number;
        StringBuilder result= new StringBuilder();
        int index=0;
        while (index != -1){
            index = numberVal.indexOf("-");
            if (index != -1){
                String val = String.valueOf(numberVal.charAt(index));
                String val1 = String.valueOf(numberVal.charAt(index+1));
                if (result.length() > 0){
                    result.append(", ").append(val).append(val1);
                } else {
                    result.append(val).append(val1);
                }
                index++;
                numberVal=numberVal.substring(index);
            }
        }
        if (result.length() != 0) {
            result.insert(0, "Negative not allowed : ");
        }
        return result.toString();
    }

}
