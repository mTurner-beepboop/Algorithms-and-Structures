package sorts;

public class SelectionSort extends Sort {
	private int len;
	
	public SelectionSort(int[] arr) {
		super(arr);
		this.len = arr.length;
	}
	
	@Override
	public int[] doSort() {
		for (int i = 0; i<=len-2; i++) {
			
			int index = i;
			
			//Find the min
			for (int j = i+1; j<=len-1; j++) {
				if (arr[j]<arr[index]) {
					index = j;
				}
			}

			swap(index,i);
		
		}
		
		return arr;
	}
	
	@Override
	public String toString() {
		return "Selection Sort";
	}
}
