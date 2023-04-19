import java.util.*;

public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left=null;
            this.right=null;
        }
    }

    static class BinaryTree {
        static int indx =-1;
        public static Node buildTree(int nodes[]) {
            indx++;
            if(nodes[indx]==-1){
                return null;
            }
            Node newNode = new Node(nodes[indx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    // Time Complexity O(n)
    public static void preOrder(Node root) {
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // Time Complexity O(n)
    public static void inOrder(Node root) {
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    // Time Complexity O(n)
    public static void postOrder(Node root) {
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    //using queue with its FIFO property
    public static void levelOrder(Node root) {
        if(root==null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node curNode = q.remove();
            if(curNode == null){
                System.out.println();
                if(q.isEmpty()) {
                    break;
                }
                else {
                    q.add(null);
                }
            }
            else {
                System.out.print(curNode.data+" ");
                if(curNode.left != null) {
                    q.add(curNode.left);
                }
                if(curNode.right != null) {
                    q.add(curNode.right);
                }
            }
        }
    }

    // height of tree
    public static int height(Node root){
        if(root==null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight)+1;
    }


    // counting no of nodes
    public static int countOfNodes(Node root) {
        if(root==null){
            return 0;
        }
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);
        return leftNodes+rightNodes+1;
    }

    //sum of all nodes
    public static int sumOfNodes(Node root) {
        if(root==null){
            return 0;
        }
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);
        return leftSum+rightSum+root.data;
    }

    // Diameter - No. of nodes in the longest path between 2 nodes O(n^2)
    public static int diamOfNodes(Node root) {
        if(root==null){
            return 0;
        }
        int diam1 = diamOfNodes(root.left);
        int diam2 = diamOfNodes(root.right);
        int diam3 = height(root.left)+height(root.right)+1;
        return Math.max(diam3, Math.max(diam1, diam2));
    }

    static class TreeInfo {
        int ht;
        int diam;

        TreeInfo(int ht,int diam) {
            this.ht = ht;
            this.diam = diam;
        }
    }
    // Diameter - O(n)
    public static TreeInfo diameter(Node root) {
        if(root==null){
           return new TreeInfo(0,0);
        }
        TreeInfo left = diameter(root.left);
        TreeInfo right = diameter(root.right);
        int heights = Math.max(left.ht, right.ht)+1;
        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht+right.ht+1;
        int diams = Math.max(Math.max(diam1, diam2), diam3);
        TreeInfo infos = new TreeInfo(heights, diams);
        return infos;
    }

    public static void main(String args[]) {
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        // System.out.println(root.data);
        // preOrder(root);
        // inOrder(root);
        // postOrder(root);
        // levelOrder(root);
        // System.out.println(height(root));
        // System.out.println(countOfNodes(root));
        // System.out.println(sumOfNodes(root));
        // System.out.println(diamOfNodes(root));
        // System.out.println(diameter(root).diam);
    }
}