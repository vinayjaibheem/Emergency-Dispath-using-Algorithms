

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class VehicleDispatch {
	
	private final int id;
	private final int type;
	private final int zipcode;

	public VehicleDispatch(int id, int type, int zipcode) {
		super();
		this.id = id;
		this.type = type;
		this.zipcode = zipcode;
	}

	public void getVehicle(List<Integer> idNew, List<Integer> typeNew, List<Integer> zipcodeNew) throws Exception {
		List<Integer> id = new ArrayList<>();
		List<Integer> type = new ArrayList<>();
		List<Integer> zipcode = new ArrayList<>();
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		int flag = 0;
		final Path path = Paths.get("AvailableVehicles.txt");
		final List<VehicleDispatch> parsed;
		try (final Stream<String> lines = Files.lines(path)) {
			parsed = lines.skip(1).map(line -> line.split("\\s*\\|\\s*")).map(line -> {
				final int x = Integer.parseInt(line[0]);
				id.add(x);
				final int y = Integer.parseInt(line[1]);
				type.add(y);
				final int z = Integer.parseInt(line[2]);
				zipcode.add(z);
				return new VehicleDispatch(x, y, z);
			}).collect(Collectors.toList());
		}
		DijkstraAlgorithm dj = new DijkstraAlgorithm();
		List<Integer> dist = new ArrayList<>();
		int distValue = 0;
		int i = 0, j = 0;
		int distance = 0;
		int value = 0;
		int x = 0;
		int min = 0;
		List<Integer> vehicleDist = new ArrayList<>();
		List<Integer> vehicleIds = new ArrayList<>();
		int typeOld = 0;
		for (i = 0; i < typeNew.size(); i++) {

			if (hmap.size() > 1 && typeNew.get(i) != typeOld && flag > 0) {
				vehicleIds.add(value);
				dist.add(min);
				vehicleDist = new ArrayList<>();
				flag = 0;
			}
			
			int a = typeNew.get(i);

			int[] distArray = new int[1000];
			for (j = 0; j < type.size(); j++) {
				int b = type.get(j);
				if (a == (b)) {
					if (zipcodeNew.get(i).equals(zipcode.get(j))) {
						vehicleIds.add(id.get(i));
						dist.add(0);
						break;
						// return vehicle with distance 1
					} else if (zipcodeNew.get(i) != (zipcode.get(j))) {
						String Start = zipcode.get(j).toString();
						String End = zipcodeNew.get(i).toString();
						distance = dj.getDistance(Start, End);
						hmap.put(distance, id.get(j));

						vehicleDist.add(distance);

						min = vehicleDist.get(0);
						for (x = 0; x < vehicleDist.size(); x++) {
							int number = vehicleDist.get(x);

							if (number < min) {
								min = number;
							}
						}
					
						if (x > 0) {
							value = hmap.get(min);
							typeOld = typeNew.get(i);
							flag++;
						}
					}

				}

			}
			
			// compare with previous values and send the lower value
			}
		OutputFile out = new OutputFile();
		out.number = idNew;
		out.vehicleid = vehicleIds;
		out.vehicletype = typeNew;
		out.vehiclezipcode = zipcodeNew;
		out.distance = dist;
		out.getFileOutput();
		
		// Search vehicleid in this id and match with zipcode send the vehicle if it is
		// not available then send vehicle Zip code DijkstraAlgorithm's Algorithm and
		// get the nearest vehicle
	}

}
