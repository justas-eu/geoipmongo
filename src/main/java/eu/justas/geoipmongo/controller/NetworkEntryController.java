package eu.justas.geoipmongo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.bytecode.opencsv.CSVReader;
import eu.justas.geoipmongo.model.Location;
import eu.justas.geoipmongo.model.NetworkEntry;
import eu.justas.geoipmongo.service.NetworkEntryService;
import eu.justas.geoipmongo.utils.IpCalculator;

@Controller
public class NetworkEntryController {

	private static final Logger log = LoggerFactory.getLogger(NetworkEntryController.class);

	@Autowired
	private NetworkEntryService networkEntryService;

	@RequestMapping(value = "/network/find", method = RequestMethod.GET)
	public String findByIp(@RequestParam("ip") String ip, ModelMap model) {
		long startTime = System.currentTimeMillis();

		Long longIp = null;
		try {
			longIp = IpCalculator.getLong(ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NetworkEntry networkEntry = networkEntryService.findNetworkEntryByIp(longIp);
		if (networkEntry != null) {
	        networkEntry.setStartIpString(IpCalculator.longToIp(networkEntry.getStartIp()));
	        networkEntry.setEndIpString(IpCalculator.longToIp(networkEntry.getEndIp()));
		}
		log.debug("Found network: " + networkEntry);
		model.addAttribute("networkEntry", networkEntry);

		long endTime = System.currentTimeMillis();
		long mseconds = (endTime - startTime);
		log.debug("Search took: " + mseconds + " ms");
		return "network";

	}

	@RequestMapping(value = "/network/import", method = RequestMethod.GET)
	public String doImport() {
		long startTime = System.currentTimeMillis();
		long count = 0;
		CSVReader reader = null;

		try {
			String importFile = "/Users/justas/Desktop/tmp/GeoLiteCity_20130806/GeoLiteCity-Blocks.csv";
			reader = new CSVReader(new InputStreamReader(new FileInputStream(importFile), "Windows-1252"));
			String[] string;
			reader.readNext();
			log.debug("Started network import");

			reader.readNext();
			string = reader.readNext();
			while (string != null && string.length > 0) {
				count++;

				Long startIp = Long.valueOf(string[0]);
				Long endId = Long.valueOf(string[1]);
				Long locationId = Long.valueOf(string[2]);
				NetworkEntry networkEntry = new NetworkEntry(startIp, endId, null);
				Location location = new Location();
				location.setId(locationId);
				networkEntry.setLocation(location);
				networkEntryService.addNetworkEntry(networkEntry);
				if ((count % 50000) == 0) {
					log.debug("Network import in progress: {0}", count);
				}

				string = reader.readNext();
			}

		} catch (Exception ex) {

			log.error(null, ex);
		} finally {
			log.debug("Network import count: {0}", count);

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					log.error(null, ex);
				}
			}
		}

		long endTime = System.currentTimeMillis();
		long seconds = (endTime - startTime) / 1000;
		log.debug("Network import took: {0} s", seconds);
		return "Done network entries import. Rows inserted: " + count + ". Duration: " + seconds + " s";

	}

}
