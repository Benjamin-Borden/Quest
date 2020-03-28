import java.util.Scanner;
import java.util.stream.*;
import java.util.Arrays;
public class Input {
    private static Scanner scan = new Scanner(System.in);

    public static void error(){
        System.out.println("Adventurer! That wasn't what you were supposed to respond with! Try something else!");
    }
    public static int getInt(int lowbound, int highbound){

        boolean valid = false;
        int i = 0;
        do{
            System.out.print("Enter Number: ");
            String str = scan.next();
            try{
                i = Integer.parseInt(str);
                if(i >= lowbound && i <= highbound){
                    valid = true;
                }
            } catch(Exception e){
                error();
            }
        }while(!valid);

        return i;

    }


    public static String getString() {
        return scan.next();
    }

    public static char getChar(char[] cs){

        boolean valid = false;
        char rtrn = '0';
        do{
            String str = scan.next();
            if(str.length()==1){
                for(char c: cs){
                    if(c==str.charAt(0)){
                        rtrn = str.charAt(0);
                        valid=true;
                    }

                }
            }
            if(!valid){
                error();
            }
        }while(!valid);

        return rtrn;
    }

    //https://stackoverflow.com/questions/31851548/flatten-nested-arrays-in-java
    public static Stream<Object> flatten(Object[] array) {
        return Arrays.stream(array)
                .flatMap(o -> o instanceof Object[]? flatten((Object[])o): Stream.of(o));
    }



}
