//**************************************************
// Nathan Schnitzer
// DJB2A.java
// 5/18/18
// Hashing Algorithm
//***************************************************

//REMEMBER TO GET A GNU LICENSE THIS TIME DIPSHIT (5.18.18)

import java.util.Random;
import java.util.ArrayList;

public class DJB2A 
{
	//private static  final long RAND_SEED = Long.MAX_VALUE;
	private static ArrayList<String> messages = null, recipts= null, keys = null, cipherText = null;
	
	//Constructor
	public DJB2A()
	{
		//Initializes trackers
		messages = new ArrayList<String>();;
		recipts = new ArrayList<String>();;
		cipherText = new ArrayList<String>();;
		keys = new ArrayList<String>();;
	}
	
	
	public static String xorEncrypt(String stringy) // String max of 7
	{
		Random gen = new Random();
		
		if (stringy.length() >= 6)
		{
			throw new IllegalArgumentException();
		}
		Integer[] charVals = new  Integer[stringy.length()];
		for (int i = 0; i < stringy.length(); i++)
		{
			Character temp = (Character)stringy.charAt(i);
			int tempInt = temp.charValue();
			charVals[i] = (Integer)tempInt;
		}
		String tempStore = "";
		for (int i = 0; i < charVals.length; i++)
		{
			String binStr = Integer.toBinaryString(charVals[i]);
			binStr = "0" + binStr;
			tempStore += "" + Integer.toBinaryString(charVals[i]);
		}
		String str = "";
		String c = "";
		for (int i = 0; i < tempStore.length(); i++)
		{
			c += gen.nextInt(2);
		}
		
		System.out.println(tempStore + " str");
		System.out.println(c + " c");
		
		//Actual XOR stuff now
		for (int i = 0; i < tempStore.length(); i++)
		{
			if (tempStore.charAt(i) != c.charAt(i))
			{
				str += "1";
			}
			else
			{
				str += "0";
			}
		}
		//If Constructor wasnt called
			if (messages == null)
			{
				messages = new ArrayList<String>();;
				recipts = new ArrayList<String>();;
				keys = new ArrayList<String>();;
				cipherText = new ArrayList<String>();;
			}
			int counter = 0;
			while (messages.get(counter) != null)
			{
				counter = counter + 1;
			}
			messages.add(stringy);
			keys.add(c);
			cipherText.add(str);
			recipts.add("" + gen.nextInt(Integer.MAX_VALUE));
		System.out.println("Returning");
		return str + ("\nRecipt: " + recipts.get(counter));
	}
		
	/* No longer necessary since ArrayLists are used
	private static void reallocate()
	{
		int newLength = messages.length + 7;
		ArrayList<String> newMessages = new String[newLength];
		ArrayList<String> newRecipts = new String[newLength];
		ArrayList<String> newKeys = new String[newLength];
		ArrayList<String> newCipherText = new String[newLength];
		
		for (int i = 0; i < messages.length; i++)
		{
			newMessages[i] = messages[i];
			newRecipts[i] = recipts[i];
			newKeys[i] = keys[i];
			newCipherText[i] = cipherText[i];
		}
		messages = newMessages;
		recipts = newRecipts;
		cipherText = newCipherText;
		keys = newKeys;
		return;
	}
	*/
	
	public String getMessage(String proof)
	{
		//proof is the recipt
		int find = recipts.indexOf(proof);
		
		if (find == -1)
		{
			return "Nice Try";
		}
		else
			return messages.get(find);
	}
	
	public String getKey(String proof, String message) 
	{
		//This requires both the recipt and message as security measures
		int findRecipt = recipts.indexOf(proof);
		int findMessage = messages.indexOf(message);
		
		if (findRecipt != -1 && findMessage != -1)
		{
			if (findRecipt == findMessage)
			{
				return keys.get(findRecipt);
			}
		}
		return "Nice Try";
	}
}
	
	