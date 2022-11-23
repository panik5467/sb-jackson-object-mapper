package com.mkyong;

import com.mkyong.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mkyong.service.Address;
import com.mkyong.service.Employee;

@SpringBootApplication
public class Application implements CommandLineRunner {

private static final ObjectMapper mapper = new ObjectMapper();

String json = "{ \"id\": 1, \"name\": { \"first\": null, \"last\": \"Mook Kim\" }, \"contact\": [ { \"type\": \"phone/home\", \"ref\": \"111-111-1234\" }, { \"type\": \"phone/work\", \"ref\": \"222-222-2222\" } ] }";

    @Autowired
    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(weatherService.forecast());
		testJackson();
    }

	public static void testJackson() throws IOException {
		
		//read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("employee.json"));
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		Employee emp = objectMapper.readValue(jsonData, Employee.class);		
		
		// Jackson JSON - Converting JSON to Object
		System.out.println("********************* Converting JSON to Object");
		System.out.println(emp);

		// Jackson JSON - Serializing Object to JSON string
		Employee emp1 = createEmployee();

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
		System.out.println("********************* Serializing Object to JSON");
        System.out.println(json);   // pretty-print

		
		// Jackson JSON - Serializing Object to JSON string
		//Employee emp1 = createEmployee();
		//configure Object mapper for pretty print
		//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//writing to console, can write to any output stream such as file
		//StringWriter stringEmp = new StringWriter();
		//objectMapper.writeValue(stringEmp, emp1);
		//System.out.println("Employee JSON is\n"+stringEmp);
		
		// Jackson JSON - Converting JSON to Map
		Map<String,Object> myMap = new HashMap<String, Object>();		
		myMap = objectMapper.readValue(jsonData, HashMap.class);
		System.out.println("********************* JSON to Map");
		System.out.println(myMap);


		// Jackson JSON - Converting JSON to Map
		//Map<String,Object> myMap = new HashMap<String, Object>();
		myMap.put("role2","Assistant");
		json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myMap);
		System.out.println("********************* MAP to JSON");
		System.out.println(json);

	}

	private static Employee createEmployee() {

		Employee emp = new Employee();
		emp.setId(100);
		emp.setName("David");
		emp.setPermanent(false);
		emp.setPhoneNumbers(new long[] { 123456, 987654 });
		emp.setRole("Manager");

		Address add = new Address();
		add.setCity("Bangalore");
		add.setStreet("BTM 1st Stage");
		add.setZipcode(560100);
		emp.setAddress(add);

		List<String> cities = new ArrayList<String>();
		cities.add("Los Angeles");
		cities.add("New York");
		emp.setCities(cities);

		Map<String, String> props = new HashMap<String, String>();
		props.put("salary", "1000 Rs");
		props.put("age", "28 years");
		emp.setProperties(props);

		return emp;
	}


private void test() {

        HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("age",null);
        map.put("name", map1);

        try {

            // convert map to JSON string
            String json = mapper.writeValueAsString(map);

            System.out.println(json);   // compact-print

            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            System.out.println(json);   // pretty-print

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

}

}
