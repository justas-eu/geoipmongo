package eu.justas.geoipmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import eu.justas.geoipmongo.model.Location;

@Repository
public class LocationService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "location";

	public void addLocation(Location location) {
		if (!mongoTemplate.collectionExists(Location.class)) {
			mongoTemplate.createCollection(Location.class);
		}
		mongoTemplate.insert(location, COLLECTION_NAME);
	}

	public List<Location> listLocation() {
		return mongoTemplate.findAll(Location.class, COLLECTION_NAME);
	}

	public void deleteLocation(Location location) {
		mongoTemplate.remove(location, COLLECTION_NAME);
	}

	public void updateLocation(Location location) {
		mongoTemplate.insert(location, COLLECTION_NAME);
	}
}
