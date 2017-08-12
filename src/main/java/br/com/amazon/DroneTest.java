package br.com.amazon;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class DroneTest {

	private static class Drone {
        private int id;
        private int flightRange;

        public Drone(int id, int flightRange) {

            this.id = id;
            this.flightRange = flightRange;
        }

        public int getId() {
            return id;
        }

        public int getFlightRange() {
            return flightRange;
        }
    }

    // The first line of the input contains three decimal integers separated by space: total number of drones ('D'), number of drones to be selected ('G') and number of drones in maintenance ('M').
    // The following 'D' lines each contains two decimal integers separated by space with information about each drone: ID and flight range in kilometers.
    // The following 'M' lines each contains the numeric ID of a drone currently in maintenance.

    // Print the IDs of the 'G' selected drones to the standard output, one per line, sorted by flight range (greater first).
    public static void main(String[] args) throws Exception {
        //Scanner in = new Scanner(System.in);
    	Scanner in = new Scanner(new File("drones.txt"));
    	   	
        int numberOfDrones = in.nextInt();
        int numberOfRequiredDrones = in.nextInt();
        int numberOfDronesInMaintenance = in.nextInt();

        List<Drone> drones = new ArrayList<Drone>();
        List<Integer> inMaintenanceDrones = new ArrayList<Integer>();

        for(int i=0; i< numberOfDrones; i++) {
            drones.add(new Drone(in.nextInt(), in.nextInt()));
        }

        for(int i=0; i< numberOfDronesInMaintenance; i++) {
            inMaintenanceDrones.add(in.nextInt());
        }

        List<Integer> greatestFlightRangeDrones = greatestFlightRangeDrones(numberOfRequiredDrones, drones, inMaintenanceDrones);

        for(int i=0; i < greatestFlightRangeDrones.size(); i++) {
            System.out.println(greatestFlightRangeDrones.get(i));
        }

    }
    
    private static List<Integer> greatestFlightRangeDrones(Integer numberOfRequiredDrones, List<Drone> drones, List<Integer> inMaintenanceDrones) {
    	Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
    	for( int i = 0 ; i < drones.size(); i++ ){
    		if( !inMaintenanceDrones.contains(drones.get(i).getId()) ){
    			map.put(drones.get(i).getId(),drones.get(i).getFlightRange());
    		}
    	}
    	Set<Entry<Integer, Integer>> set = map.entrySet();
		List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
    	Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
    	Collections.reverse(list);
    	List<Integer> listRet = new ArrayList<Integer>();
    	for( int i = 0; i < list.size(); i++ ){
    		listRet.add(list.get(i).getKey());
    	}
    	return listRet.subList(0, numberOfRequiredDrones);
    }
   
}