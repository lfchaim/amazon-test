package br.com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readLines( File file ){
		FileReader fr = null;
		BufferedReader br = null;
		List<String> list = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while( (line = br.readLine()) != null ){
				list.add(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try{br.close();}catch(Exception e){}
			try{br.close();}catch(Exception e){}
		}
		return list;
	}
}
