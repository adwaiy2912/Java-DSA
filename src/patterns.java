import java.util.Scanner;

public class patterns {
    
    public static void solidRectangle(int length, int breadth) {
        for (int i = 0; i < breadth; i++){
            for (int j = 0; j < length; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void hollowRectangle(int length, int breadth) {
        for (int i = 0; i < breadth; i++) {
            for (int j = 0; j < length; j++) {
                if (i == 0 || i == breadth-1 || j == 0 || j == length-1) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void halfPyramid(int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void invertedHalfPyramid(int length) {
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void rotatedHalfPyramid(int length) {
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < length; j++) {
                if (j+1 >= i) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void numberedHalfPyramid(int length) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void invertedNumberedHalfPyramid(int length) {
        System.out.println();
        for (int i = length; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void floydsTriangle(int length) {
        int num = 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }

    public static void _0_1Triangle(int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i+j) % 2 == 0){
                    System.out.print(1);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    public static void rotatedPyramid(int length) {
        for (int i = 1; i <= length; i++) {
            if (i <= (int)(length/2)) {
                for (int j = 0; j < i; j++) {
                    System.out.print("*");
                }
            }
            else {
                for (int j = 0; j <= length-i; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public static void pyramid(int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height-i-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void halfPyramid2(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 0; j < height-i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void invertedHalfPyramid2(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = height; j >= i; j--) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void checkerboard (int height) {
        for (int i = 0; i < height; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < height; j++) {
                    System.out.print("*");
                    System.out.print(" ");
                }
                System.out.println();
            } else {
                for (int j = 0; j < height; j++) {
                    System.out.print(" ");
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();

        // solidRectangle(height, height);
        // hollowRectangle(height, height);
        // halfPyramid(height);
        // invertedHalfPyramid(height);
        // rotatedHalfPyramid(height);
        // numberedHalfPyramid(height);
        // invertedNumberedHalfPyramid(height);
        // floydsTriangle(height);
        // _0_1Triangle(height);
        // rotatedPyramid(height);
        // pyramid(height);
        // halfPyramid2(height);
        // invertedHalfPyramid2(height);
        checkerboard(height);

        sc.close();
    }
}
