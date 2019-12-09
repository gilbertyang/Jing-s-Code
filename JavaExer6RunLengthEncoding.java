/*************************************************************************
 *  Compilation:  javac RunLengthEncoding.java
 *  Execution:    java RunLengthEncoding
 *
 *  @author: Jing Yang
 *  to encode and decode of a string
 *************************************************************************/

public class RunLengthEncoding {

    /* 
     * Encode the original string by finding sequences in the string
     * where the same character repeats. Replace each such sequence
     * by a token consisting of: the number of characters in the sequence
     * followed by the repeating character.
     * Return the encoded string.
     */
    public static String encode (String original)  {
        int i=1;
        int count=1;
        while(i<original.length()&& original.charAt(i-1)==original.charAt(i)){
            count++;
            i++;
        }
        if(count==1){
            if(original.substring(i).length()!=0){
                return ""+original.charAt(i-1)+encode(original.substring(i));
            }else{
                return ""+original.charAt(i-1);
            }
        }else if(count>1){
            if(original.substring(i).length()!=0){
                return String.valueOf(count)+original.charAt(i-1)+encode(original.substring(i));
            }else{
                return String.valueOf(count)+original.charAt(i-1);
            }
        }else{
            return "";
        }
    }

    /*
     * Decodes the original string encoded with the encode method.
     * Returns the decoded string.
     *
     * YOUR decode METHOD MUST BE RECURSIVE, do not use while, do/while, 
     * or for loops
     */
    public static String decode (String original)  {
        String str=encode(original);
        char firstChar=str.charAt(0);
        int num=firstChar-'0';

        if(Character.isDigit(str.charAt(0))){
            if(str.substring(2).length()!=0){
                //Character.isDigit(str.charAt(2)) || Character.isLetter(str.charAt(2))
                if(num>0){                  //instance like 2ab2c
                    firstChar--;
                    String newStr=firstChar+str.substring(1);
                    return str.charAt(1)+decode(newStr);
                }else{
                    return decode(str.substring(2)); //when the first loop of character is finished, like, we finish decoding 2a in 2a2b
                }
                //return "a is not empty";
            }else{
                if(num>0){    //to decode loop like 2a
                    firstChar--;
                    String newStr=firstChar+str.substring(1);
                    return str.charAt(1)+decode(newStr);
                }else{       //example when finish decoding 2a--->0a
                    return "";
                }
                //return "a is empty";
            }
        }else if(Character.isLetter(str.charAt(0))){
            if(str.substring(1).length()!=0){
                //Character.isDigit(str.charAt(1)) || Character.isLetter(str.charAt(1))
                return str.charAt(0)+decode(str.substring(1));   //like a3b
                //return "b is not empty";
            }else{
                return str.charAt(0)+"";  //like a
                //return "b is empty";
            }
        }else{
            return "";
        }
        
    }

    public static void main (String[] args) {
        String original=args[0];
        System.out.println(encode(original));
        System.out.println(decode(original));
    }
}
