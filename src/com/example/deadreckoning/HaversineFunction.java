package com.example.deadreckoning;

public class HaversineFunction {
    public static final double R = 6372.8; // In kilometers
    public static double calculateHaversine(double latitude1, double longitude1, double latitude2, double longitude2) {
    	
        double distance_latitude = Math.toRadians(latitude2 - latitude1);
        double distance_longitude = Math.toRadians(longitude2 - longitude1);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.sin(distance_latitude / 2) * Math.sin(distance_latitude / 2) + Math.sin(distance_longitude / 2) * Math.sin(distance_longitude / 2) * Math.cos(latitude1) * Math.cos(latitude2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}