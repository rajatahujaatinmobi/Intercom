package com.intercom.inmemorydatastructure;

import com.intercom.DTO.LocationEntity;
import com.intercom.common.CommonUtils;
import lombok.Data;

@Data
class Node<T extends LocationEntity> {
        private T data;
        private Node<T> left;
        private Node<T> right;
        private long depth;
        private double[] array;
        private int k = 3;

        Node(T value) {
            this.data = value;
            this.depth = 0;
            array = new double[k];
            CommonUtils.convertLocationToThreeDimentional(array, data.getLatitude(), data.getLongitude());
        }
        Node(LocationEntity locationEntity,T dummy){
            this.data = dummy;
            this.depth = 0;
            array = new double[k];
            CommonUtils.convertLocationToThreeDimentional(array, locationEntity.getLatitude(), locationEntity.getLongitude());
        }

        public int overLoadedComparator(Node<T> that) {
            int axis = (int) this.depth % k;
            if (this.array[axis] < that.array[axis]) {
                return -1;
            }
            if (this.array[axis] > that.array[axis]) {
                return 1;
            }
            return 0;
        }
    }