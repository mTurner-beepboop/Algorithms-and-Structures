package linked;

public class LinkedList<Item> {
	private Node<Item> head;

	private static class Node<Item> {
		private Item key;
		private Node<Item> next;

		public Node(Item key) {
			this.next = null;
			this.key = key;
		}

		public Node(Item key, Node<Item> next) {
			this.next = next;
			this.key = key;
		}

		public void setData(Item key) {
			this.key = key;
		}

		public Item getData() {
			return this.key;
		}

		public void setNext(Node<Item> next) {
			this.next = next;
		}

		public Node<Item> getNext() {
			return this.next;
		}
	}

	public LinkedList() {
		head = null;
	}

	public LinkedList(Node<Item> head) {
		this.head = head;
	}

	/**
	 * Method to find the number of nodes associated with the linked list
	 * 
	 * @return number of nodes in Linked List
	 */
	public int listCount() {
		int count = 0;
		Node<Item> check = this.head;
		while (true) {
			if (check == null) { // If current node is null, return count
				return count;
			} else { // Otherwise move to the next node and increment counter
				check = check.getNext();
				count++;
			}
		}
	}

	/**
	 * Adds a new node to the end of the list
	 * 
	 * @param data
	 */
	public void add(Item data) {

		// Check if linked list is empty, if so, set item node as list head and stop
		if (this.head == null) {
			this.head = new Node<Item>(data);
			return;
		}

		Node<Item> temp = new Node<Item>(data);
		Node<Item> current = head;

		while (current.getNext() != null) {
			current = current.getNext();
		}

		current.setNext(temp);
	}

	/**
	 * Extends the current linked list, adding all the elements of the supplied list
	 * to the end of this one
	 * 
	 * @param linked list of values to add
	 */
	public void extend(LinkedList<Item> data) {
		Node<Item> temp = data.getHead();
		for (int i = 0; i<data.listCount(); i++) {
			this.add(temp.getData());
			temp = temp.getNext();
		}
	}

	/**
	 * Inserts a new node at the position specified, unless the position is out of
	 * bounds, in which case it will add to the end of the list
	 * 
	 * NOTE: position is indexed from 0
	 * 
	 * @param data
	 * @param position
	 */
	public void insert(Item data, int position) {

		// Check if the list is empty, if so, set head to item node
		if (this.head == null) {
			this.head = new Node<Item>(data);
			return;
		}

		Node<Item> check = this.head;
		Node<Item> toAdd = new Node<Item>(data);

		// Check if the index oints to the head (is 0)
		if (position == 0) {
			this.head = toAdd;
			toAdd.setNext(check);
			return;
		}

		for (int i = 1; i < position; i++) {
			// In this case, the position is out of bounds, so item is inserted into last
			// position
			if (check.getNext() == null) {
				check.setNext(toAdd);
				return;
			}
			check = check.getNext();
		}

		// Should insert the new data into the desired position and join the split list
		Node<Item> temp = check.getNext();
		check.setNext(toAdd);
		toAdd.setNext(temp);
	}

	/**
	 * Get the data contained at the specified index of the linked list
	 * 
	 * NOTE: indexed from 0
	 * 
	 * @param index
	 * @return data at position
	 */
	public Item get(int index) throws OutOfBoundsException {

		// Validate the index input
		if (index < 0) {
			throw new OutOfBoundsException();
		}

		Node<Item> check = head;

		// Iterate over the linked list to the desired index then return the data
		for (int i = 0; i < index; i++) {
			// Check if the next index is null, if so throw exception
			if (check.getNext() == null) {
				throw new OutOfBoundsException();
			}
			check = check.getNext();
		}

		return check.getData();
	}

	/**
	 * Removes the node at the specified point, joining the split list in the
	 * process
	 * 
	 * @param index
	 * @throws OutOfBoundsException
	 */
	public void remove(int index) throws OutOfBoundsException {

		// Check if the index is valid
		if (index < 0 || index > this.listCount()) {
			throw new OutOfBoundsException();
		}

		// Check if index points to head
		if (index == 0) {
			this.head = this.head.getNext();
			return;
		}

		Node<Item> check = head;
		for (int i = 0; i < index - 1; i++) {
			check = check.getNext();
		}
		check.setNext(check.getNext().getNext());
	}

	/**
	 * Removes elements after the provided index and returns a new list consisting
	 * of the removed elements. ie cuts the list in 2
	 * 
	 * @param index
	 * @return Linked list of elements that were removed from the list
	 * @throws OutOfBoundsException
	 */
	public LinkedList<Item> bisect(int index) throws OutOfBoundsException {

		// Verify the input is valid
		if (index < 0 || index >= this.listCount()) {
			throw new OutOfBoundsException();
		}

		// Check if the index points to head
		if (index == 0) {
			LinkedList<Item> temp = new LinkedList<Item>();
			Node<Item> tempNode = this.head;
			temp.add(tempNode.getData());
			while (tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
				temp.add(tempNode.getData());
			}
			this.head = null;
			return temp;
		}

		// Otherwise, bisect the list at the desired index
		LinkedList<Item> temp = new LinkedList<Item>();
		LinkedList<Item> tempList = this.tail();
		for (int i = 1; i < index; i++) {
			tempList = tempList.tail();
		}
		temp = tempList;
		while (true) {
			try {
				this.get(index);
				this.remove(index);
			} catch (OutOfBoundsException e) {
				break;
			}
		}
		return temp;
	}

	/**
	 * Gets the tail of the linked list in the form of a linked list
	 * 
	 * @return linked list tail
	 */
	public LinkedList<Item> tail() {

		// Check if the list is empty, if so return null
		if (head == null) {
			return null;
		}

		// Otherwise create a new Linked list starting from the next node
		LinkedList<Item> temp = new LinkedList<Item>();
		Node<Item> tempNode = this.head.getNext();
		while (tempNode != null) {
			temp.add(tempNode.getData());
			tempNode = tempNode.getNext();
		}
		return temp;
	}

	/**
	 * Gets the data contained in the head of the linked list
	 * 
	 * @return data at head
	 */
	public Item key() {
		return head.getData();
	}

	@Override
	public String toString() {
		String retString = "";

		if (head != null) {
			Node<Item> node = head;
			while (node != null) {
				retString += "[" + node.getData().toString() + "]";
				node = node.getNext();
			}
		}

		return retString;
	}

	public Node<Item> getHead() {
		return this.head;
	}
}
