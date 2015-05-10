package mahfouz;

import java.util.ArrayList;
import java.util.Scanner;

//For this radix sort, runtime is O(m*N)= O(2N) [for inputs 0-99] 
//This algorithm works best when the length of the largest number is less than log(N)

public class RadixSort
{
		 public static void radixSort(int[] input) {
		  ArrayList<Integer>[] library = new ArrayList[10];
		  for (int i = 0; i < library.length; i++) {
		    library[i] = new ArrayList<Integer>();
		  }
		  boolean test= false;
		  int ind = -1;
		  int temp = 1;
		  while (!test) {
		    test = true;
		    for (Integer i : input) {
			  if (test && (i/temp) > 0) {
				  test = false;
			  }
		      library[ (i/temp) % 10].add(i);
		    }
		    temp *= 10;
		    int tmp= 0;
		    for(ArrayList<Integer> a : library){
		    	for(Integer m:a){
		    	input[tmp++]=m;
		    	}
		    	a.clear();
		    }
		  }
	 }

    public static void main(String[] args) 
    {
        Scanner in= new Scanner( System.in );        
//        int arr[] = new int[100];
//        System.out.println("Enter 100 integers [Range-> 1 to 100]:");
//        for (int i = 0; i < 5; i++)
//            arr[i] = in.nextInt();
        int arr[] = new int[] {34, 87, 1, 0, 12, 15, 29, 2, 44, 21, 5, 98, 56, 34};
        RadixSort.radixSort(arr);
        System.out.println("Sorted array:");        
        for (int j: arr){
        	 System.out.print(j+" ");         
        }
        System.out.println();                     
    }    
}