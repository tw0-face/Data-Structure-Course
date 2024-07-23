import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}


interface IStack {
/**
* Removes the element at the top of stack and returns that element.
* @return top of stack element, or through exception if empty
*/
public Object pop();
/**
* Get the element at the top of stack without removing it from stack.
* @return top of stack element, or through exception if empty
*/
public Object peek();
/**
* Pushes an item onto the top of this stack.
* @param object to insert
*/
public void push(Object element);
/**
* Tests if this stack is empty
* @return true if stack empty
*/
public boolean isEmpty();
public int size();
}

class SingleLinkedList implements ILinkedList {
        

    static class Node {
        private Object element; //Node data
        private Node next; //Node link
        public Node (Object data) { //Node constructor
            element = data;
            
        }
        
    }
    
    
    private Node head, tail; //first and last elements in linked list
    private int size;
    
    public SingleLinkedList(Node head){ //SingleLinkedList constructor
        this.head = head;
        this.tail = head;
        if (head != null) {
            this.size = 1;
            Node current = head;
            while(current.next != null) {
                current = current.next;
                this.size++;
            }
        tail = current;
        }
        else this.size = 0;
    }
    
    @Override
    public void add(int index, Object element) {
          
        Node current = head;
        Node newNode = new Node(element);
                if(index==0) {
            newNode.next=head;
            head=newNode;
            size++;return;}
        
        if (current != null) {
            for (int i = 0; i < index-1; i++) {
                current = current.next;
            }
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
        
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) head=tail=newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        
    }

    @Override
    public Object get(int index) {
        if (index==0)  return head.element;
        Node current = head.next; //node that head points to
        for (int i = 0; i < index-1; i++) {
            current = current.next;//travering till the index
        }
        return current.element;
    }

    @Override
    public void set(int index, Object element) {
        if (index==0) {head.element = element; return;} //if adding a head
        Node current = head.next;
        for (int i = 0; i < index-1; i++) {
            current = current.next;
        }
        current.element = element;
        
    }

    @Override
    public void clear() {
        head.element = tail.element = null;
        size = 0;    
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {head = head.next; size--; return;} //removing first element
        Node current = head;
        for (int i = 0; i < index-1; i++) {
            current = current.next;
        }
        current.next = (current.next).next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        Node current = head;
        for (int i = 0; i < fromIndex; i++) {
          current = current.next;
        }
        final SingleLinkedList subl = new SingleLinkedList(null); //creating an empty linked list
        subl.add(current.element);
        for (int i = fromIndex; i < toIndex; i++) {
          current = current.next;
          subl.add(current.element);// adding elements in range to the sublist
        }
        return subl;
    }

    @Override
    public boolean contains(Object o) {
        int index =0;
        Node current = head;
        while (current != null) {
            if (current.element == o) return true;
            current = current.next;
            index++;
        }
        return false;
    }
    public void printLList() { //printing the linked list
        Node current = this.head;
        if(current.element != null){
            System.out.print("[");
            while(current.next != null) {
                System.out.print(current.element+ ", ");
                current = current.next;
            }
            System.out.print(current.element+"]\n");
        } else 
            System.out.println("[]");
    }
}

class MyStack implements IStack {
    
    static SingleLinkedList tempList = new SingleLinkedList(null);

    @Override
    public Object pop() {
        if (tempList.isEmpty()) {
           // System.out.println("Error");
            return null;
        } 
        

            Object stackTop = tempList.get(0);
            tempList.remove(0);
            return stackTop;
        
    }

    @Override
    public Object peek() {
        if (tempList.isEmpty()) {
            System.out.println("Error");
            return null;
        } else {

            Object stackTop = tempList.get(0);
            return stackTop;
        }
    }

    @Override
    public void push(Object element) {

tempList.add(0, element);

    }

    @Override
    public boolean isEmpty() {
        return tempList.isEmpty();
    }

    @Override
    public int size() {
        return tempList.size();
    }

    

     public static void main(String[] args) {
    
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().replaceAll("\\[|\\]", "");
              String[] s = input.split(", ");
              MyStack inStack = new MyStack();
if (!(s.length == 1 && s[0].isEmpty())) 
                  for(int i = 0; i < s.length; i++) 
                      tempList.add(Integer.parseInt(s[i]));
                 
              
              String func = sc.nextLine();

              switch (func) {
              case "push":
                  Object element = sc.nextInt();
                  inStack.push(element);
                  tempList.printLList();
                  break;
              case "pop":
                  
                      if(inStack.isEmpty()) {
                      System.out.println("Error");
                      break;
                  }
                      if(inStack.size() == 1) {
                          System.out.println("[]");
                          break;
                      }
                          
                  Object stackTop = inStack.pop();
                  tempList.printLList();
                  break;
              case "peek":
                          if(inStack.isEmpty()) {
                      System.out.println("Error");
                      break;
                  }
                  Object lastElem = inStack.peek();
                  System.out.println(lastElem);
                  break;
              case "isEmpty":
                String isempty = String.valueOf(inStack.isEmpty());
                  System.out.println(isempty.substring(0, 1).toUpperCase()+ isempty.substring(1)); //False instead of false
                  break;
              case "size":
                  System.out.println(inStack.size());
                  break;
                 default:
              System.out.println("Error");
   }
     
     
     }


}


interface IExpressionEvaluator {
  
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression infix expression
* @return postfix expression
*/
  
public String infixToPostfix(String expression);
  
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/
  
public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {
  
    static String a,b,c;
    
    public static boolean isInt(String s) {
        if (s == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    

    static int priority(String s)
    {
        switch (s.charAt(0)) {
        case '+': 
        case '-': 
            return 1;
  
        case '*': 
        case '/':
            return 2;
            
        case '^':
            return 3;

           default:
               return 0;
        }
       
    }
    
    static boolean isOperator(String s)
    {
        switch (s.charAt(0)) {
        case '+': 
        case '-': 
        case '*': 
        case '/':
        case '^':
            return true;
           default:
               return false;
        }
       
    }
    
    @Override
    public String infixToPostfix(String expression) {
        expression = expression.replace("--", "+");
        expression = expression.replace("++", "+");
        expression = expression.replace("+-", "-");
        expression = expression.replace("^+", "^");
        expression = expression.replace("*+", "*");
        expression = expression.replace("/+", "/");
        if (expression.charAt(0) == '+') expression = expression.substring(1);
        expression = expression.replace(" ", "");
        String op="+-*/^()";
        String res="";
        char[] ch = expression.toCharArray();
        for (char c : ch){
            if (op.contains(""+c))res+="&"+c+"&";
            else res+=c;}
        String[] s2=res.split("&");
         String[] s = Arrays.stream(s2).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size]);
        
        String postExp = "";
        MyStack tempStack = new MyStack();
        int nBrackets = 0;
        for (int i = 0; i < s.length; i++) {
            String tempChar = s[i];
           if (isInt(tempChar) || tempChar.equals("a") || tempChar.equals("b") || tempChar.equals("c")) postExp += tempChar; //if input is a,b,c
            else if (tempChar.equals("(") ) {tempStack.push(tempChar);nBrackets++;} //if input is (
            else if (tempChar.equals(")") && nBrackets > 0 && !tempStack.isEmpty()) { //if input is )
                while (!tempStack.isEmpty() && tempStack.peek() != "(") { postExp += (String) tempStack.pop();
                tempStack.pop();nBrackets--;}
            }
            else if (isOperator(tempChar) ) 
            {    
                while (!(tempStack.isEmpty())) {
                    if (priority(tempChar) <= priority((String)tempStack.peek()))postExp += tempStack.pop();
                    else  {tempStack.push(tempChar);break;}}
                if (tempStack.isEmpty()) tempStack.push(tempChar);
            }
            else {System.out.println("Error"); System.exit(0);} 
        }
        
           while (!tempStack.isEmpty()) {
               if ((String) tempStack.peek() == "(" || (String) tempStack.peek() == ")" || nBrackets != 0) {System.out.println("Error"); System.exit(0);}
               else postExp += (String) tempStack.pop();
               
           }
           if (postExp.length()==0){System.out.println("Error"); System.exit(0);}
         return postExp;    
    }

    @Override
    public int evaluate(String expression) {
    //    if (!(expression.contains("a") ||expression.contains("a") || ))
        int result = 0;
        MyStack tempStack = new MyStack();
        
         
         String[] s = expression.split("");
            for (int i = 0; i < s.length; i++) {
                if(s[i].equals("a")) s[i] = a;
                else if(s[i].equals("b")) s[i] = b;
                else if(s[i].equals("c")) s[i] = c;
                else continue;
            }
  
        for (int i = 0; i < s.length; i++) {
            String tempChar = s[i];      
            
            //if (isInt(tempChar)) tempStack.push(Integer.valueOf(tempChar)); // push numerical value of char to stack
             if (tempChar.equals("+") || tempChar.equals("-") || tempChar.equals("*") || tempChar.equals("/") || tempChar.equals("^") ) 
            {
                int num1 = (int) tempStack.pop();
                int num2 = 0;
                int res = 0;
                if (tempStack.size() > 0) num2 = (int) tempStack.pop();
                switch(tempChar.charAt(0))
                {
                    case '+':
                    res = num2 + num1;
                    break;
                    case '-':
                        res = num2 - num1;
                    break;
                    case '*':
                        res = num2 * num1;
                    break;
                    case '/':
                        res = num2 / num1 ;
                    break;            
                    case '^':
                        res = (int) Math.pow(num2, num1) ;
                        break;
                    default:
                        System.out.println("Error");
                         System.exit(0);
            }
                tempStack.push(res);
            }

            
            else {tempStack.push(Integer.valueOf(tempChar));} 

            }
        result =  (int) tempStack.pop();
        return (int) result;
    }
    
  
    public static void main(String[] args) {
        Evaluator eval = new Evaluator();
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine().trim();
         a = sc.nextLine().split("=")[1].trim();
         b = sc.nextLine().split("=")[1].trim();
         c = sc.nextLine().split("=")[1].trim();
         if(a.length() == 0 || b.length() == 0 || c.length() == 0) {
             System.out.println("Error");
             System.exit(0);
         }
         switch(expression.charAt(0)){case '*':case'/':case'^':System.out.println("Error");System.exit(0);}
         switch(expression.charAt(expression.length()-1)){case '*':case'/':case'^':case'+':case'-':System.out.println("Error");System.exit(0);}
         if(expression.contains("//") || expression.contains("^^") || expression.contains("**")) {System.out.println("Error");System.exit(0);}
        String postExp = eval.infixToPostfix(expression);
        System.out.println(postExp);
        int result = eval.evaluate(postExp);
        System.out.println(result);

    }



}