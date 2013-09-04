package eu.justas.geoipmongo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.bytecode.opencsv.CSVReader;
import eu.justas.geoipmongo.model.Location;
import eu.justas.geoipmongo.service.LocationService;

@Controller
public class LocationController {

	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/location/import", method = RequestMethod.GET)
	public String doImport() {
		long startTime = System.currentTimeMillis();
		CSVReader reader = null;
		long count = 0;

		try {
			String importFile = "/tmp/GeoLiteCity-Location.csv";
			reader = new CSVReader(new InputStreamReader(new FileInputStream(importFile), "Windows-1252"));

			String[] string;
			reader.readNext();
			log.debug("Started Location import");

			reader.readNext();
			string = reader.readNext();
			while (string != null && string.length > 0) {
				count++;
				Long extId = Long.valueOf(string[0]);
				String countryCode = string[1];
				String region = string[2];
				String city = string[3];
				String postalCode = string[4];
				Double latitude = (string[5].equals("")) ? null : Double.valueOf(string[5]);
				Double longtitude = (string[6].equals("")) ? null : Double.valueOf(string[6]);
				Integer metroCode = (string[7].equals("")) ? null : Integer.valueOf(string[7]);
				Integer areaCode = (string[8].equals("")) ? null : Integer.valueOf(string[8]);
				Location location = new Location(extId, countryCode, region, city, postalCode, latitude, longtitude,
						metroCode, areaCode);
				locationService.addLocation(location);

				if ((count % 50000) == 0) {
					log.debug("Location import in progress: {0}", count);
				}

				string = reader.readNext();
			}
			log.debug("Location insert count: {0}", count);
		} catch (Exception ex) {
			log.error("Error csv", ex);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					log.error("Error closing csv", ex);
				}
			}
		}

		long endTime = System.currentTimeMillis();
		long seconds = (endTime - startTime) / 1000;
		log.debug("Location import took: {0} s, Rows inserted: " + count, seconds);
		return "Done location entries import. Rows inserted: " + count + ". Duration: " + seconds + " s";

	}

}
