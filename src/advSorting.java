public class advSorting {

    // merge sort -> O(nlogn) & O(n)
    private static void conquer(int arr[], int start, int mid, int end) {
        int[] merged = new int[end-start+1];

        int idx = 0;
        int idx1 = start;
        int idx2 = mid+1;
        while (idx1 <= mid && idx2 <= end) {
            if (arr[idx1] <= arr[idx2]) {
                merged[idx++] = arr[idx1++];
            } else {
                merged[idx++] = arr[idx2++];
            }
        }
        while (idx1 <= mid) {
            merged[idx++] = arr[idx1++];
        }
        while (idx2 <= end) {
            merged[idx++] = arr[idx2++];
        }

        for (int i = 0, j = start; i < merged.length; i++, j++) {
            arr[j] = merged[i];
        }
    }

    private static void divide(int arr[], int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end-start)/2;
        divide(arr, start, mid);
        divide(arr, mid+1, end);

        conquer(arr, start, mid, end);
    }

    public static void mergeSort(int arr[]) {
        divide(arr, 0, arr.length-1);

        return;
    }

    // quick sort -> O(n^2), θ(nlogn) & O(logn)
    private static int partition(int arr[], int low, int high) {
        // considering pivot as the last element
        int pivot = arr[high];
        int  i = low-1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                int temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[++i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    private static void pivot(int arr[], int low, int high) {
        if (low >= high) {
            return;
        }
        int pidx = partition(arr, low, high);

        pivot(arr, low, pidx-1);
        pivot(arr, pidx+1, high);
    }

    public static void quickSort(int arr[]) {
        pivot(arr, 0, arr.length-1);

        return;
    }
    public static void main(String[] args) {
        int arr[] = {6, 3, 9, 5, 2, 8};

        // mergeSort(arr);
        // quickSort(arr);
        
        for (int i : arr) System.out.print(i + " ");
    }
}
