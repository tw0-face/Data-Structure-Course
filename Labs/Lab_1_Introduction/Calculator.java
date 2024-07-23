
import java.util.Scanner;

public class Calculator implements ICalculator{

	@Override
	public int add(int x, int y) {
		return x+y;
	}

	@Override
	public float divide(int x, int y) throws RuntimeException {
		return (float) x/y;
	}
	
	 public static void main(String[] args) {
		 Calculator myCalculator = new Calculator();
		    Scanner equation = new Scanner(System.in);
		    String eq = equation.nextLine();
	         String[] parts = eq.split(" ");
	
	            int fnumber =  Integer.parseInt(parts[0]);
	            char operator =  parts[1].charAt(0);
	            int snumber =  Integer.parseInt(parts[2]);
	      
		    switch (operator) {
		    case '+':
		        System.out.println(myCalculator.add(fnumber, snumber)); 
		    	break;
		    case '/':
		    	if (snumber == 0) {System.out.println("Error");}
		    	else { System.out.println(myCalculator.divide(fnumber, snumber)); }
		       
		    	break;
            default: System.out.println("Error");
            }

		    
	 }

}
