package br.com.amazon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.util.FileUtil;

public class Trips {

	// The first line of the input contains two decimal integers separated by space: the maximum weight ('W') that can be delivered in a single trip and the quantity of packages ('P') to be delivered.
    // The following 'P' lines contain a decimal integer representing the weight of each package.
    
	public static void main(String[] args) throws Exception {
    	List<String> list = FileUtil.readLines(new File("trips.txt"));
    	
    	
    	for( int i = 0; i < list.size(); i++ ){
            int tripMaxWeight = getMaxW(list.get(i));
            int[] packagesWeight = getPackagesW(list.get(i));

            int minimumNumberOfTrips = minimumNumberOfTrips(tripMaxWeight, packagesWeight);

            System.out.println(minimumNumberOfTrips);
    		
    	}

    }
    
    static int getMaxW( String value ){
    	return Integer.parseInt(value.substring(0,value.indexOf(" ")));
    }
    
    static int[] getPackagesW( String value ){
    	String[] strArr = value.split(" ");
    	int[] ret = new int[strArr.length-1];
    	for( int i = 1; i < strArr.length; i++ ){
    		ret[i-1] = Integer.parseInt(strArr[i]);
    	}
    	return ret;
    }
    
    static int minimumNumberOfTrips(int tripMaxWeight, int[] packagesWeight) {
    	int idxTrip = 1;
    	int maxPackPerTrip = 2;
    	Map<Integer,List<Integer>> mapTrip = new LinkedHashMap<Integer,List<Integer>>();
    	try{
	    	for( int i = 0; i < packagesWeight.length; i++ ){
	    		List<Integer> listTemp = mapTrip.get(idxTrip);
    			if( listTemp == null )
    				listTemp = new ArrayList<Integer>();
	    		if( packagesWeight[i] >= tripMaxWeight ){
	    			if( listTemp.size() > 0 ){
	    				List<Integer> listNew = new ArrayList<Integer>();
    					listNew.add(packagesWeight[i]);
    					mapTrip.put(idxTrip, listNew);
    					idxTrip++;
	    			}else{
		    			listTemp.add(packagesWeight[i]);
		    			mapTrip.put(idxTrip++, listTemp);
	    			}
	    		} else {
    				int tempTotal = listTemp.parallelStream().reduce(0, Integer::sum);
    				if( (tempTotal + packagesWeight[i]) > tripMaxWeight || listTemp.size() >= maxPackPerTrip ){
    					idxTrip++;
    					List<Integer> listNew = new ArrayList<Integer>();
    					listNew.add(packagesWeight[i]);
    					mapTrip.put(idxTrip, listNew);
    				} else {
    					listTemp.add(new Integer(packagesWeight[i]));
    					mapTrip.put(idxTrip, listTemp);
    				}
	    		}
	    	}
    	} catch( Exception e ){
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	try{System.out.println(mapper.writeValueAsString(mapTrip));}catch(Exception e){}
    	return mapTrip.size();
    }
}
