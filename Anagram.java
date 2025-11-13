/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		String newStr1 = "";
		String newStr2 = "";
		for(int i = 0; i < str1.length(); i ++){
			if (((str1.charAt(i) >= 97) && (str1.charAt(i) <= 122))){
				newStr1 = newStr1+ str1.charAt(i);
			}
		}
		for(int j = 0; j < str2.length(); j ++){
			if (((str2.charAt(j) >= 97) && (str2.charAt(j) <= 122))){
				newStr2 = newStr2+ str2.charAt(j);
			}
		}
		int sum = 0;
		for(int l = 0; l < str1.length(); l++){
			char check = str1.charAt(l);
			boolean found = false;
			for(int k = 0; k < str2.length(); k++){
				if (str2.charAt(k) == check) {
					sum++;
					str2 = str2.substring(0, k) + str2.substring(k + 1);
					found = true;
					break;
				}
			}
			if (!found) return false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
		String newStr = "";
		for(int i = 0; i < str.length(); i ++){
			if (((str.charAt(i) >= 97) && (str.charAt(i) <= 122)) || (str.charAt(i) == ' ')){
				newStr = newStr + str.charAt(i);
			}
		}
		return newStr;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String newStr = "";
		int length = str.length();
		while (newStr.length() < length) {
			int place = (int)(Math.random() * str.length());
			char add = str.charAt(place);
			newStr = newStr + add;
			str = str.substring(0, place) + str.substring(place + 1);
		}
		return newStr;
	}
}
