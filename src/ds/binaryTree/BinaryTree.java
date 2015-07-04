package ds.binaryTree;

public class BinaryTree<E extends Comparable> implements IBinaryTree<E> {
    private Node<E> root;
    private int size;

    @Override
    public boolean add(E element) {
        if (size == 0) {
            root = new Node<E>(null, null, null, element);
            size++;
            return true;
        } else {
            for (Node<E> start = root; start != null; ) {
                if (start.value.compareTo(element) <= 0) {
                    if (start.rightChild == null) {
                        start.rightChild = new Node<E>(start, null, null, element);
                        size++;
                        break;
                    } else {
                        start = start.rightChild;
                    }
                } else {
                    if (start.leftChild == null) {
                        start.leftChild = new Node<E>(start, null, null, element);
                        size++;
                        break;
                    } else {
                        start = start.leftChild;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(E element) {
        Node<E> target = findNode(element);
        if (target == null) {
            return false;
        }
        if (target == root) {
            root = null;
            size--;
            return true;
        }
        if(target.rightChild == null && target.leftChild == null){
            if(target.parent.leftChild == target){
                target.parent.leftChild = null;
            }else{
                target.parent.rightChild = null;
            }
        } else if (target.leftChild == null) {
            if(defineChild(target) == 1) {
                target.parent.leftChild = target.rightChild;
            }else{
                target.parent.rightChild = target.rightChild;
            }
        } else if (target.rightChild == null){
            if(defineChild(target) == 1) {
                target.parent.leftChild = target.leftChild;
            }else{
                target.parent.rightChild = target.leftChild;
            }
        }else{
            Node<E> max = findMax(target.leftChild);
            if(max.leftChild != null){
                max.parent.rightChild = max.leftChild;
                max.leftChild.parent = max.parent;
            }
            target.value = max.value;

        }
        size--;
        return true;
    }

    public E findMaxValue(){
        return findMax(root).value;
    }

    public E findMinValue(){
        return findMin(root).value;
    }

    /**
     * @return 1 if child is left, -1 right, 0 if something going wrong
     */
    private int defineChild(Node child) {
        return child.parent.leftChild == child ? 1 : child.parent.rightChild == child ? -1 : 0;
    }

    private Node<E> findMax(Node<E> node) {
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node;
    }

    private Node<E> findMin(Node<E> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    private Node<E> findNode(E element) {
        Node<E> foundNode = null;
        for (Node<E> start = root; start != null; ) {
            if (start.value.compareTo(element) == 0) {
                foundNode = start;
                break;
            } else if (start.value.compareTo(element) > 0) {
                if (start.leftChild == null) {
                    break;
                } else {
                    start = start.leftChild;
                }
            } else {
                if (start.rightChild == null) {
                    break;
                } else {
                    start = start.rightChild;
                }
            }

        }
        return foundNode;
    }


    private static class Node<E> {
        E value;
        Node<E> leftChild;
        Node<E> rightChild;
        Node<E> parent;

        public Node(Node<E> parent, Node<E> leftChild, Node<E> rightChild, E value) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }
    }
}
