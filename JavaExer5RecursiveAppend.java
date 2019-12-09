/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author:Jing Yang
 *  Practice of recursion
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) {
    	if(n>=0){

    		return original+appendNTimes(original,n-1);
    	}else{
    		return "";
    	}
    	
 
    }

    public static void main (String[] args) {

		int num=Integer.parseInt(args[1]);
		System.out.println(appendNTimes(args[0],num));
    }
}
