package com.tech.webflux.service;

import java.util.List;

import com.tech.webflux.dto.ServiceResponseDto;
import com.tech.webflux.dto.VehicleDto;

public interface VehicleService {

	ServiceResponseDto<VehicleDto> createVehicle(VehicleDto vehicleDto);
	
	ServiceResponseDto<VehicleDto> updateVehicle(VehicleDto vehicleDto);
	
	ServiceResponseDto<VehicleDto> getVehicle(int vehicleId);
	
	ServiceResponseDto<List<VehicleDto>> getVehiclesList();
	
	ServiceResponseDto<VehicleDto> deleteVehicle(int vehicleId);
}
