package sorts;

public class MergeSort extends Sort {
	
	private int lo, hi;
	
	public MergeSort(int[] arr) {
		super(arr);
		this.lo = 0;
		this.hi = arr.length-1;
	}
	
	public MergeSort(int[] arr, int lo, int hi) {
		super(arr);
		this.lo = lo;
		this.hi = hi;
	}
	
	@Override
	public int[] doSort() {
		if (lo<hi) {
			int mid = (lo+hi)/2;
			Sort s1 = new MergeSort(arr, lo, mid);
			arr = s1.doSort();
			Sort s2 = new MergeSort(arr, mid+1, hi);
			arr = s2.doSort();
			this.merge(arr, lo, mid, hi);
		}
		return arr;
	}
	
	private int[] merge(int[] arr, int lo, int mid, int hi) {
		int n1 = mid - lo + 1;
		int n2 = hi - mid;
		
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i=0; i<n1; i++) {
			L[i] = arr[lo + i];
		}
		for (int j=0; j<n2; j++) {
			R[j] = arr[mid + 1+ j];
		}
		
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		for (int k=lo; k<= hi; k++){
			if(L[i] <= R[j]){
				arr[k] = L[i];
				i++;
			}
			else{
				arr[k] = R[j];
				j++;
			}
		}
		
		return arr;
		
	}

}
