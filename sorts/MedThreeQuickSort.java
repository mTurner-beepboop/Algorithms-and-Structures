package sorts;

public class MedThreeQuickSort extends Sort {
	//I broke it
	private int lo, hi, mid;
	
	public MedThreeQuickSort(int arr[]) {
		super(arr);
		this.lo = 0;
		this.hi = arr.length-1;
		this.mid = (arr.length-1)/2;
	}
	
	private MedThreeQuickSort(int arr[], int lo, int hi) {
		super(arr);
		this.lo = lo;
		this.hi = hi;
		this.mid = (hi-lo)/2;
	}
	
	@Override
	public int[] doSort() {
		if(this.lo<this.hi) {
			int q = this.partition(arr, lo, hi);
			Sort s1 = new MedThreeQuickSort(arr, lo, q-1);
			arr = s1.doSort();
			Sort s2 = new MedThreeQuickSort(arr, q+1, hi);
			arr = s2.doSort();
		}
		return arr;
		
	}
	
	private int median() {
		//Make sure they are in order
		if (arr[hi] < arr [lo]) {
			swap(hi,lo);
		}
		if (arr[mid] < arr[lo]) {
			swap(mid,lo);
		}
		if (arr[hi] < arr[mid]) {
			swap(hi,mid);
		}
		
		//Return the middle
		return mid;
		
	}
	
	private int partition(int[] arr, int lo, int hi) {
		int x = arr[median()];
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
		return "Median of Three Quick Sort";
	}

}
