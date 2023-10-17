package core;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {40,10,20,30,50};
        Arrays.sort(arr);
        int last = arr.length-1;
        int key = 50;
        binarySearch(arr, last, 0, key);
    }
    public static void binarySearch(int[] arr, int last, int start, int key){
        while(start <= last){
            int mid = (start + last)/2;
            System.out.println("Start"+start+"-Last-"+last+"-Mid->"+mid);
            if(arr[mid] < key){
                start = mid + 1;
            }else if(arr[mid] == key){
                System.out.println("Index:-->"+(mid+1)+"Va->"+arr[mid]);
                break;
            }else{
                last = mid-1;
            }
        }
        if ( start > last ){
            System.out.println("Element is not found!");
        }
    }
}
