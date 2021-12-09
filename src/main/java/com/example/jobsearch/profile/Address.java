package com.example.jobsearch.profile;

public class Address  {
	private final String houseNumber;
	private final String road;
	private final String city;
	private final String state;
	private final String zip;

	public Address(String houseNumber, String road, 
	String city, String state, String zip) {
		this.houseNumber = houseNumber;
		this.road = road;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getHouseNumber() { return houseNumber; }
	public String getRoad() { return road; }
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getZip() { return zip; }
	
	@Override
	public int hashCode() {
		return city.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Address another = (Address) obj;
		return houseNumber.equals(another.houseNumber) 
			&& road.equals(another.road)
			&& city.equals(another.city)
			&& state.equals(another.state)
			&& zip.equals(another.zip);
	}

	@Override
	public String toString() {
		return houseNumber + " " + road + ", " + city 
			+ " " + state + " " + zip;
	}
}