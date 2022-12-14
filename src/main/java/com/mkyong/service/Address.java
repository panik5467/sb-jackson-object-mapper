package com.mkyong.service;

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
@AllArgsConstructor //(access = AccessLevel.PRIVATE)
@NoArgsConstructor //(access = AccessLevel.PRIVATE)
**/

@Jacksonized
@Value
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Hides the constructor to force useage of the Builder.

public class Address {
	
	private String street;
	private String city;
	private int zipcode;


}