package eu.justas.geoipmongo.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author justas
 */
@XmlRootElement
@Document
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String countryCode;
	private String region;
	private String city;
	private String postalCode;
	private Double latitude;
	private Double longitude;
	private Integer metroCode;
	private Integer areaCode;

	public Location() {
	}

	public Location(Long extId, String countryCode, String region, String city, String postalCode, Double latitude,
			Double longitude, Integer metroCode, Integer areaCode) {
		this.id = extId;
		this.countryCode = countryCode;
		this.region = region;
		this.city = city;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.metroCode = metroCode;
		this.areaCode = areaCode;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getMetroCode() {
		return metroCode;
	}

	public void setMetroCode(Integer metroCode) {
		this.metroCode = metroCode;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Location)) {
			return false;
		}
		Location other = (Location) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "eu.justas.geoip.model.Location[ id=" + id + " ]";
	}
}
