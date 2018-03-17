package com.intercom.inmemorydatastructure;


import com.intercom.DTO.LocationEntity;
import com.intercom.DTO.Person;
import com.intercom.Reader.impl.JsonReader;
import com.intercom.exception.NearestCustomerApplicationException;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hadoop on 17/3/18.
 */
public class KDimentionalTreeTest {

    KDimentionalTree<Person> kDimentionalTree;
    JsonReader<Person> jsonReader ;
    LocationEntity locationEntityCentre;

    @Before
    public void setUp() throws Exception {
        kDimentionalTree = new KDimentionalTree<>(3);
        jsonReader = new JsonReader<>();
        jsonReader.registerFilePath("src/test/resources/customer");
        double dublinIntercomOfficeLatitude = 53.339428;
        double dublinIntercomOfficeLongitude = -6.257664;
        locationEntityCentre = new LocationEntity(dublinIntercomOfficeLatitude,dublinIntercomOfficeLongitude);

    }

    public KDimentionalTreeTest(){

    }
    @Test
    public void findNearestNodesFromRadius() throws NearestCustomerApplicationException{
        kDimentionalTree.setRoot(null);
        while (jsonReader.hasNextEntity()){
            Person person = jsonReader.read(Person.class);
            kDimentionalTree.add(person);
        }
        int radius = 100;
        Assert.assertTrue(kDimentionalTree.findNearestNodesFromRadius(locationEntityCentre,radius).size() == 16);
        // Tree is all set
        radius = 0;
        Assert.assertTrue(kDimentionalTree.findNearestNodesFromRadius(locationEntityCentre,radius).size() == 0);

    }

    @Test(expected = NearestCustomerApplicationException.class)
    public void addNull() throws NearestCustomerApplicationException{
        kDimentionalTree.add(null);
    }
    @Test
    public void addValue() throws NearestCustomerApplicationException{
        kDimentionalTree.add(getPerson());
        Assert.assertTrue(kDimentionalTree.getRoot().getData().equals(getPerson()));
    }

    Person getPerson(){
        Person person = new Person("Christina McArdle",12,52.986375,-6.043701);
        return person;
    }
}
