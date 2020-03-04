package sorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtilityFuncs {
	
	public static int[] doRead(String path) throws FileNotFoundException {
		File file = new File(System.getProperty("user.dir")+"/Files/" + path);
		Scanner sc = new Scanner(file);
		
		//Read in the random numbers from the file
		List<Integer> arr = new ArrayList<Integer>();
		while(sc.hasNextLine()) {
			arr.add(Integer.parseInt(sc.nextLine()));
		}
		sc.close();
		
		int[] arrInt = new int[arr.size()];
		for(int i = 0; i<arr.size();i++) {
			arrInt[i] = arr.get(i);
		}
		
		return arrInt;
	}
	
	public static void printArr(int[] arr) {
		for (int i = 0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static boolean isSorted(int a[]) {
		int n = a.length;
		for (int i=0; i<n-1; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}
	
}
