package binarytree;

public class BSTTest {

	public static void main(String[] args) {
		//Initialise tree
		BST tree = new BST();
		tree.insert(4);
		tree.insert(10);
		tree.insert(3);
		tree.insert(7);
		tree.insert(5);
		tree.insert(7);
		tree.insert(3);
		tree.insert(13);
		tree.insert(11);
		tree.insert(1);
		tree.insert(9);
		tree.insert(2);
		tree.insert(15);
		tree.insert(14);
		tree.insert(8);
		tree.insert(4);
		tree.insert(3);
		
		//Test inorder and delete
		tree.inorder();
		tree.delete(4);
		tree.inorder();
		
		//Test preorder and postorder
		tree.preorder();
		tree.postorder();
		
		//Test search
		System.out.println("Node found: "+tree.search(7));
		
		//Test valid
		System.out.println("Valid: "+tree.checkBST());
		
		//Test root insertion
		tree.preorder();
		tree.insert(6);
		tree.preorder();
		tree.insertRoot(6);
		tree.preorder();
		System.out.println("Valid: "+tree.checkBST());
		
		//Test balance
		tree.balanceBST();
		tree.preorder();
		System.out.println("Valid: "+tree.checkBST());
		
		//Double check count is being logged right
		System.out.println("Node found: "+tree.search(3));
		System.out.println("Node found: "+tree.search(4));
	}

}
