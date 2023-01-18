package fr.eql.aicap.annuaire;
import java.util.List;

public class BinaryTree {
    static Node root;
    public void addNode(String key, int positionInBinFile) {
        // Create a new Node and initialize it
        Node newNode = new Node(key, positionInBinFile);
        // If there is no root this becomes root
        if (root == null) {
            root = newNode;
        } else {
            // Set root as the Node we will start
            // with as we traverse the tree
            Node focusNode = root;
            // Future parent for our new Node
            Node parent;
            while (true) {
                // root is the top parent so we start
                // there
                parent = focusNode;
                // Check if the new node should go on
                // the left side of the parent node
                if (key.compareToIgnoreCase(focusNode.key) < 0) {
                    // Switch focus to the left child
                    focusNode = focusNode.leftChild;
                    // If the left child has no children
                    if (focusNode == null) {
                        // then place the new node on the left of it
                        parent.leftChild = newNode;
                        return; // All Done
                    }
                } else { // If we get here put the node on the right
                    focusNode = focusNode.rightChild;
                    // If the right child has no children
                    if (focusNode == null) {
                        // then place the new node on the right of it
                        parent.rightChild = newNode;
                        return; // All Done
                    }
                }
            }
        }
    }
    public void deleteNode(String key, int positionInBinFile) {
        //TODO Urgent

    }
    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth
    public void inOrderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            // Traverse the left node
            inOrderTraverseTree(focusNode.leftChild);
            // Visit the currently focused on node
            System.out.println(focusNode);
            // Traverse the right node
            inOrderTraverseTree(focusNode.rightChild);
        }
    }
    public static void inOrderTraverseTree_List(Node focusNode,List<Stagiaire> List,String fichier_Binaire) {
        if (focusNode != null) {
            // Traverse the left node
            inOrderTraverseTree_List(focusNode.leftChild,List,fichier_Binaire);
            // add in list the currently focused on node
            List.add(Stagiaire.getSelect(fichier_Binaire, focusNode.address));
            // Traverse the right node
            inOrderTraverseTree_List(focusNode.rightChild,List,fichier_Binaire);
        }
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
        }
    }

    public void postOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);

            System.out.println(focusNode);

        }

    }

    public static Node findNode(String key) {
        // Start at the top of the tree
        Node focusNode = root;
        // While we haven't found the Node
        // keep looking
        while (!focusNode.key.equals(key)) {
            // If we should search to the left
            if (key.compareToIgnoreCase(focusNode.key) < 0) {
                // Shift the focus Node to the left child
                focusNode = focusNode.leftChild;
            } else {
                // Shift the focus Node to the right child
                focusNode = focusNode.rightChild;
            }
            // The node wasn't found
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }
}