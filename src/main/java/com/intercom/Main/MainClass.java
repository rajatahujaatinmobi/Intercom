package com.intercom.Main;

import com.intercom.DTO.LocationEntity;
import com.intercom.DTO.Pair;
import com.intercom.DTO.Person;
import com.intercom.Reader.impl.JsonReader;
import com.intercom.inmemorydatastructure.KDimentionalTree;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hadoop on 13/3/18.
 */
public class MainClass {
    public static void main(String[] args) {
        try {

            KDimentionalTree<Person> threeDimentionalTree = new KDimentionalTree<Person>(3);

            Type type = Person.class;
            JsonReader<Person> jsonReader = new JsonReader<Person>();
            jsonReader.registerFilePath("src/main/resources/customer");
            while (jsonReader.hasNextEntity()){
                Person person = jsonReader.read(type);
                threeDimentionalTree.add(person);
            }

            double dublinIntercomOfficeLatitude = 53.339428;
            double dublinIntercomOfficeLongitude = -6.257664;

            LocationEntity locationEntity = new LocationEntity(dublinIntercomOfficeLatitude, dublinIntercomOfficeLongitude);
            // In Km
            Integer radius = 100;

            List<Pair<Person,Integer>> list = threeDimentionalTree.findNearestNodesFromRadius(locationEntity,radius);

            System.out.println("Total Customers "+list.size());
            for(Pair<Person,Integer> pair:list){
                System.out.println(pair.getKey());
                System.out.println("Distance From Dublin Office :"+pair.getValue()+" Km");
                System.out.println();
            }

        }
        catch (Exception e){
            System.out.println("Exception Caught and Ducked here in main class "+e);
        }
    }
}
