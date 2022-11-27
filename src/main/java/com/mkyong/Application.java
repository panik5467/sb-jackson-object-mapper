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
import com.mkyong.component.NestedModel;
import com.mkyong.component.UpdateId;
import com.mkyong.component.DeviceUpdate;
import com.mkyong.component.Properties;
import com.mkyong.component.ModelFactory;

@SpringBootApplication
public class Application implements CommandLineRunner {

private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
	private NestedModel bootModel; // Object initialized at boot
	
    @Autowired
    private WeatherService weatherService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(weatherService.forecast());
		
		testNestedModel();
		//testLombok();
		//testJackson();
    }

    public void testNestedModel() throws IOException {

		NestedModel model = createNestedModel();
		
		model = mapper.readValue(new File("nested.json"), NestedModel.class);

		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bootModel);
		
		//System.out.println(model.getDeviceUpdate().getProperties().getUpdateId().getVersion());
		System.out.println(model.getDeviceUpdate());
		
		System.in.read();
    }
	
    public void testLombok() throws IOException {

		Employee emp = createEmployee();
		
		System.out.println(emp);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
		System.out.println(json);
		
		emp = mapper.readValue(new File("employee.json"), Employee.class);		
		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
		System.out.println(json);
		
		// json TREE model
		JsonNode empNode = mapper.valueToTree(emp);
		System.out.println(empNode);
		
		// Map to Object
		Map<String,Object> hhh = Map.of("provider","tartempion","name","kiloutou","version","V1.234");
		json = mapper.writeValueAsString(hhh);
		
		Map<String,Object> map = mapper.readValue(new File("employee.json"), HashMap.class);
		map.put("name", json);
		emp = mapper.convertValue(map, Employee.class);
		System.out.println(emp);

	}
	
	private static NestedModel createNestedModel() {

		UpdateId u = UpdateId.builder()
		.version("V1.18")
		.manufacturer("BTM 1st Stage")
		.model("modelAZER")
		.build();
		
		Properties p = Properties.builder()
		.name("Bangalore")
		.updateId(u)
		.build();
		
		DeviceUpdate d = DeviceUpdate.builder()
		.id(10000L)
		.name("Bangalore")
		.properties(p)
		.build();
		
		NestedModel model = NestedModel.builder()
		.name("BTM 1st Stage")
		.deviceUpdate(d)
		.build();
		
		return model;
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

		Address add = Address.builder()
		.city("Bangalore")
		.street("BTM 1st Stage")
		.zipcode(560100)
		.build();


		Employee emp = Employee.builder()
			.name("Elvis")
			.id(123456)
			.address(add)
			.phoneNumbers(new long[] {12345L,789456L})
			.role("Grand Chef")
			.cities(List.of("Paris","Strasbourg"))
			.properties(Map.of("prop1","Badabooul","prop2","rantanplan"))
			.build();
		return emp;
	}
/**	

{
  "id": 123,
  "name": "Pankaj",
  "permanent": true,
  "address": {
    "street": "Albany Dr",
    "city": "San Jose",
    "zipcode": 95129
  },
  "phoneNumbers": [
    123456,
    987654
  ],
  "role": "Manager",
  "cities": [
    "Los Angeles",
    "New York"
  ],
  "properties": {
    "age": "29 years",
    "salary": "1000 USD"
  }
  
	private static Employee createEmployee0() {

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
		
		Employee.Pays p = new Employee.Pays();
		p.setLangue("llll");
		p.setCouleur("cccc");
		p.setPopulation(123123123L);
		emp.setPays(p);

		return emp;
	}**/


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
