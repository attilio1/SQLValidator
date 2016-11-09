package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static final String EXAMPLE_TEST = "This is my small example "
      + "string which I'm going to " + "use for pattern matching.";

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(".*lB");
		Matcher matcher = pattern.matcher("ColB");
		System.out.println(matcher.matches());
	}


}
