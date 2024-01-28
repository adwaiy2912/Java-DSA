import java.util.Scanner;

public class helloWorld {
    public static void main(String arg[]) {
        System.out.println("Hello World");

        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        System.out.println(sentence);
        sc.close();
    }
}