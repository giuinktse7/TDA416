package collection;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    private E deleteReturn;

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        super(data, leftTree, rightTree);
    }

    @Override
    public boolean add(E item) {
        if (root == null) {
            root = new Node<>(item, null);
            return true;
        } else
            return add(item, root);
    }

    private boolean add(E item, Node<E> currentNode) {
        int diff = item.compareTo(currentNode.e);

        if (diff == 0)
            return false;

        if (diff < 0) {
            if (currentNode.left == null) {
                currentNode.left = new Node<>(item, currentNode);
                return true;
            } else
                return add(item, currentNode.left);
        }
        else {
            if (currentNode.right == null) {
                currentNode.right = new Node<>(item, currentNode);
                return true;
            } else
                return add(item, currentNode.right);
        }
    }

    @Override
    public E find(E target) {
        return target != null ? find(target, root) : null;
    }

    private E find(E target, Node<E> currentNode) {
        int diff = target.compareTo(currentNode.e);
        if (diff == 0)
            return currentNode.e;

        return find(target, diff < 0 ? currentNode.left : currentNode.right);
    }

    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    @Override
    public E delete(E target) {
        if (target == null)
            return deleteReturn = null;

        root = delete(root, target);
        return deleteReturn;
    }

    private Node<E> delete(Node<E> currentNode, E target) {
        if (currentNode == null) {
            deleteReturn = null;
            return null;
        }

        int diff = target.compareTo(currentNode.e);
        if (diff < 0) {
            currentNode.left = delete(currentNode.left, target);
            return currentNode;
        } else if (diff > 0) {
            currentNode.right = delete(currentNode.right, target);
            return currentNode;
        } else {
            deleteReturn = currentNode.e;
            if (currentNode.left == null)
                return currentNode.right;
            else if (currentNode.right == null)
                return currentNode.left;
            else {
                //Two children, replace contents instead of nodes
                if (currentNode.left.right == null) {
                    currentNode.e = currentNode.left.e;
                    currentNode.left = currentNode.left.left;
                    return currentNode;
                } else {
                    currentNode.e = deleteMax(currentNode.left);
                    return currentNode;
                }
            }
        }
    }

    private E deleteMax(Node<E> node) {
        if (node.right.right == null) {
            E result = node.right.e;
            node.right = node.right.left;
            return result;
        } else
            return deleteMax(node.right);
    }

    @Override
    public boolean remove(E target) {
        return false;
    }
}
