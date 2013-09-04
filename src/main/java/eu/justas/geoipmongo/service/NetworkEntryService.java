package eu.justas.geoipmongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import eu.justas.geoipmongo.model.NetworkEntry;

@Repository
public class NetworkEntryService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "networkEntry";
	
	public void addNetworkEntry(NetworkEntry networkEntry) {
		if (!mongoTemplate.collectionExists(NetworkEntry.class)) {
			mongoTemplate.createCollection(NetworkEntry.class);
		}		
		mongoTemplate.insert(networkEntry, COLLECTION_NAME);
	}
	
	public List<NetworkEntry> listNetworkEntry() {
		return mongoTemplate.findAll(NetworkEntry.class, COLLECTION_NAME);
	}
	
	public void deleteNetworkEntry(NetworkEntry networkEntry) {
		mongoTemplate.remove(networkEntry, COLLECTION_NAME);
	}
	
	public void updateNetworkEntry(NetworkEntry networkEntry) {
		mongoTemplate.insert(networkEntry, COLLECTION_NAME);		
	}
	
	public NetworkEntry findNetworkEntryByIp(Long ip) {
		Query query = new Query();
		query.addCriteria(Criteria.where("startIp").lt(ip).and("endIp").gt(ip));
		return mongoTemplate.findOne(query, NetworkEntry.class);		
	}
	
}
