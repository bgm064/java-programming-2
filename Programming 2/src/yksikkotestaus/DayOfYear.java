package yksikkotestaus;

import java.time.Year;

/**
 * Smelly Example #1
 * 
 * https://web.mit.edu/6.005/www/fa16/classes/04-code-review/
 * 
 * Collaboratively authored with contributions from: Saman Amarasinghe, Adam
 * Chlipala, Srini Devadas, Michael Ernst, Max Goldman, John Guttag, Daniel
 * Jackson, Rob Miller, Martin Rinard, and Armando Solar-Lezama.
 * 
 * This work is licensed under CC BY-SA 4.0.
 */

public class DayOfYear {

    public static int dayOfYear(int month, int dayOfMonth, int year) {
    	// --------------------------------------------------------------------
    	// alkuperäinen koodi
    	
    	/* if (month == 2) {
            dayOfMonth += 31;
        } else if (month == 3) {
            dayOfMonth += 59;
        } else if (month == 4) {
            dayOfMonth += 90;
        } else if (month == 5) {
            dayOfMonth += 31 + 28 + 31 + 30;
        } else if (month == 6) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31;
        } else if (month == 7) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30;
        } else if (month == 8) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31;
        } else if (month == 9) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
        } else if (month == 10) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
        } else if (month == 11) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
        } else if (month == 12) {
            dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 31;
        }
        
        return dayOfMonth; */
    	
    	// --------------------------------------------------------------------
    	// alkuperäinen koodi korjattuna ottamaan huomioon karkausvuodet sekä epäkelvot päivämäärät
    	
    	/* if (month <= 0 || month > 12 || dayOfMonth <= 0 || dayOfMonth > 31 || year <= 0) {
    		throw new IllegalArgumentException("Invalid date");
    	}
    	
    	if (month == 2) {
        	dayOfMonth += 31;
        } else if (month == 3) {
        	dayOfMonth += 59;
        } else if (month == 4) {
        	dayOfMonth += 90;
        } else if (month == 5) {
        	dayOfMonth += 31 + 28 + 31 + 30;
        } else if (month == 6) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31;
        } else if (month == 7) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30;
        } else if (month == 8) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31;
        } else if (month == 9) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
        } else if (month == 10) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
        } else if (month == 11) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
        } else if (month == 12) {
        	dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
        }
        
        if (Year.isLeap(year)) {
        	dayOfMonth += 1;
        }
        
		return dayOfMonth; */	

    	// --------------------------------------------------------------------
    	// refaktoroitu koodi
    	
    	if (month <= 0 || month > 12 || dayOfMonth <= 0 || dayOfMonth > 31 || year <= 0) {
    		throw new IllegalArgumentException("Invalid date");
    	}
    	
    	Year givenYear = Year.of(year);
    	
    	int[] monthDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    	
    	if (givenYear.isLeap()) {
    		monthDays[1] = 29;
    	}
    	
    	for (int i = 1; i < month; i++) {
    		dayOfMonth += monthDays[i - 1];
		}
    	
    	return dayOfMonth;
	}
}
