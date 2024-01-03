package Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Views {
    public static void main(String[] args) {
        // Create a LinkedHashMap to maintain insertion order
//      
    	Random rand = new Random();
    	   
        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(10000);
        System.out.println(rand_int1);
        String s=String.valueOf(rand_int1);
        System.out.println(s);
        
    }

    // Method to add a key-value pair to a LinkedHashMap
    public static void addKeyValuePair(LinkedHashMap<Integer, String> linkedHashMap, int key, String value) {
        linkedHashMap.put(key, value);
    }
}
