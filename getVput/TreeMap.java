/**
 * Created By: Jason Wehran
 * Date Created: June 27, 2023
 */
import java.util.Comparator;

//! ALL METHODS IN THIS CLASS BASED OFF THE GIVEN BST CLASS

public class TreeMap<K, V> {
    private TreeNode root;
    private int size;
    private Comparator<K> comp;
    public static int iterations;

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;

        TreeNode(K key1, V val) {
            key = key1;
            value = val;
            left = right = null;
        }
    }

    /**
	 * Default constructor
	 * creates an empty TreeMap
	 */
    public TreeMap(Comparator<K> c) {
        root = null;
        size = 0;
        comp = c;
    }

    /**
	 * Method size
	 * @return the number of nodes in the BST
	 */
    public int size() {
        return size;
    }

    /**
	 * Method isEmpty
	 * @return true if the tree is empty, false otherwise
	 */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
	 * Method clear
	 * resets root to null and size to 0
	 */
    public void clear() {
        root = null;
    }

    /**
	 * Search method
	 * @param value being searched
	 * @return true if value is found, false otherwise
	 */
    public boolean containsKey(K key) {
        TreeNode node = root;
        while (node != null) {
            if (comp.compare(key, node.key) < 0)
                node = node.left;
            else if (comp.compare(key, node.key) > 0)
                node = node.right;
            else
                return true;
        }
        return false;
    }

    /**
     * Method to get value
     * @param key
     * @return
     */
    public V get(K key) {
        iterations = 0;
        TreeNode node = root;
        while (node != null) {
            iterations++;
            if (comp.compare(key, node.key) < 0)
                node = node.left;
            else if (comp.compare(key, node.key) > 0)
                node = node.right;
            else {
                return node.value;
            }
        }
        return null;
    }

    /**
	 * Add a new node to the tree if the node does not exist
	 * @param value of the new node to be added
	 * @return true if a node is added successfully, 
	 *         false if the node exists already in the tree
	 */
    public boolean add(K key, V val) {
        if (root == null) // first node to be inserted
            root = new TreeNode(key, val);
        else {
            TreeNode parent, node;
            parent = null;
            node = root;
            while (node != null) {// Looking for a leaf node
                parent = node;
                if (comp.compare(key, node.key) < 0) {
                    node = node.left;
                } else if (comp.compare(key, node.key) > 0) {
                    node = node.right;
                } else {
                    node.value = val;
                    return false; // duplicates are not allowed
                }
            }
            if (comp.compare(key, parent.key) < 0) {
                parent.left = new TreeNode(key, val);
            } else {
                parent.right = new TreeNode(key, val);
            }
        }
        size++;
        return true;
    }

    /**
	 * Remove the node with value if found
	 * @param value of the node to be removed
	 * @return true if a node with value is found and removed
	 *         false if no node is not found
	 */
    public boolean remove(K key) {
        TreeNode parent, node;
        parent = null;
        node = root;
        // Find value first
        while (node != null) {
            if (comp.compare(key, node.key) < 0) {
                parent = node;
                node = node.left;
            } else if (comp.compare(key, node.key) > 0) {
                parent = node;
                node = node.right;
            } else
                break; // value found
        }
        // if value not in the tree
        if (node == null) {
            return false;
        }

        // if node has no children
        if (node.left == null && node.right == null) {
            if (parent == null) { // delete root
                root = null;
            } else {
                changeChild(parent, node, null);
            }
        }
        // if node has one right child
        else if (node.left == null) {
            if (parent == null) { // delete root
                root = node.right;
            } else {
                changeChild(parent, node, node.right);
            }
        }
        // if node has one left child
        else if (node.right == null) {
            if (parent == null) { // delete root
                root = node.left;
            } else {
                changeChild(parent, node, node.left);
            }
        }
        // if node has two children
        else {
            TreeNode rightMostParent = node;
            TreeNode rightMost = node.left;
            // go right on the left subtree
            while (rightMost.right != null) {
                rightMostParent = rightMost;
                rightMost = rightMost.right;
            }
            // copy the value of rigthMost to node
            node.value = rightMost.value;
            // delete right most
            changeChild(rightMostParent, rightMost,
                    rightMost.left);
        }
        size--;
        return true;
    }

    /**
	 * Private method used by the remove method
	 * to update the links from parent to child
	 * @param parent of the node being deleted
	 * @param node the node being deleted
	 * @param newChild the node that will replace node as the child of parent
	 */
    private void changeChild(TreeNode parent,
            TreeNode node, TreeNode newChild) {
        if (parent.left == node)
            parent.left = newChild;
        else
            parent.right = newChild;
    }

    /**
	 * Recursive method to display the list of the tree nodes inorder
	 */    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    // Recursive method preorder()
    public void preorder() {
        preorder(root);
    }

    /**
	 * method to display the list of the tree nodes preorder
	 */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
	 * Recursive method to display the list of the tree nodes postorder
	 */    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }

}