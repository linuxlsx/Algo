import java.util.*;

public class InsertSort{

    /**
     * complexity is O(n2)
     * performance adapts to the initial order of elements
     * insertion sort is used on quite small data sets
     * properties:
     *  stable : insertion sort retains relative order of the same elements
     *  in place
     *
     *  http://www.algolist.net/Algorithms/Sorting/Insertion_sort
     */
    public static void sort(int[] arrays){
        
        for (int i = 0; i < arrays.length; i++){
            int tmp = arrays[i];

            int j = i;
            while(j > 0 && arrays[j - 1] > tmp){
                arrays[j] = arrays[j - 1];
                j--;
            }

            arrays[j] = tmp;
        }

    }

    public static void main(String[] args){
        
        int[] arr = new int[]{ 9,5,7,3,1,4,7,8,3,6,0};

        sort(arr);

        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
