import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int fibonacci(int n) {
    	// Implement your method here.
        int f_term = 0, s_term = 1 ;
for (int i = 0; i < n; i++) {
    int n_term = f_term + s_term;
    f_term = s_term;
    s_term = n_term;
  }
          
        return s_term-f_term;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
   Scanner sc = new Scanner(System.in);
            int sin = sc.nextInt();
          int res = new Solution().fibonacci(sin);
                    System.out.print(res);


    
    }
}