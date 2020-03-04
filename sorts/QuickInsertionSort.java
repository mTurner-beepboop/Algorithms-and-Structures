package sorts;

public class QuickInsertionSort extends Sort {
	
	private int lo;
	private int hi;
	private int cutoff;
	
	public QuickInsertionSort(int[] arr, int cutoff) {
		super(arr);
		this.lo = 0;
		this.hi = arr.length-1;
		this.cutoff = cutoff;
	}
	
	private QuickInsertionSort(int[] arr, int lo, int hi, int cutoff) {
		super(arr);
		this.lo = lo;
		this.hi  = hi;
		this.cutoff = cutoff;
	}
	
	@Override
	public int[] doSort() {
		//Sort the array up to the cutoff point
		arr = this.doQuickSort();
		//Sort the rest of the array with Insertion Sort
		Sort s = new InsertionSort(arr);
		arr = s.doSort();
		return arr;
	}
	
	private int[] doQuickSort() {
		if (this.lo<this.hi) {
			//If the array is less than the cutoff point, return without sorting
			if (this.cutoff > this.hi-this.lo) {
				return arr;
			}
			//Otherwise, continue with quick sort
			int q = this.partition(arr, lo, hi);
			QuickInsertionSort s1 = new QuickInsertionSort(arr, lo ,q-1 ,cutoff);
			arr = s1.doQuickSort();
			//Reaches a problem on the below line for larger files - ie >20k
			QuickInsertionSort s2 = new QuickInsertionSort(arr, q+1, hi, cutoff);
			arr = s2.doQuickSort();
		}
		
		return arr;

	}
	

	private int partition(int[] arr, int lo, int hi) {
		int x = arr[hi];
		int i = lo-1;
		for (int j = lo; j<=hi-1; j++) {
			if(arr[j] <= x) {
				i = i+1;
				swap(i, j);
			}
		}
		swap(i+1, hi);
		
		return i+1;
	}
	
	@Override
	public String toString() {
		return "Quick Sort with Insertion Sort on subarrays of "+cutoff;
	}

}
