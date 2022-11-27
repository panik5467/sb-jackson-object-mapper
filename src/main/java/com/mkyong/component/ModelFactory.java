package com.mkyong.component;

import com.mkyong.component.NestedModel;
import com.mkyong.component.UpdateId;
import com.mkyong.component.DeviceUpdate;
import com.mkyong.component.Properties;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.context.annotation.Configuration
public class ModelFactory {

	@org.springframework.context.annotation.Bean
	public NestedModel createNestedModel() {

		UpdateId u = UpdateId.builder()
		.version("version")
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
		.name("from ModelFactory")
		.deviceUpdate(d)
		.build();
		
		return model;
	}

}