/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication19;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author XiaoxuShen
 */
public class JavaApplication19 {

    /**
     * @param args the command line arguments
     * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. 
     * Give a count as well as print the strings. 

    For example: 
    Input: "1123". You need to general all valid alphabet codes from this string. 

    Output List 
    aabc //a = 1, a = 1, b = 2, c = 3 
    kbc // since k is 11, b = 2, c= 3 
    alc // a = 1, l = 12, c = 3 
    aaw // a= 1, a =1, w= 23 
    kw // k = 11, w = 23
     */

	public static void main(String[] args) {
		for(String str : decode("1123"))
		{
			System.out.println(str);
		}
	}

	public static Set <String> decode(String in)
	{
		char curChar = 'a';
		Map <Integer, Character> numberToChar = new HashMap<>();
		for(int i = 1; i <= 26; i++)
		{
			numberToChar.put(i, curChar);
			curChar++;
		}
		return decodeHelper(numberToChar, in, 0, new ArrayList<Character>());
	}

	private static Set<String> decodeHelper(Map <Integer, Character> numberToChar, 
			String in, int charAt,
			ArrayList<Character> arrayList) {
		Set <String> result = new HashSet<>();
		if(charAt >= in.length())
		{
			String str = "";
			for(char c : arrayList)
			{
				str += c;
			}
			result.add(str);
			return result;
		}
		else
		{
			int charCode = Integer.valueOf(in.charAt(charAt) + "");
			char curChar = numberToChar.get(charCode);
			arrayList.add(curChar);
			result.addAll(decodeHelper(numberToChar, in, charAt+1, arrayList));
			arrayList.remove(arrayList.size()-1);
			if(in.length() > charAt+1)
			{
				charCode = Integer.valueOf(in.substring(charAt, charAt+2));
				if(charCode <= 26)
				{
					curChar = numberToChar.get(charCode);
					arrayList.add(curChar);
					result.addAll(decodeHelper(numberToChar, in, charAt+2, arrayList));								
					arrayList.remove(arrayList.size()-1);
				}
			}
		}
		return result;
	}
	
}

