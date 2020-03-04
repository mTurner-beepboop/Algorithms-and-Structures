package sorts;

public class PathologicalArray {

	public static int[] generatePathologicalArray(int n) {
		// Need to make an array such that every element picked as pivot is as close as
		// possible to the maximum or minimum number of the array. In this case I think
		// I'll choose the minimum. So for every recursive call, the array must have the
		// lowest number selected as pivot, ie, the first, mid and last element of the
		// subarray must be as low as possible. This is probably easiest to generate
		// with a recursive method
		int[] arr = new int[n];
		int max = n-1;
		int min = 0;
		int mid = n/2;
		
		int count = 1;
		
		if (arr[max] == 0) {
			arr[max] = count;
			count++;
		}
		if (arr[mid] == 0) {
			arr[mid] = count;
			count++;
		}
		if (arr[min] == 0) {
			arr[min] = count;
			count++;
		}
		Object[] ret = generateHelper(arr, min+1, mid-1, count);
		arr = (int[]) ret[0];
		count = (int) ret[1];
		ret = generateHelper(arr, mid+1, max-1, count);
		arr = (int[]) ret[0];
		count = (int) ret[1];
		
		//Fill out the rest of the elements if they are empty
		int i = 0;
		while(i<n-1) {
			if (arr[i]==0) {
				arr[i] = count;
				count++;
			}
			i++;
		}
		
		return arr;
	}
	
	private static Object[] generateHelper(int[] arr, int first, int last, int count) {
		int mid = (first+last)/2;
		if (mid == first || mid == last) {
			Object[] ret = {arr, count};
			return ret;
		}
		if (arr[last] == 0) {
			arr[last] = count;
			count++;
		}
		if (arr[mid] == 0) {
			arr[mid] = count;
			count++;
		}
		if (arr[first] == 0) {
			arr[first] = count;
			count++;
		}
		Object[] ret = generateHelper(arr, first+1, mid-1, count);
		arr = (int[]) ret[0];
		count = (int) ret[1];
		ret = generateHelper(arr, mid+1, last-1, count);

		return ret;
	}
}
