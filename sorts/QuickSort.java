package sorts;

public class QuickSort extends Sort {

	int lo;
	int hi;
	
	//Used in the initial call to the sort
	public QuickSort(int[] arr) {
		super(arr);
		this.lo = 0;
		this.hi = arr.length-1;
	}
	
	//Used in the recursive call to the sort
	private QuickSort(int[] arr, int lo, int hi) {
		super(arr);
		this.lo = lo;
		this.hi = hi;
	}
	
	@Override
	public int[] doSort() {
		if(this.lo<this.hi) {
			int q = this.partition(arr, lo, hi);
			Sort s1 = new QuickSort(arr, lo, q-1);
			arr = s1.doSort();
			Sort s2 = new QuickSort(arr, q+1, hi);
			arr = s2.doSort();
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
		return "QuickSort";
	}

}
