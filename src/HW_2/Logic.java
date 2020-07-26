package HW_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Logic {

    public static String makeRq(int i, String request){

        String line = "";

        switch (i){
            case 1:
                break;
            case 2:
                break;
            case 12:
                line = "\"" + request + "\", ";
                break;
            default:
                line = request + ", ";
                break;
        }

        return line;
    }


    public static String scannerStr(String s){
        Scanner scan = new Scanner(System.in);
        System.out.println(s);
        return scan.nextLine();
    }

    public static int scannerInt(String s){
        int size = -1;
        Scanner scan = new Scanner(System.in);
        System.out.println(s);

        try {
            size = scan.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Необходимо ввести число");
        }
        return size;
    }
}
