import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class advRecursion {

    public static void subSequences(String str, int index, String finalStr) {
        if (str.length() == index) {
            System.out.println(finalStr);
            return;
        }
        subSequences(str, index+1, finalStr+str.charAt(index));
        subSequences(str, index+1, finalStr+" ");
    }

    public static void unqSubSeq(String str, int index, String finalStr, HashSet<String> set) {
        if (str.length() == index) {
            if (set.contains(finalStr)) {
                return;
            } else {
                System.out.println(finalStr);
                set.add(finalStr);
                return;
            }
        }
            unqSubSeq(str, index+1, finalStr+str.charAt(index), set);
            unqSubSeq(str, index+1, finalStr, set);
    }

    private static String keypad[] = {".", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
    public static void keyComb(String str, int index, String comb) {
        if (str.length() == index) {
            System.out.println(comb);
            return;
        }
        String keyMap = keypad[str.charAt(index) - '0'];
        for (int i = 0; i < keyMap.length(); i++) {
            keyComb(str, index+1, comb+keyMap.charAt(i));
        }
    }

    public static void permutations(String str, String finalStr) {
        if (str.length() == 0) {
            System.out.println(finalStr);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String newStr = str.substring(0, i) + str.substring(i+1);
            permutations(newStr, finalStr+str.charAt(i));
        }

    }

    public static int totalPaths(int n, int m, int i, int j) {
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        if (i == n || j == m) {
            return 0;
        }

        int downPaths = totalPaths(n, m, i+1, j);
        int rightPaths = totalPaths(n, m, i, j+1);
        return downPaths + rightPaths;
    }

    public static int placeTiles(int n, int m) {
        if (n == m) {
            return 2;
        }
        if (n < m) {
            return 1;
        }
        // vertical placement
        int vp = placeTiles(n-m, m);
        // horizontal placement
        int hp = placeTiles(n-1, m); 
        return vp + hp;
    }

    public static int callGuests(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int single = callGuests(n-1);
        int pair = (n-1)*callGuests(n-2);

        return single + pair;
    }
 
    public static void subsets(int n, ArrayList<Integer> lst) {
        if (n == 0) {
            System.out.println(lst);
            return;
        }

        lst.add(n);
        subsets(n-1, lst);

        lst.remove(lst.size()-1);
        subsets(n-1, lst);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // subSequences("abc", 0, "");
        // unqSubSeq("aaa", 0, "", set);
        // keyComb("13", 0, "");
        // permutations("abc", "");
        // totalPaths(10, 10, 0, 0);
        // placeTiles(4, 2);
        // callGuests(4);
        // subsets(3, lst);
        
        sc.close();
    }
}
