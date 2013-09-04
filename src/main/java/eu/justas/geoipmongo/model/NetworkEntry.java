package eu.justas.geoipmongo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author justas
 */
@Document
@XmlRootElement
public class NetworkEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@XmlTransient
	private Long startIp;

	@XmlTransient
	private Long endIp;

	@DBRef
	private Location location;

	@Transient
	private String startIpString;

	@Transient
	private String endIpString;

	public String getEndIpString() {
		return endIpString;
	}

	public void setEndIpString(String endIpString) {
		this.endIpString = endIpString;
	}

	public String getStartIpString() {
		return startIpString;
	}

	public void setStartIpString(String startIpString) {
		this.startIpString = startIpString;
	}

	public NetworkEntry(Long startIp, Long endIp, Location location) {
		this.startIp = startIp;
		this.location = location;
		this.endIp = endIp;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public NetworkEntry() {
	}

	public Long getStartIp() {
		return startIp;
	}

	public void setStartIp(Long startIp) {
		this.startIp = startIp;
	}

	public Long getEndIp() {
		return endIp;
	}

	public void setEndIp(Long endIp) {
		this.endIp = endIp;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 61 * hash + (this.startIp != null ? this.startIp.hashCode() : 0);
		hash = 61 * hash + (this.endIp != null ? this.endIp.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final NetworkEntry other = (NetworkEntry) obj;
		if (this.startIp != other.startIp && (this.startIp == null || !this.startIp.equals(other.startIp))) {
			return false;
		}
		if (this.endIp != other.endIp && (this.endIp == null || !this.endIp.equals(other.endIp))) {
			return false;
		}
		return true;
	}
}
