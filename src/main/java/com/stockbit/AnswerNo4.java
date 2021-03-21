package com.stockbit;

import org.json.simple.JSONArray;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AnswerNo4 {
	//this code works well with condition that there is no duplicated words
	//means each word should be different compared each other
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		String[] list = {"kita", "atik", "tika", "aku", "kia", "makan", "kua"};
		List<String> input = new ArrayList(Arrays.asList(list));
		JSONArray result = new JSONArray();
		int size = input.size();
		while(size>0) {
			JSONArray anagram = new JSONArray();

			String sortedWord = sorting(input.get(0));
			anagram.add(input.get(0));

			for (int j = 1; j < size; j++) {
				String word = input.get(j);
				if(sortedWord.equals(sorting(word))){
					anagram.add(word);
				}
			}
			result.add(anagram);

			for (int i=0; i<anagram.size();i++) {
				input.remove(anagram.get(i));
			}
			size = input.size();
		}
		System.out.println(result.toJSONString());
	}

	public static String sorting(String input){
		String[] wordArray = input.split("");
		Arrays.sort(wordArray);
		return String.join("",wordArray);
	}

}
