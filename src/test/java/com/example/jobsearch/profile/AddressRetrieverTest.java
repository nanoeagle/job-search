package com.example.jobsearch.profile;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import com.example.jobsearch.util.Http;

import org.junit.*;
import org.mockito.*;

public class AddressRetrieverTest {
	@Mock private Http http;
	@InjectMocks private AddressRetriever addressRetriever;
	private AutoCloseable closeable;

	@Before
	public void initCloseable() {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@Before
	public void initAddressRetriever() {
		addressRetriever = new AddressRetriever();
	}

	@After
	public void releaseMocks() throws Exception {
		closeable.close();
	}

	@Test
	public void retrieverRetrievesExpectedAddressFromCoordinates() 
	throws IOException {
		Address expectedAddress = new Address("324", "North Tejon Street", 
			"Colorado Springs", "Colorado", "80903");
		when(http.get(contains("lat=1.000000&lon=2.000000"))).thenReturn(
			"{address: {"
				+ "house_number: " + expectedAddress.getHouseNumber() + ","
				+ "road: " + expectedAddress.getRoad() + ","
				+ "city: " + expectedAddress.getCity() + ","
				+ "state: " + expectedAddress.getState() + ","
				+ "postcode: " + expectedAddress.getZip() + ","
				+ "country_code: us}" +
			"}"
		);
		Address retrievedAddress = addressRetriever.retrieve(1l, 2l);
		assertThat(retrievedAddress, equalTo(expectedAddress));
	}
}