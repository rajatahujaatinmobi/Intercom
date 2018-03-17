package com.intercom.common;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * Created by hadoop on 13/3/18.
 */
public class CommonUtils {
    private static int radiusOfEarth = 6371;
    public static void convertLocationToThreeDimentional(double[] array, double latitude, double longitude) {
        array[0] = (double) (cos(toRadians(latitude)) * cos(toRadians(longitude)))*radiusOfEarth;
        array[1] = (double) (cos(toRadians(latitude)) * sin(toRadians(longitude)))*radiusOfEarth;
        array[2] = (double) (sin(toRadians(latitude)))*radiusOfEarth;
    }
}
