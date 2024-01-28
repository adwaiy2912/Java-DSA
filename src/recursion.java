import java.util.Scanner;

public class recursion {

    public static int Sum(int n) {
        if (n < 0) {
            System.out.println("Invalid input");
            return -1;
        }
        else if (n==0) {
            return 0;
        }
        int sum = n + Sum(n-1);
        return sum;
    }

    public static int Factorial(int n) {
        if (n < 0) {
            System.out.println("Invalid input");
            return -1;
        }
        else if (n == 0) {
            return 1;
        }
        int fac = n * Factorial(n-1);
        return fac;
    }

    public static void Fibonacci(int i, int a, int b, int n) {
        if (n <= -2) {
            System.out.println("Invalid input");
            System.out.println(-1);
            return;
        }
        if (n == -1) {
            System.out.println("0");
            return;
        }
        if (n >= 0 && i == 0) {
            System.out.print("0 1 ");
        }
        if (i == n) {
            return;
        }
        System.out.print((a+b) + " ");
        Fibonacci(i+1, b, a+b, n);
    }

    public static int Power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int ans = x * Power(x, n-1);
        return ans;
    }

    public static void towerOfHanoi(int n, String src, String helper, String dest, int i) {
        if (n == 1) {
            // System.out.println("Transfer disk " + n + " from " + src + " to " + dest);
            i++;
            System.out.println(i);
            return;
        }
        towerOfHanoi(n-1, src, dest, helper, i);
        // System.out.println("Transfer disk " + n + " from " + src + " to " + dest);
        towerOfHanoi(n-1, helper, src, dest, i);
    }

    public static void PrintRev(String str, int index) {
        if (index == 0){
            System.out.println(str.charAt(index));
            return;
        }
        System.out.print(str.charAt(index));
        PrintRev(str, index-1);
    }

    private static int first = -1;
    public static void PrintEle(String str, char ele, int index) {
        if (str.length() == index) {
            return;
        }
        if (str.charAt(index) == ele) {
            if (first == -1) {
                first = index;
            }
        }
        PrintEle(str, ele, index+1);
    }

    public static boolean IsSorted(int arr[], int index ) {
        if (arr.length-1 ==  index) {
            return true;
        }
        else if (arr[index] >= arr[index+1]) {
            return false;
        }
        return IsSorted(arr, index+1);

    }

    public static void XatEnd(String str, int index, String finalStr, int count) {
        if (str.length() == index) {
            for (int i = 0; i < count; i++) {
                finalStr += 'x';
            }
            System.out.println(finalStr);
            return;
        }
        else if (str.charAt(index) == 'x') {
            count++;
        } else {
            finalStr += str.charAt(index);
        }
        XatEnd(str, index+1, finalStr, count);
    }

    public static void RemovDup(String str, int index, String finalStr, char arr[]) {
        if (str.length() == index) {
            System.out.println(finalStr);
            return;
        }
        boolean isThere = false;
        char ch = str.charAt(index);
        for (int i = 0; i < arr.length; i++) {
            if  (arr[i] == ch) {
                isThere = true;
                break;
            }
        }
        if (!isThere) {
            finalStr += ch;
            char newArr[] = new char [arr.length+1];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            newArr[arr.length] = ch;
            RemovDup(str, index+1, finalStr, newArr);
        } else {
            RemovDup(str, index+1, finalStr, arr);
        }

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // System.out.print("Enter: ");
        // int n = sc.nextInt();

        // Sum(n); 
        // Factorial(n);
        // Fibonacci(0, 0, 1, n-2);
        // Power(2, n); 
        // towerOfHanoi(n, "S", "H", "D", 0);
        // PrintRev(str, str.length()-1);
        // PrintEle("Hello", 'e', 0);
        // IsSorted(arr, 0);
        // XatEnd("Hexxllxoxx", 0, "", 0);
        // RemovDup("hhellloo", 0, "", arr);

        sc.close();
    }
}
