package com.intercom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hadoop on 17/3/18.
 */
@AllArgsConstructor
@ToString
@Data
public class Pair<K,V> {
    K key;
    V value;
}
