package com.intercom.inmemorydatastructure;

import com.intercom.DTO.LocationEntity;
import com.intercom.DTO.Pair;
import com.intercom.exception.ErrorCode;
import com.intercom.exception.NearestCustomerApplicationException;
import lombok.Data;

import java.util.*;


/**
 * Created by hadoop on 15/3/18.
 */
@Data
public class KDimentionalTree<T extends LocationEntity> {
    private int dimension;
    // root of K dimentionl Tree
    private Node<T> root = null;

    public KDimentionalTree(int dimension) {
        this.dimension = dimension;
    }

    // Find all near nodes

    public List<Pair<T, Integer>> findNearestNodesFromRadius(LocationEntity centre, int radius) {
        Node<T> node = new Node<T>(centre, null);
        List<Pair<Node<T>, Integer>> nodes = new ArrayList<Pair<Node<T>, Integer>>();
        List<Pair<T, Integer>> returnedList = new ArrayList<Pair<T, Integer>>();
        findNearestNodesFromRadiusHelper(root, node, 0, radius, nodes);
        for (Pair<Node<T>, Integer> pair : nodes) {
            returnedList.add(new Pair<T, Integer>(pair.getKey().getData(), pair.getValue()));
        }
        return returnedList;
    }

    private void findNearestNodesFromRadiusHelper(Node current, Node target, int depth, int radius, List<Pair<Node<T>, Integer>> nearNodes) {
        if (current == null) {
            return;
        }
        int actualDistance = getDistance(current, target);

        if (actualDistance <= radius) {
            nearNodes.add(new Pair<>(current, actualDistance));
        }
        if (current.overLoadedComparator(target) >= 0) {
            int distance = Math.abs(getDistanceOnParticularAxis(current, target, depth));
            if (distance <= radius) {
                // Very tricky Step
                // We might find nodes on right  side as well since distance is less than radius
                findNearestNodesFromRadiusHelper(current.getRight(), target, depth + 1, radius, nearNodes);
            }
            findNearestNodesFromRadiusHelper(current.getLeft(), target, depth + 1, radius, nearNodes);
        } else {
            int distance = Math.abs(getDistanceOnParticularAxis(current, target, depth));
            if (distance <= radius) {
                // Very tricky Step
                // We might find nodes on left side as well since distance is less than radius
                findNearestNodesFromRadiusHelper(current.getLeft(), target, depth + 1, radius, nearNodes);
            }
            //Usual Tree Traversal towards right
            findNearestNodesFromRadiusHelper(current.getRight(), target, depth + 1, radius, nearNodes);
        }
    }

    // Adding value to the tree structure
    public boolean add(T value) throws NearestCustomerApplicationException {
        if (value == null) {
            throw new NearestCustomerApplicationException("Adding Null Value ", ErrorCode.ADDING_NULL_VALUE);
        }
        if (root == null) {
            root = new Node<T>(value);
            return true;
        }
        // As of now its height is 0
        // once we find its correct height, we will update its height
        Node<T> toBeInsertedNode = new Node<T>(value);
        Node<T> current = root;

        while (true) {
            if (current.overLoadedComparator(toBeInsertedNode) <= 0) {
                if (current.getLeft() == null) {
                    toBeInsertedNode.setDepth(current.getDepth() + 1);
                    current.setLeft(toBeInsertedNode);
                    break;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    toBeInsertedNode.setDepth(current.getDepth() + 1);
                    current.setRight(toBeInsertedNode);
                    break;
                }
                current = current.getRight();
            }
        }
        return true;
    }

    private int getDistance(Node current, Node target) {
        final double x = current.getArray()[0] - target.getArray()[0];
        final double y = current.getArray()[1] - target.getArray()[1];
        final double z = current.getArray()[2] - target.getArray()[2];
        return (int) (Math.sqrt(x * x + y * y + z * z));
    }

    private int getDistanceOnParticularAxis(Node current, Node target, int depth) {
        return (int) (current.getArray()[depth % dimension] - target.getArray()[depth % dimension]);
    }
}
