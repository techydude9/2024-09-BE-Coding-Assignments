package dog.rescue.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import dog.rescue.DogRescueApplication;
import dog.rescue.controller.model.LocationData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
		classes = DogRescueApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class RescueControllerTest  extends RescueServiceTestSupport {

	@Test
	void testInsertLocation() {
		// Given: A location request
		LocationData request = buildInsertLocation(1);
		LocationData expected = buildInsertLocation(1);
		
		// When: the location is added to the location table
		LocationData actual = insertLocation(request);
		
		
		// Then: the location is returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is  one row in the location table.
		assertThat(rowsInLocationTable()).isOne();
		
	} // end of testInsertLocation method ----

}  // end of RescueControllerTest class -----------
