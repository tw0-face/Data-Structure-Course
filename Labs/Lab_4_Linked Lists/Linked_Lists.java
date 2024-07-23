import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;


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


interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
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

public class PolynomialSolver implements IPolynomialSolver{

    SingleLinkedList A = new SingleLinkedList(null);
    SingleLinkedList B = new SingleLinkedList(null);
    SingleLinkedList C = new SingleLinkedList(null);
    static SingleLinkedList R = new SingleLinkedList(null);
    public static Scanner sc = new Scanner(System.in);

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        if (terms[0].length == 0) {System.out.println("Error");System.exit(0);}//if terms empty array exit
         switch (poly) {
         case 'A': 
         A = arrayTLL(terms)    ; //terms array to linkedlist and assign it to poly
             break;
         case 'B': 
         B = arrayTLL(terms)    ;
             break;
         case 'C': 
         C = arrayTLL(terms)    ;
             break;
         case 'R': 
         R = arrayTLL(terms)    ;
             break;
         default:
            System.out.println("Error");
                 System.exit(0);
         }
        
    }


    @Override
    public String print(char poly) {
        SingleLinkedList tempList = null;
        String equation = "";
        switch (poly) {
        case 'A': 
        tempList = A;
            break;
        case 'B': 
            tempList = B;
            break;
        case 'C': 
            tempList = C;
            break;
        case 'R': 
            tempList = R;
            break;
        default:
            System.out.println("Error");
                System.exit(0);
        }
        if(tempList.isEmpty()) equation = "[]"; //if empty print empty list
        
        else{for(int i = 0; i < tempList.size(); i++)
        {
            int[] term = (int[])tempList.get(i); 
            String coeff = term[0]+"";
            String exp = term[1]+"";
            if (!exp.equals("0")){
                if (coeff.equals("1")) equation +="x^" + exp + "+";
                else if (coeff.equals("0")) equation +="";
                else equation += coeff + "x^" + exp + "+";
            }
            else if (exp.equals("0")){
                equation += coeff;
            }
        }}
        if(equation.contains("+-")) equation = equation.replace("+-", "-");
        if(equation.contains("x^1")) equation = equation.replace("x^1","x");
        
        System.out.println(equation);
        

    return equation;
    }

    @Override
    public void clearPolynomial(char poly) {
        
        switch (poly) {
        case 'A': 
       if(A.size() == 0) {System.out.println("Error"); System.exit(0);}
        A.clear();// clear linkedlist
            break;
        case 'B': 
        if(B.size() == 0) {System.out.println("Error"); System.exit(0);}
        B.clear();
            break;
        case 'C': 
        if(C.size() == 0) {System.out.println("Error"); System.exit(0);}
        C.clear();
            break;
        default:
            System.out.println("Error");
                System.exit(0);
        }
        
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        
        SingleLinkedList tempList = null;
        float sum = 0;
        switch (poly) {
        case 'A': 
        tempList = A;
            break;
        case 'B': 
            tempList = B;
            break;
        case 'C': 
            tempList = C;
            break;
        default:
            System.out.println("Error");
                System.exit(0);
        }
        if(tempList == null) {System.out.println("Error"); return (float)0;}
        for(int i = 0; i < tempList.size(); i++)
        {
            int[] term = (int[])tempList.get(i); 
            float coeff = term[0];
            float exp = term[1];
            sum +=(float)  coeff*Math.pow(value,exp) ;
        }
        return sum;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        SingleLinkedList firsteq = null;
        SingleLinkedList seceq = null;
        if(poly1=='A') firsteq = A;
        else if(poly1=='B') firsteq = B;
        else if(poly1=='C') firsteq = C;
        if(poly2=='A') seceq = A;
        else if(poly2=='B') seceq = B;
        else if(poly2=='C') seceq = C;
        int size1 = firsteq.size();
        int size2 = seceq.size();
        if(size1 == 0 || size2 == 0) {System.out.println("Error"); return null;}
        int [] coeff1 = new int[size1];
        int [] coeff2 = new int[size2];
        int [] sum = new int[Math.max(size1, size2)];
        for(int i = 0; i < size1; i++)
        {
            int[] term = (int[])firsteq.get(i); 
            coeff1[i] = term[0];
            }
        for(int i = 0; i < size2; i++)
        {
            int[] term = (int[])seceq.get(i); 
            coeff2[i] = term[0];
            }
        if (size1 == size2)
         for(int i = 0; i < Math.max(size1, size2); i++) //if same size add elements
             sum[i] = coeff1[i] + coeff2[i];
			 // if different sizes assign the bigger to sum then add the smaller starting from the size difference index
        else if (size1 > size2) {
            sum = coeff1;
            for (int i = 0; i < size2; i++)
                sum[size1-size2+i] += coeff2[i];
        } else {
            sum = coeff2;
            for (int i = 0; i < size1; i++)
                sum[size1-size2+i] += coeff1[i];
        }
        int[][] arr = new int[sum.length][2];
        for(int i = 0; i < sum.length; i++) {
            arr[i][0] = sum[i]; 
            arr[i][1] = sum.length-i-1; }
        return arr;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        SingleLinkedList firsteq = null;
        SingleLinkedList seceq = null;
        if(poly1=='A') firsteq = A;
        else if(poly1=='B') firsteq = B;
        else if(poly1=='C') firsteq = C;
        if(poly2=='A') seceq = A;
        else if(poly2=='B') seceq = B;
        else if(poly2=='C') seceq = C;
        int size1 = firsteq.size();
        int size2 = seceq.size();
        if(size1 == 0 || size2 == 0) {System.out.println("Error"); return null;}
        int [] coeff1 = new int[size1];
        int [] coeff2 = new int[size2];
        int [] sub = new int[Math.max(size1, size2)];
        for(int i = 0; i < size1; i++)
        {
            int[] term = (int[])firsteq.get(i); 
            coeff1[i] = term[0];
            }
        for(int i = 0; i < size2; i++)
        {
            int[] term = (int[])seceq.get(i); 
            coeff2[i] = term[0];
            }
        if (size1 == size2)
          for(int i = 0; i < Math.max(size1, size2); i++)
                sub[i] = coeff1[i] - coeff2[i];
        else if (size1 > size2) {
            sub = coeff1;
            for (int i = 0; i < size2; i++)
                sub[size1-size2+i] -= coeff2[i];
        } else {
            sub = coeff2;
            for (int i = 0; i < size1; i++)
                sub[size1-size2+i] -= coeff1[i];
        }
        int[][] arr = new int[sub.length][2];
        for(int i = 0; i < sub.length; i++) {
            arr[i][0] = sub[i]; 
            arr[i][1] = sub.length-i-1; }
        return arr;        
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        
        SingleLinkedList firsteq = null;
        SingleLinkedList seceq = null;
        if(poly1=='A') firsteq = A;
        else if(poly1=='B') firsteq = B;
        else if(poly1=='C') firsteq = C;
        if(poly2=='A') seceq = A;
        else if(poly2=='B') seceq = B;
        else if(poly2=='C') seceq = C;
        int size1 = firsteq.size();
        int size2 = seceq.size();
        if(size1 == 0 || size2 == 0) {System.out.println("0"); return null;} //if one array is empty print 0
        int [] coeff1 = new int[size1];
        int [] coeff2 = new int[size2];
        int [] exp1 = new int[size1];
        int [] exp2 = new int[size2];
        int [] mulCoeff = new int[size1+size2-1];
        for(int i = 0; i < size1; i++)
        {
            int[] term = (int[])firsteq.get(i); 
            coeff1[i] = term[0];
            exp1[i] = term[1];
            }
        for(int i = 0; i < size2; i++)
        {
            int[] term = (int[])seceq.get(i); 
            coeff2[i] = term[0];
            exp2[i] = term[1];
            }
        for(int i = 0; i < size1; i++)
        {
            for(int j = 0; j <size2; j++)
            {
                mulCoeff[i+j] += coeff1[i] * coeff2[j];
                }
            }
        
        int[][] arr = new int[mulCoeff.length][2];
        for(int i = 0; i < mulCoeff.length; i++) {
            arr[i][0] = mulCoeff[i]; 
            arr[i][1] = mulCoeff.length-i-1; }
           return arr;  }


        
    

    
    public static SingleLinkedList arrayTLL(int [][] terms)  //change array to single linked list
    {
        SingleLinkedList tempList = new SingleLinkedList(null);
        if (terms[0].length == 0) return tempList;
        for (int i = 0 ; i < terms.length ; i++) 
        {
            int[] term = new int[]{terms[i][0],terms.length-i-1} ;
            tempList.add(term);
        }
    return tempList;
    }
    public static void main(String[] args) {
        
 char poly;
          char poly2;
          int[][] terms = null;
          PolynomialSolver polsol = new PolynomialSolver();
          while(sc.hasNext()) {
            String func = sc.nextLine();
          switch (func) {
              case "set":
                  poly = sc.nextLine().charAt(0);
                  terms = readCoeff(); // read terms array 
                  polsol.setPolynomial(poly,terms);
                  break;
            case "print":
                poly = sc.nextLine().charAt(0);
                polsol.print(poly);  
                break;
            case "eval":
                  
                poly = sc.nextLine().charAt(0);
                float value= Float.parseFloat(sc.nextLine());
                 
                float val = polsol.evaluatePolynomial(poly, value);
                  System.out.println((int)val);
                
                break;
            case "clear":
                  
                poly = sc.nextLine().charAt(0);
                polsol.clearPolynomial(poly);
                polsol.print(poly);
                break;
            case "add":
             
                poly = sc.nextLine().charAt(0);
                poly2 = sc.nextLine().charAt(0);
                polsol.setPolynomial('R',  polsol.add(poly, poly2)); //assign the result to R linked list
                polsol.print('R');
             
                break;
            case "sub":
                poly = sc.nextLine().charAt(0);
                poly2 = sc.nextLine().charAt(0);
                polsol.setPolynomial('R', polsol.subtract(poly, poly2));
                polsol.print('R');
                break;
            case "mult":
              
                poly = sc.nextLine().charAt(0);
                poly2 = sc.nextLine().charAt(0);
                polsol.setPolynomial('R', polsol.multiply(poly, poly2));
                if (R.size() == 0) System.out.println("0");
                else polsol.print('R');
                break;
            default:
                System.out.println("Error");
                  System.exit(0);
          }
          }
   
        
        
          
          
      }
      
        public static int[][] readCoeff(){ //read array from string input
            String tempArr = sc.nextLine().replaceAll("\\[|\\]", "");
            String[] s = {};
              if(!tempArr.isEmpty()) {s = tempArr.split(",");}
              else {s = tempArr.split("");}
              if (s.length == 1 && s[0].isEmpty()) {
                  int[][] arr = {{}};
                  return arr;
              } else {
                  int[][] arr = new int[s.length][1];
                  for(int i = 0; i < s.length; i++) 
                      arr[i][0] = Integer.parseInt(s[i]);
                  return arr;
              }
    }

}