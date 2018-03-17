package com.intercom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by hadoop on 13/3/18.
 */

@Data
public class Person extends LocationEntity {
    String name;
    Integer user_id;
    public Person(String name,Integer id, double latitiue, double longitute){
        super(latitiue,longitute);
        this.name = name;
        this.user_id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", user_id=" + user_id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
