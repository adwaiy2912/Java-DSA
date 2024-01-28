import java.util.*;

public class DP {
    // Fibonacii series - nth term -> sum of prev 2 terms
    // Fibo. series - Recursion
    public static int fib1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib1(n-1) + fib1(n-2);
    }

    // Fibo. series - Memorization
    public static int fib2(int n, int f[]) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (f[n] != 0) {
            return f[n];
        }
        f[n] = fib2(n-1, f) + fib2(n-2, f);
        return f[n];
    }

    // Fibo. series - Tabulation
    public static int fib3(int n) {
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // Climbing stairs - count ways -> reach nth stair; can climb -> 1 or 2 stairs
    // Climb stairs - recursion, O(2^n) & O(1)
    public static int countWays1(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        return countWays1(n-1) + countWays1(n-2);
    }

    // Climb stairs - memorization, O(n) & O(n)
    public static int countWays2(int n, int ways[]) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (ways[n] != -1) {
            return ways[n];
        }

        ways[n] = countWays2(n-1, ways) + countWays2(n-2, ways);
        return ways[n];
    }

    // Climb stairs - tabluation, O(n) & O(n)
    public static int countWays3(int n) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 0-1 knapsack - max. val stored in bag -> of weight(W) -> by wt-val pair array
    // 0-1 knapsack - recursion, O(2^n) & O(1)
    public static int knapsack1(int val[], int wt[], int W, int i) {
        if (W == 0 || i == 0) {
            return 0;
        }

        if (wt[i-1] <= W) {  // valid
            // include curr item
            int ans1 = val[i-1] + knapsack1(val, wt, W-wt[i-1], i-1);
            // exclude curr item
            int ans2 = knapsack1(val, wt, W, i-1);

            return Math.max(ans1, ans2);
        }
        else {  // invalid
            return knapsack1(val, wt, W, i-1);
        }
    }
    
    // 0-1 knapsack - memorization, O(n*W) & O(n*W)
    public static int knapsack2(int val[], int wt[], int W, int i, int sack[][]) {
        if (W == 0 || i == 0) {
            return 0;
        }
        if (sack[i][W] != -1) {
            return sack[i][W];
        }

        if (wt[i-1] <= W) {
            int ans1 = val[i-1] + knapsack2(val, wt, W-wt[i-1], i-1, sack);
            int ans2 = knapsack2(val, wt, W, i-1, sack);
            sack[i][W] = Math.max(ans1, ans2);
            return sack[i][W];
        }
        else {  // invalid
            sack[i][W] = knapsack2(val, wt, W, i-1, sack);
            return sack[i][W];
        }
    }

    // 0-1 knapsack - tabluation, O(n*W) & O(n*W)
    public static int knapsack3(int val[], int wt[], int W) {
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        // dp(i, j) -> maxProfit for, i = no. of pieces and j = knapsack weight
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                int v = val[i-1], w = wt[i-1];
                if (w <= j) {   //valid
                    int incProfit = v + dp[i-1][j-w];
                    int excProfit = dp[i-1][j];
                    dp[i][j] = Math.max(incProfit, excProfit);
                } else {   //invalid
                    dp[i][j] = dp[i-1][j];
                }

            }
        }
        return dp[n][W];
    }

    // unbounded knapsack - tabluation, O(n*W) & O(n*W)
    public static int knapsack4(int val[], int wt[], int W) {
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                int v = val[i-1], w = wt[i-1];
                if (w <= j) {   //valid
                    int incProfit = v + dp[i][j-w];   // changed i-1 -> i, for 0-1 to unbounded knapsack
                    int excProfit = dp[i-1][j];
                    dp[i][j] = Math.max(incProfit, excProfit);
                } else {   //invalid
                    dp[i][j] = dp[i-1][j];
                }

            }
        }
        return dp[n][W];
    }
   
    // variation of 0-1 knapsack - tabluation, O(n*W) & O(n*W)
    public static boolean targetSumSubSet(int arr[], int sum) {
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][sum+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                int v = arr[i-1];
                if (v <= j && dp[i-1][j-v]) {   //include
                    dp[i][j] = true;
                } else if (dp[i-1][j]) {   //exclude
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][sum];
    }

    // rod cutting - max val. -> of rod len (L) -> by cutting -> given length-price pair array
    // logic - same as 0-1 knapsack
    // rod cutting - tabluation, O(n*len) & O(n*len)
    public static int rodCutting(int lengths[], int pieces[], int L) {
        int n = lengths.length;
        int dp[][] = new int[n+1][L+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= L; j++) {
                if (lengths[i-1] <= j) {
                    dp[i][j] = Math.max(pieces[i-1] + dp[i-1][j-lengths[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][L];
    }

    // longest common subsequence - len of LCS -> 2 strings, str1 & str2 (not continious substr)
    // longest common subsequence - recursion, O(2^(n*m)) & O(1)
    public static int longComSubSeq1(String str1, String str2, int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (str1.charAt(i-1) == str2.charAt(j-1)) {
            return 1 + longComSubSeq1(str1, str2, i-1, j-1);
        }
        int ans1 = longComSubSeq1(str1, str2, i, j-1);
        int ans2 = longComSubSeq1(str1, str2, i-1, j);
        return Math.max(ans1, ans2);
    }

    // longest common subsequence - memorization, O(n*m) & O(n*m)
    public static int longComSubSeq2(String str1, String str2, int i, int j, int lcs[][]) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (lcs[i-1][j-1] != -1) {
            return lcs[i-1][j-1];
        }

        if (str1.charAt(i-1) == str2.charAt(j-1)) {
            lcs[i-1][j-1] = 1 + longComSubSeq2(str1, str2, i-1, j-1, lcs);
        } else {
            int ans1 = longComSubSeq2(str1, str2, i-1, j, lcs);
            int ans2 = longComSubSeq2(str1, str2, i, j-1, lcs);
            lcs[i-1][j-1] = Math.max(ans1, ans2);
        }
        return lcs[i-1][j-1];
    }
    
    // longest common subsequence - tabulation, O(n*m) & O(n*m)
    public static int longComSubSeq3(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int dp[][] = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    // longest common substring - len of LCS -> 2 strings, str1 & str2 (continious substr)
    // logic - similar to longest common subsequence
    // longest common substring - tabulation, O(n*m) & O(n*m)
    public static int longComSubStr(String str1, String str2) {
        int n = str1.length(), m = str2.length(), ans = 0;
        int dp[][] = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;
    }

    // longest inceasing subsequence - len of LIS -> array subseq in sorted asc. (unique ele)
    // logic - similar to longest common subsequence
    // longest inceasing subsequence - tabulation, O(n*m) & O(n*m)
    public static int longIncSubSeq(int arr[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : arr) {
            set.add(x);
        }
        int arr2[] = new int[set.size()], y = 0;
        for (int x : set) {
            arr2[y++] = x;
        }
        Arrays.sort(arr2);
        int n = arr.length, m = arr2.length;
        int dp[][] = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i-1] == arr2[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    // edit distance - no. of addition, deletion and replace operations -> convert str1 to str2
    // logic - similar to longest common subsequence
    // edit distance - tabulation, O(n*m) & O(n*m)
    public static int editDist(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int dp[][] = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int add = dp[i][j-1] + 1;
                    int del = dp[i-1][j] + 1;
                    int rep = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(add, Math.min(del, rep));
                }
            }
        }
        return dp[n][m];
    }

    // string conversion - no. of addition, deletion and replace operations -> convert str1 to str2
    // logic - similar to longest common subsequence
    // string conversion - tabulation, O(n*m) & O(n*m)
    public static int strConversion(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int dp[][] = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int add = dp[i][j-1] + 1;
                    int del = dp[i-1][j] + 1;
                    dp[i][j] = Math.min(add, del);
                }
            }
        }
        return dp[n][m];
    }

    // wildcard matching - text (s) matches to wildcard pattern (p); using wildcard pattern matching algo.;
    //                     '?' -> single char; '*' -> any seq of chars (inc empty seq)
    // logic - similar to longest common subsequence
    // wildcard matching - tabulation, O(n*m) & O(n*m)
    public static boolean wildCardMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean dp[][] = new boolean[n+1][m+1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(i-1) == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }

    // catalan number - nth term -> sum of product of ith term and (n-i-1)th term pairs -> i from 0 to n-1
    // catalan number - recursion, O(2^n) & O(1)
    public static int catlan1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += catlan1(i) * catlan1(n-i-1);
        }
        return ans;
    }
    
    // catalan number - memorization, O(n*n) & O(n)
    public static int catlan2(int n, int cat[]) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (cat[n] != -1) {
            return cat[n];
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += catlan2(i, cat) * catlan2(n-i-1, cat);
        }
        return cat[n] = ans;
    }

    // catalan number - tabulation, O(n*n) & O(n)
    public static int catlan3(int n) {
        int dp[] = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n ; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }

    // counting BST - no. of BSTs -> given n no. of nodes
    // logic - same as catlan
    // counting BST - tabulation, O(n*n) & O(n)
    public static int countBST(int n) {
        int dp[] = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n ; i++) {
            for (int j = 0; j < i; j++) {
                int left = dp[j], right = dp[i-j-1];
                dp[i] += left * right;
            }
        }
        return dp[n];
    }

    // mountain ranges - no. of types of mountain ranges -> given n, up and down strokes;
    //                   no. of down strokes <= no. of up strokes at any time
    // logic - same as catlan
    // mountain ranges - tabulation, O(n*n) & O(n)
    public static int mountainRanges(int n) {
        int dp[] = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n ; i++) {
            for (int j = 0; j < i; j++) {
                int inside = dp[j], outside = dp[i-j-1];
                dp[i] += inside * outside;
            }
        }
        return dp[n];
    }
    
    // matrix chain multiplication - min val. of order of chain of multi. 
    // matrix chain multiplication - recursion, O(2^n) & O(1)
    public static int mcm1(int arr[], int i, int j) {
        if (i == j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = mcm1(arr, i, k);   //Ai...Ak -> arr[i-1] x arr[k]
            int cost2 = mcm1(arr, k+1, j);   //A(k+1)...Aj -> arr[k] x arr[j]
            int cost3 = arr[i-1]  * arr[k] * arr[j];
            minCost = Math.min(minCost, cost1 + cost2 + cost3);
        }
        return minCost;
    }

    // matrix chain multiplication - memorization, O(n^3) & O(n^2)
    public static int mcm2(int arr[], int i, int j, int mat[][]) {
        if (i == j) {
            return 0;
        }
        if (mat[i][j] != -1) {
            return mat[i][j];
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = mcm2(arr, i, k, mat);
            int cost2 = mcm2(arr, k+1, j, mat);
            int cost3 = arr[i-1]  * arr[k] * arr[j];
            minCost = Math.min(minCost, cost1 + cost2 + cost3);
        }
        return mat[i][j] = minCost;
    }

    // matrix chain multiplication - tabulation, O(n^3) & O(n^2)
    public static int mcm3(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        // dp(i, j) -> min cost for, i < j; i = start index and j = last index
        for (int len = 2; len < n; len++) {
            for (int i = 1; i <= n-len; i++) {
                int j = i+len-1;
                // traversing upper triangular matrix(dp) diagonally
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost1 = dp[i][k];
                    int cost2 = dp[k+1][j];
                    int cost3 = arr[i-1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost1 + cost2 + cost3);
                }
            }
        }
        return dp[1][n-1];
    }

    // minimum partitioning - min abs. diff. btw 2 partitioned subarrays
    // logic - similar to knapsack
    // minimum partitioning - tabulation, O(n*W) & O(n*W)
    public static int minPartition(int arr[]) {
        int n = arr.length, sum = 0, W;

        for (int x : arr) {
            sum += x;
        }
        W = sum/2;
        int dp[][] = new int[n+1][W+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (arr[i-1] <= j) {
                    dp[i][j] = Math.max(arr[i-1] + dp[i-1][j-arr[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int sum2 = dp[n][W];
        int sum1 = sum - sum2;
        return Math.abs(sum1 - sum2);
    }

    // min array jumps - min no. of jumps req. to reach (n-1)th index
    // min array jumps - tabulation, O(n^2) & O(n)
    public static int minJumps(int arr[]) {
        int n = arr.length;
        int dp[] = new int[n];

        for (int i = n-2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= arr[i]; j++) {
                dp[i] = Math.min(dp[i+j] + 1, dp[i]);
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        // int n = 8;

        // System.out.println(fib1(n));
        // int f[] = new int[n+1];
        // System.out.println(fib2(n, f));
        // System.out.println(fib3(n));

        // int ways[] = new int[n+1];
        // Arrays.fill(ways, -1);
        // System.out.println(countWays1(n));
        // System.out.println(countWays2(n, ways));
        // System.out.println(countWays3(n));

        // int val[] = {15, 14, 10, 45, 30};
        // int wt[] = {2, 5, 1, 3, 4};
        // int sack[][] = new int[val.length+1][n+1];
        // for (int[] arr: sack) {
        //     Arrays.fill(arr, -1);
        // }
        // System.out.println(knapsack1(val, wt, n, val.length));
        // System.out.println(knapsack2(val, wt, n, val.length, sack));
        // System.out.println(knapsack3(val, wt, n));
        // System.out.println(knapsack4(val, wt, n));
        // System.out.println(targetSumSubSet(wt, 10));

        // int lengths[] = {1, 2, 3, 4, 5, 6, 7, 8}; // pieces length
        // int pieces[] = {1, 5, 8, 9, 10, 17, 17, 20};
        // System.out.println(rodCutting(lengths, pieces, n));

        // String str1 = "abcgge", str2 = "ebecgg";
        // int lcs[][] = new int[str1.length()+1][str2.length()+1];
        // for (int arr[]: lcs) {
        //     Arrays.fill(arr, -1);
        // }
        // System.out.println(longComSubSeq1(str1, str2 , str1.length(), str2.length()));
        // System.out.println(longComSubSeq2(str1, str2, str1.length(), str2.length(), lcs));
        // System.out.println(longComSubSeq3(str1, str2));

        // System.out.println(longComSubStr(str1, str2));
        // int arr[] = {50, 3, 10, 7, 40, 80};
        // System.out.println(longIncSubSeq(arr));
        // System.out.println(editDist(str1, str2));
        // System.out.println(strConversion(str1, str2));
        // String str = "baaabab", pattern = "******ba***ab";
        // System.out.println(wildCardMatch(str, pattern));

        // System.out.println(catlan1(8));
        // int cat[] = new int[n+1];
        // Arrays.fill(cat, -1);
        // System.out.println(catlan2(n, cat));
        // System.out.println(catlan3(n));
        // System.out.println(countBST(n));
        // System.out.println(mountainRanges(n));

        // int arr[] = {1, 2, 3, 4, 3};
        // int n = arr.length;
        // System.out.println(mcm1(arr, 1, n-1));
        // int mat[][] = new int[n][n];
        // for (int a[]: mat) {
        //     Arrays.fill(a, -1);
        // }
        // System.out.println(mcm2(arr, 1, n-1, mat));
        // System.out.println(mcm3(arr));

        // int nums[] = {1, 6, 11, 5};
        // System.out.println(minPartition(nums));
        int jumps[] = {2, 2, 1, 1, 4};
        System.out.println(minJumps(jumps));
    }
}
