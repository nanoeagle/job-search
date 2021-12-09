package com.example.jobsearch.profile;

import java.io.*;

import com.example.jobsearch.util.Http;

import org.json.JSONObject;

public class AddressRetriever {
	private Http http;

	public Address retrieve(double latitude, double longitude)
	throws IOException {
		String coordinates = String.format("lat=%.6f&lon=%.6f", 
			latitude, longitude);
		JSONObject addressObj = retrieveAddressJSONObjectBasedOn(coordinates);
		throwExceptionIfCountryIsNotUS(addressObj);
		return createAddressBasedOn(addressObj);
	}

	private JSONObject retrieveAddressJSONObjectBasedOn(String coordinates) 
	throws IOException {
		String response = http.get("somewhere.com" + coordinates);
		JSONObject responseObj = (JSONObject) new JSONObject(response);
		return (JSONObject) responseObj.get("address");
	}

	private void throwExceptionIfCountryIsNotUS(JSONObject addressObj) {
		String country = (String) addressObj.get("country_code");
		if ( !country.equals("us") )
			throw new UnsupportedOperationException(
				"Cannot support non-US addresses at this time");
	}

	private Address createAddressBasedOn(JSONObject addressObj) {
		String houseNumber = addressObj.getNumber("house_number").toString();
		String road = addressObj.getString("road");
		String city = addressObj.getString("city");
		String state = addressObj.getString("state");
		String zip = addressObj.getNumber("postcode").toString();
		return new Address(houseNumber, road, city, state, zip);
	}
}