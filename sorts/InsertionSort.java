package sorts;

public class InsertionSort extends Sort {
	private int len;
	
	public InsertionSort(int[] arr) {
		super(arr);
		this.len = arr.length;
	}
	
	@Override
	public int[] doSort() {
		for (int j = 1; j<this.len; j++) {
			int key = arr[j];
			int i = j-1;
			while (i>=0 && arr[i]>key) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
		}
		
		return arr;
	}
	
	@Override
	public String toString() {
		return "Insertion Sort";
	}

}
