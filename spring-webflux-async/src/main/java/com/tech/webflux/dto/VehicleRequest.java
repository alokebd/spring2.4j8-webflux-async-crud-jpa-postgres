package com.tech.webflux.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class VehicleRequest {

	private String model;
	private String make;
	private String vin;
	private String color;
	private String year;
	private int id;
}
