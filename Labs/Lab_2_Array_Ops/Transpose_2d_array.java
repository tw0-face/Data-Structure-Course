import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[][] transpose(int[][] array) {
    	/*
        	Implement your method here
        */
              int n_row = array.length;
        int n_col = array[0].length;
        int[][] trans = new int[n_col][n_row];
          for(int i = 0; i < n_row; i++) {
                for (int j = 0; j < n_col; j++) {
                    trans[j][i] = array[i][j];
                }
            }
          
        return trans;    
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
           Scanner sc = new Scanner(System.in);
                String sin = sc.nextLine();
               int row_size = sin.split("\\], \\[").length;
                String[] s = sin.replaceAll("\\[|\\]", "").split(", ");
                int col_size = s.length/row_size;
                int[][] arr = new int[row_size][col_size];
                if (s.length == 1 && s[0].isEmpty()){
                    arr = new int[][]{{}};}
                else {
                    int k =0;
                   for(int i = 0 ; i < row_size ; i++){
    for(int j = 0 ; j < col_size; j++)
    {
    arr[i][j] = Integer.parseInt(s[k++]);
    }}
                }
          
                      int[][] res = new Solution().transpose(arr);
                      System.out.print("[[");
           
            for (int i = 0; i < res.length; i++) {
        
        for(int j = 0; j < res[i].length; j++) {
            System.out.print(res[i][j]); 
            if (j<res[i].length-1) {
                System.out.print(", ");
            } 
        }
      
        if (i<res[i].length-1) {
            System.out.print("], [");
        } 
        
        
    }
    System.out.print("]]");
            
          
          
    
    }
}