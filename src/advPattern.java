import java.util.Scanner;

public class advPattern {

    public static void butterfly(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2*(height-i)-2; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = height-1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2*(height-i)-2; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void solidRhombus(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height-i-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < height; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void numberPyramid(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 0; j < height-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public static void palindromicPyramid(int height) {
        for (int i = 1; i <= height+1; i++) {
            for (int j = 0; j < 2*(height-i+1); j++) {
                System.out.print(" ");
            }
            for (int j = i-1; j > 0; j--) {
                System.out.print(j+" ");
            }
            for (int j = 2; j < i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public static void diamond(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height-i-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = height-2; i >= 0; i--) {
            for (int j = 0; j < height-i-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void hollowButterfly(int height) {
        for (int i = 0; i < height; i++) {
            System.out.print("*");
            for (int j = 0; j < i-height+3; j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            for (int j = 0; j < 2*(height-i-1); j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            for (int j = 0; j < i-height+3; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
        for (int i = height-1; i >= 0; i--) {
            System.out.print("*");
            for (int j = 0; j < i-height+3; j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            for (int j = 0; j < 2*(height-i-1); j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            for (int j = 0; j < i-height+3; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
    }

    public static void hollowRhombus(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height-i-1; j++) {
                System.out.print(" ");
            }
            if (i == 0 || i == height-1) {
                for (int j = 0; j < height; j++) {
                    System.out.print("*");
                }
                System.out.println();
                continue;
            } else {
                System.out.print("*");
            }
            for (int j = 0; j < height-2; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
    } 

    public static void hollowDiamond(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = height; j > i; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j = 0; j < 2*i-1; j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = height-2; i >= 0; i--) {
            for (int j = height; j > i; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j = 0; j < 2*i-1; j++) {
                System.out.print(" ");
            }
            if (i != 0) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    public static void pascalsTriangle(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height-i+1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i+1; j++) {
                int iFacto = factorial(i);
                int jFacto = factorial(j);
                int i_jFacto = factorial(i-j);
                System.out.print(iFacto/(jFacto*i_jFacto)+" ");
            }
            System.out.println();
        }
    }

    public static int factorial(int n) {
        if (n <= 0) {
            return 1;
        } 
        else {
            return n * factorial(n-1);
        }
    }

    public static void circle(int height) {
        for (int i = 0; i < height-2; i++) {
            for (int j = 0; j < height-3-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < height+2*i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < 1; i++){
            for (int j = 0; j < height+4; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = height-3; i >= 0; i--) {
            for (int j = 0; j < height-3-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < height+2*i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void arrow(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = height-1; j > i; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < height*2; i++) {
            for (int j = 0 ; j < height-1; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter pyramid height: ");
        int height = sc.nextInt();

        // butterfly(height);
        // solidRhombus(height);
        // numberPyramid(height);
        // palindromicPyramid(height);
        // diamond(height);
        // hollowButterfly();
        // hollowRhombus();
        // hollowDiamond(height);
        // pascalsTriangle();
        // halfPyramid();
        // invertedHalfPyramid();
        // circle(height);
        // arrow(height);
        
        System.out.println(height);

        sc.close();
    }
    
}