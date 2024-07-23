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

public class LinkedListQueue implements IQueue {

    static SingleLinkedList tempList = new SingleLinkedList(null);

    @Override
    public void enqueue(Object item) {
        tempList.add(0, item);
        
    }

    @Override
    public Object dequeue() {
         if (tempList.isEmpty())   return null;

                Object queueTop = tempList.get(tempList.size()-1);
                tempList.remove(tempList.size()-1);
                return queueTop;
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
           LinkedListQueue inQueue = new LinkedListQueue();
if (!(s.length == 1 && s[0].isEmpty())) 
               for(int i = 0; i < s.length; i++) 
                   tempList.add(Integer.parseInt(s[i]));
              
           
           String func = sc.nextLine();

           switch (func) {
           case "enqueue":
               Object element = sc.nextInt();
               inQueue.enqueue(element);
               tempList.printLList();
               break;
           case "dequeue":
               
                   if(inQueue.isEmpty()) {
                   System.out.println("Error");
                   break;
               }
                   if(inQueue.size() == 1) {
                       System.out.println("[]");
                       break;
                   }
                       
               Object queueTop = inQueue.dequeue();
               tempList.printLList();
               break;

           case "isEmpty":
             String isempty = String.valueOf(inQueue.isEmpty());
               System.out.println(isempty.substring(0, 1).toUpperCase()+ isempty.substring(1)); //False instead of false
               break;
           case "size":
               System.out.println(inQueue.size());
               break;
              default:
           System.out.println("Error");
}
  
  
  }

     


}