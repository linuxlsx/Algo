import java.util.*;

public class SelectSort{

    public static void sort(int[] arrays){
        
        for (int i = 0; i < arrays.length; i++){
            int minIndex = i;
            for (int j = i + 1; j < arrays.length; j++){
                if(arrays[minIndex] > arrays[j]){
                    minIndex = j;
                }
            }

            if(minIndex != i){
                int tmp = arrays[minIndex];
                arrays[minIndex] = arrays[i];
                arrays[i] = tmp;
            }
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
