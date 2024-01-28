public class segmentTree {
    static int tree[];

    public static void init(int n) {
        tree = new int[4*n];
    }

    public static void buildST(int arr[], int idxST, int start, int end) {
        if (start == end) {
            tree[idxST] = arr[start];
            return;
        }
        int mid = start + (end-start)/2;
        
        buildST(arr, 2*idxST+1, start, mid); // left subtree
        buildST(arr, 2*idxST+2, mid+1, end); // right subtree

        tree[idxST] = tree[2*idxST+1] + tree[2*idxST+2];
    }

    private static int getSum(int idxSt, int si, int sj, int qi, int qj) {
        if (qj < si || qi > sj) {
            return 0;
        } else if (si >= qi && sj <= qj) {
            return tree[idxSt];
        } else {
            int mid = (si + sj)/2;
            int left = getSum(2*idxSt+1, si, mid, qi, qj);
            int right = getSum(2*idxSt+2, mid+1, sj, qi, qj);
            return left + right;
        }
    }
    public static int getSum(int arr[], int qi, int qj) {
        int n = arr.length;
        return getSum(0, 0, n-1, qi, qj);
    }

    private static void update(int idxST, int si, int sj, int idx, int diff) {
        if (idx > sj || idx < si) {
            return;
        }

        tree[idxST] += diff;
        if (si != sj) {
            int mid = (si + sj)/2;
            update(2*idxST+1, si, mid, idx, diff);
            update(2*idxST+2, mid+1, sj, idx, diff);
        }
    }
    public static void update(int arr[], int idx, int newVal) {
        int n = arr.length;
        int diff = newVal - arr[idx];
        arr[idx] = newVal;

        update(0, 0, n-1, idx, diff); 
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        int n = arr.length;
        init(n);
        buildST(arr, 0, 0, n-1);

        
        System.out.println(getSum(arr, 2, 5));
        update(arr, 4, 10);
        System.out.println(getSum(arr, 2, 5));
    }
}
