import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  /*** Inserts an item at the queue front.*/
  public void enqueue(Object item);
  /*** Removes the object at the queue rear and returnsit.*/
  public Object dequeue();
  /*** Tests if this queue is empty.*/
  public boolean isEmpty();
  /*** Returns the number of elements in the queue*/
  public int size();
}

public class ArrayQueue implements IQueue{
    
    static  Object[] tempArray ;
    
    public ArrayQueue(int n){
          tempArray = new Object[n];

    }
    
    

    
    @Override
    public void enqueue(Object item) {
       if (tempArray[0] == null) {
            tempArray[0] = item;
             return ;}
         tempArray = Arrays.copyOf(tempArray, tempArray.length + 1);
        for(int i = tempArray.length; i > 0; i--) {
            if (tempArray[i-1] != null) {
                tempArray[i] = tempArray[i-1];                
            }
        }

        tempArray[0] = item;
        
        
    }

    @Override
    public Object dequeue() {
         if (tempArray.length == 0)  return null;

                Object queueTop = tempArray[tempArray.length-1];
                tempArray = Arrays.copyOf(tempArray, tempArray.length - 1);
                return queueTop;
    }

    @Override
    public boolean isEmpty() {
        return tempArray[0] == null;
    }

    @Override
    public int size() {
        return tempArray.length;
    }
    
    
     public static void main(String[] args) {
            
         Scanner sc = new Scanner(System.in);
         String input = sc.nextLine().replaceAll("\\[|\\]", "");
           String[] s = input.split(", ");
          int n = s.length;
           ArrayQueue inQueue = new ArrayQueue(n);
if (!(s.length == 1 && s[0].isEmpty())) 
               for(int i = 0; i < s.length; i++) 
                   tempArray[i] = Integer.parseInt(s[i]);
                   

              
           
           String func = sc.nextLine();

           switch (func) {
           case "enqueue":
               Object element = sc.nextInt();
               inQueue.enqueue(element);
               System.out.println(Arrays.toString(tempArray));
               break;
           case "dequeue":
               
           
                   if(tempArray[0] == null) {
                       System.out.println("Error");
                       break;
                   }
                       
               Object queueTop = inQueue.dequeue();
               System.out.println(Arrays.toString(tempArray));
               break;

           case "isEmpty":
             String isempty = String.valueOf(inQueue.isEmpty());
               System.out.println(isempty.substring(0, 1).toUpperCase()+ isempty.substring(1)); //False instead of false
               break;
           case "size":
               if(tempArray[0] == null) {
                   System.out.println(0);
                   break;
               }
               System.out.println(inQueue.size());
              
               break;
              default:
           System.out.println("Error");
}
  
  
  }

}
