package sorts;

public class ThreeWayQuickSort extends Sort {

	private int lo, hi;

	// Constructor used in creation of initial sort
	public ThreeWayQuickSort(int[] arr) {
		super(arr);
		this.lo = 0;
		this.hi = arr.length - 1;
	}

	// Constructor used in recursive call to sort
	private ThreeWayQuickSort(int[] arr, int lo, int hi) {
		super(arr);
		this.lo = lo;
		this.hi = hi;
	}

	// Carries out the actual sort
	@Override
	public int[] doSort() {
		if (this.hi > this.lo) { // Use the base quicksort sorting method utilising 2 pivots instead
			int[] piv = this.partition(arr, lo, hi);

			Sort s1 = new ThreeWayQuickSort(arr, lo, piv[0] - 1);
			arr = s1.doSort();
			Sort s2 = new ThreeWayQuickSort(arr, piv[1] + 1, hi);
			arr = s2.doSort();
		}
		return arr;
	}

	// Generates pivot points from the lowest indexed point, ordering the array such
	// that the elements lower are on the left and vice versa. The variable greater
	// will keep track of the upper bounds of the elements with the same value as
	// the lower pivot.
	private int[] partition(int[] arr, int lo, int hi) {
		int lower = lo; // The lower pivot
		int greater = hi; // The higher pivot
		int el = arr[lo]; // Element to compare to
		int i = lo + 1; // The index of the next element

		while (i <= greater) {
			if (arr[i] < el) { // If the indexed value is less than the low pivot
				swap(lower, i); // Swap the values and increment both the index and the lower pivot
				lower++;
				i++;
			} else if (arr[i] > el) { // If the indexed value is greater than the low pivot
				swap(i, greater); // Swap with index and lower the higher pivot by one
				greater = greater - 1;
			} else { // If the values are the same
				i++; // Increment the index
			}
		}

		int[] r = { lower, greater }; // return the 2 pivots that have been generated within the function
		return r;
	}

	@Override
	public String toString() {
		return "Three Way Quick Sort";//codesillygivsquish
	}

}
