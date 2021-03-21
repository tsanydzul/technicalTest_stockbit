package com.stockbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

public class AnswerNo3 {

    public static void main(String[] args) {
        String str = "TEST(TRUE))))))";
        System.out.println("Question on Java Version, Result = " + findFirstStringInBracket(str));
        System.out.println("Optimized Java Version, Result = " + findFirstStringInBracketOptimized(str));
    }

    //Question on Java Version
    public static String findFirstStringInBracket(String str){
        if(str.length()>0){
            int indexFirstBracketFound = str.indexOf("(");
            if(indexFirstBracketFound >= 0){
                String[] runes = str.split("");
                String wordsAfterFirstBracket = String.join("",Arrays.copyOfRange(runes, indexFirstBracketFound, str.length()));
                int closingBracket = wordsAfterFirstBracket.indexOf(")");
                if(closingBracket>=0){
                    String[] runes2 = wordsAfterFirstBracket.split("");
                    String result = String.join("", Arrays.copyOfRange(runes2, 1, closingBracket));
                    return result;
                }else{
                    return "";
                }
            }else{
                return "";
            }
        }else{
            return "";
        }
    }

    //Optimized Java Version
    public static String findFirstStringInBracketOptimized(String str){
        if(str.length()>0){
            int firstBracket = str.indexOf("(");
            int closingBracket = str.indexOf(")");
            if(firstBracket>=0 && closingBracket >=0){
                return str.substring(firstBracket+1, closingBracket);
            }else {
                return "";
            }
        }else{
            return "";
        }
    }

}
