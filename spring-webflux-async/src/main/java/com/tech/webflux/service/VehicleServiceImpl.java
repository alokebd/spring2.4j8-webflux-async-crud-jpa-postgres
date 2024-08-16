package com.tech.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.tech.webflux.db.domain.Vehicle;
import com.tech.webflux.db.repository.VehicleRepository;
import com.tech.webflux.dto.ServiceResponseDto;
import com.tech.webflux.dto.VehicleDto;
import com.tech.webflux.util.Constants;

@Component
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public ServiceResponseDto<VehicleDto> createVehicle(VehicleDto vehicleDto) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		Vehicle vehicleDomain = modelMapper.map(vehicleDto, Vehicle.class);
		vehicleDomain = vehicleRepo.save(vehicleDomain);
		
		vehicleDto.setId(vehicleDomain.getId());
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> updateVehicle(VehicleDto vehicleDto) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleDto.getId());
		vehicleOptional.ifPresent( vehicle -> {
			
			Vehicle vehicleDomain = modelMapper.map(vehicleDto, Vehicle.class);
			vehicleRepo.save(vehicleDomain);
			
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> getVehicle(int vehicleId) {
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		VehicleDto dto = null;
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		if (vehicleOptional.isPresent()) {
			Vehicle v = vehicleOptional.get();
			dto = modelMapper.map(v, VehicleDto.class);
			responseDto.setResponseObject(dto);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		}
		return responseDto;
	}

	@Override
	public ServiceResponseDto<List<VehicleDto>> getVehiclesList() {
		ServiceResponseDto<List<VehicleDto>> responseDto = new ServiceResponseDto<List<VehicleDto>>();
		
		List<Vehicle> vehicleList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
		
		List<VehicleDto> vehicleDtoList = vehicleList.parallelStream().map( (vehicle) -> {
			return modelMapper.map(vehicle, VehicleDto.class);
		}).collect(Collectors.toList());
		
		responseDto.setResponseObject(vehicleDtoList);
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> deleteVehicle(int vehicleId) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
		vehicleOptional.ifPresent( vehicle -> {
			
			vehicleRepo.delete(vehicle);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}
	
}
