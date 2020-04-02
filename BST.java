import java.util.ArrayList;

public class BST {

    Node root;
    BST() {
        root = null;
    }

    //Class of Node , pointers to left and right children
    public class Node {
        public String data;
        public Node left, right;

        Node(String d) {
            data = d;
            left = null;
            right = null;
        }
    //getters and setters for Node
        public void setRightChild(Node n) {
            right = n;
        }

        public void setLeftChild(Node n) {
            left = n;
        }

        public Node getRightChild() {
            return right;
        }

        public Node getLeftChild() {
            return left;
        }

        public String getData() {
            return data;
        }

        public void setData(String x) {
            data = x;
        }
    }


    // Returns true if the word is inside the tree

    public boolean search(String searchword) {

        //It starts from the root. If null, then false
        Node temp = root;
        while (true) {
            //If temp is null, it means the word doesn't exists, so false
            if (temp == null)
                return false;
                //If not, it checks if the node is the one it is looking for, or if it needs to go to left or right
            else {
                //If temp one has the value it is looking for, then true
                if (temp.data.equals(searchword))
                    return true;
                    //If not, if the temp one is less than the value, it goes to the right child to keep searching
                else if (temp.data.compareTo(searchword) < 0)
                    temp = temp.right;
                    //Else, if the temp  is greater than the value, it goes to the left child to keep searching
                else
                    temp = temp.left;
            }
        }
    }



    //Method that inserts a new String word into the tree
    public void insert(String word) {

        //New node made for new word
        Node newNode = new Node(word);

        //if root is null(eg. first word) make new word the root
        if (root == null) {
            root = newNode;
        }

        //if root isn't null, insert where it goes the deeper levels
        else {

            //node that newNode will be compared to
            Node temp = root;
            while (true) {

                //Test to see if it goes left
                if (temp.data.compareTo(newNode.data) > 0) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        break;
                    } else
                        temp = temp.left;
                } else {
                    //place on the right
                    if (temp.right == null) {
                        temp.right = newNode;
                        break;
                    } else
                        temp = temp.right;
                }
            }
        }
    }


    // Checks the Trie to see if the words from input.txt are misspelled
    public void SpellCheckInputWords (ArrayList<String> wordlist) {

        //loops through list of words from input.txt
        for (int i = 0; i < wordlist.size(); i++) {
            String current = wordlist.get(i);

            //if the word matches an entry in the dictionary write it to the output file
            if (search(current)) {
                ReadandWriteFiles.writeOutput("output.txt", current);
            } else {
                // if the word is misspelled, look for suggestions
                suggestions(current);
            }
        }
    }

    public void suggestions(String word) {
        boolean done = false;

        // letters lowercase to be able to find match
        word = word.toLowerCase();

        char[] wordArr = word.toCharArray();

        //look for matches of the word minus its last letter
        int length = wordArr.length -1;

        while (done == false) {

            // create char array for new shortened word
            char[] newArr = new char[length];


            //fill char array with new shortened word
            for (int i = 0; i < length; i++) {
                newArr[i] = wordArr[i];
            }

            //make the new char array a string
            String newSearch = String.valueOf(newArr);

            //if this new word is found in the dictionary, suggest it
            if (search(newSearch)) {
                ReadandWriteFiles.writeOutput("output.txt", newSearch);
                done = true;
            }
            //if not try subtracting the last letter of the new word

            length--;
        }

    }
}















