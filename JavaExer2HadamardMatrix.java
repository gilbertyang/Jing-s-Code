/*************************************************************************
 *  Compilation:  javac HadamardMatrix.java
 *  Execution:    java HadamardMatrix 2
 *
 *  @author: Jing Yang
 *
 * The program HadamardMatrix prints H(n)
 *
 *  % java HadamardMatrix 2
 *  T T
 *  T F
 *
 *************************************************************************/

public class HadamardMatrix {

    public static void main(String[] args) {

	// WRITE YOUR CODE HERE
    	int N=Integer.parseInt(args[0]);
    	boolean[][] H=new boolean[N][N];

    	H[0][0]=true;
    	for(int n=1;n<N;n+=n){
    		for(int i=0;i<n;i++){
    			for(int j=0;j<n;j++){
    				H[i+n][j]=H[i][j];
    				H[i][j+n]=H[i][j];
    				H[i+n][j+n]=!H[i][j];
    			}
    		}
    	}
    	for(int i=0;i<N;i++){
    		for(int j=0;j<N;j++){
    			if(H[i][j]){
    				System.out.print("T");
    			}else{
    				System.out.print("F");
    			}
    		}
    		System.out.println();
    	}
    }
}
