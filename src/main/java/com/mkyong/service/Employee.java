package com.mkyong.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;


/**
@Builder
@ToString
@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor //(access = AccessLevel.PRIVATE)
@NoArgsConstructor //(access = AccessLevel.PRIVATE)

**/
@Jacksonized
@Value
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Hides the constructor to force useage of the Builder.
public class Employee {

	private int id;
	private String name;
	private boolean permanent;
	private Address address;
	private long[] phoneNumbers;
	private String role;
	private List<String> cities;
	private Map<String, String> properties;


}