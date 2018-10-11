package com.knowledge.daaproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputFile {

	List<Integer> number = new ArrayList<>();
	List<Integer> vehicletype = new ArrayList<>();
	List<Integer> vehiclezipcode = new ArrayList<>();
	List<Integer> vehicleid = new ArrayList<>();
	List<Integer> distance = new ArrayList<>();

	// if id is found in the list then check for minimum in them and insert that
	public void getFileOutput() throws IOException {
		File dir = new File("C:\\Users\\HP\\Desktop\\DAA\\DAAOUTPUT");

		dir.mkdirs();
		File file = new File(dir, "Results.txt");
		FileWriter archivo = new FileWriter(file);
		archivo.write(String.format("%20s %20s %20s %20s %20s \r\n", "ID", "VEHICLE_TYPE", "DESTINATION_ZIP",
				"VEHICLE_ID", "DISTANCE"));
		for (int i = 0; i < number.size(); i++) {
			archivo.write(String.format("%20d %20d %20d %20d %20d  \r\n", number.get(i), vehicletype.get(i),
					vehiclezipcode.get(i), vehicleid.get(i), distance.get(i)));
		}
		 System.out.println(" Id - " + number + " typeNew - " + vehicletype +
		 " zipecodeNew - " + vehiclezipcode + " v id - " + vehicleid + " distance - "
		 +
		 distance);
		//
		archivo.flush();
		archivo.close();
	}

}
