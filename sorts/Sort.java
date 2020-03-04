package sorts;

public abstract class Sort {
	protected int[] arr;
	
	public Sort(int[] arr) {
		this.arr = arr;
	}
	
	protected void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public abstract int[] doSort();
	
	public long timeSort() {
		long time1 = System.currentTimeMillis();
		this.doSort();
		long timetaken = System.currentTimeMillis() - time1;
		return timetaken;
	}
}
