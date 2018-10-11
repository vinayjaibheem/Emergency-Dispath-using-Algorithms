

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	private final int id;
	private final int type;
	private final int zipcode;

	public Main(int id, int type, int zipcode) {
		this.id = id;
		this.type = type;
		this.zipcode = zipcode;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final Path path = Paths.get("EmergencyVehicleRequest.txt"); //feeding the request file
		final List<Main> parsed;
		List<Integer> id = new ArrayList<>();
		List<Integer> type = new ArrayList<>();
		List<Integer> zipcode = new ArrayList<>();
		try (final Stream<String> lines = Files.lines(path)) {
			parsed = lines.skip(1).map(line -> line.split("\\s*\\|\\s*")).map(line -> {
				final int x = Integer.parseInt(line[0]);
				id.add(x);
				final int y = Integer.parseInt(line[1]);
				type.add(y);
				final int z = Integer.parseInt(line[2]);
				zipcode.add(z);
				return new Main(x, y, z);
			}).collect(Collectors.toList());
		}

		VehicleDispatch vd = new VehicleDispatch(1, 2, 3);
		vd.getVehicle(id, type, zipcode);
	}

}

