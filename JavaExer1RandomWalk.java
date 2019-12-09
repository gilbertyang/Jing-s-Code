/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author:Jing Yang
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker {

    public static void main(String[] args) {

	// WRITE YOUR CODE HERE
    	int steps=Integer.parseInt(args[0]);
    	int[] possible= new int[]{-1,0,1};
    	int[] possible1= new int[]{-1,1};
    	int x0=0;
    	int y0=0;
    	System.out.println("("+x0+","+y0+")");
    	for(int i=0;i<steps;i++){
    		
    		int x=(int)(Math.random()*2);
    		int x1=possible[x];

    		int y=(int)(Math.random()*2);
    		int y1=possible1[x];

    		if(x1==-1 || x1==1){
    			y1=0;
    		}else if(x1==0){
    			y1=y1;
    		}
    		x0+=x1;
    		y0+=y1;

    		x1=0;
    		y1=0;
    		System.out.println("("+x0+","+y0+")");
    	}
    	System.out.println("Squared distance = "+(Math.pow(x0,2)+Math.pow(y0,2)));
    }
}
