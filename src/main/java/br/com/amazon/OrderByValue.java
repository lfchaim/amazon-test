package br.com.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderByValue {

	public static void main(String[] args) throws Exception {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 4);
		map.put(2, 6);
		map.put(3, 1);
		map.put(4, 1);
		map.put(6, 8);
		map.put(7, 5);

		Set<Entry<Integer, Integer>> set = map.entrySet();
		List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		System.out.println("Keys\t\tValues");
		List<Integer> listKeys = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : list) {
			System.out.println(" " + entry.getKey() + "\t\t  " + entry.getValue());
			listKeys.add(entry.getKey());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(listKeys);
		System.out.println(json);
	}
}