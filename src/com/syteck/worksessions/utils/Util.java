package com.syteck.worksessions.utils;

public class Util {
	
	public static boolean isInt(String str) {
		
		try {
			
			Integer.parseInt(str);
			return true;
			
		} catch(NumberFormatException e) {
			
			return false;
			
		}
	}

}
