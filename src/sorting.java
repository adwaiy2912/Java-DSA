import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sorting {

    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int tempNum = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tempNum;
                }
            }
        }
    }

    public static void selectionSort(int arr[]) {
        for (int i = 0; i < arr.length-1; i++) {
            int smallestIndex = i; 
            for (int j = i+1; j < arr.length; j++) {
                if (arr[smallestIndex] > arr[j]) {
                    smallestIndex = j;
                }
            }
            int temp = arr[smallestIndex];
            arr[smallestIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] > arr[j-1]) {
                    break;
                } else {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public static void shellSort(int arr[]) {
        // gap -> arr.length/2 & gap /= 2 decrement
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int ele = arr[i];
                int j = i;
                while (j >= gap && arr[j-gap] > ele) {
                    arr[j] = arr[j-gap];
                    j -= gap;
                }
                arr[j] = ele;
            }
        }
    }
    
    // counting sort -> makes assumptions; counts no. of occurances
    // val are within a specific range; range = max - min +1
    public static void countingSort(int arr[]) {
        int min = arr[0], max = arr[0];
        for (int i : arr) {
            if (i < min) min = i;
            if (i > max) max = i;
        }

        int countArr[] = new int[max-min+1];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]-min]++;
        }

        int j = 0;
        for (int i = min; i <= max; i++) {
            while (countArr[i-min] > 0) {
                arr[j++] = i;
                countArr[i-min]--;
            }
        }
    }

    // radix sort ->
    public static void radixSort(int arr[], int radix, int width) {
        for (int i = 0; i < width; i++) {
            radixSingleSort(arr, i , radix);
        }
    }

    public static void radixSingleSort(int arr[], int pos, int radix) {
        int len = arr.length;
        int countArr[] = new int[radix];

        for (int val : arr) {
            countArr[(val / (int)Math.pow(radix, pos)) % radix]++;
        }
        for (int j = 1; j < radix; j++) {
            countArr[j] += countArr[j-1]; 
        }

        int temp[] = new int[len];
        for (int tempidx = len-1; tempidx >= 0; tempidx--) {
            temp[--countArr[(arr[tempidx] / (int)Math.pow(radix, pos))%radix]] = arr[tempidx];
        }

        for (int tempidx = 0; tempidx < len; tempidx++) {
            arr[tempidx] = temp[tempidx];
        }
    }

    private static int hash(int val) {
        return val/10;  //since all values are btw 0-100 -> returns 0-10 (buckets.length)
    }

    @SuppressWarnings("unchecked")
    public static void bucketSort(int arr[]) {
        List<Integer>[] buckets = new List[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < arr.length; i++) {
            buckets[hash(arr[i])].add(arr[i]);
        }
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int pos = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int val : buckets[i]) {
                arr[pos++] = val;
            }
        } 
    }

    public static void main(String[] args) {
        int arr[] = {82,56,98,51,32,72,1};

        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);
        // countingSort(arr);
        // radixSort(arr, 10, 1);
        // bucketSort(arr);
        
        for (int i : arr) System.out.print(i + " ");
    }    
}
