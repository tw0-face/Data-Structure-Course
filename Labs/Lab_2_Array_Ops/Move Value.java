import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] moveValue(int[] array, int value) {
    	/*
        	Implement your method here
        */
                int n = array.length; 
        int[] temp = new int[n];
        int j =0;
        int last =n-1;
           for (int i = 0; i < n; i++) {
               if(array[i] != value ) {
                   temp[j] = array[i];
               j++;}
               else {
                   temp[last] = value;
               last--;}
            }
          
        return temp;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  
      Scanner sc = new Scanner(System.in);
            String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        int number = sc.nextInt();
            String[] s = sin.split(", ");;
            int[] arr = new int[s.length];
            if (s.length == 1 && s[0].isEmpty())
                arr = new int[]{};
            else {
                for(int i = 0; i < s.length; ++i)
                   arr[i] = Integer.parseInt(s[i]);
            }
                
                  int[] res = new Solution().moveValue(arr, number);
          
            
            
            System.out.print("[");
            for (int i = 0; i < res.length; i++) {
                 System.out.print(res[i]);
                if (i < res.length - 1) {
                     System.out.print(", ");
                }
            }
            System.out.print("]");
            
    
    }
}