package com.tech.webflux.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.webflux.dto.ServiceResponseDto;
import com.tech.webflux.dto.VehicleDto;
import com.tech.webflux.dto.VehicleRequest;
import com.tech.webflux.exception.BadRequestException;
import com.tech.webflux.exception.InternalServerError;
import com.tech.webflux.exception.VehicleNotFoundException;
import com.tech.webflux.service.VehicleService;
import com.tech.webflux.util.ApplicationProperties;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/vehicle")
@Api(tags="Vehicle")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
    private VehicleService vehicleService;
	
	@Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody VehicleRequest req) {
        try {
            VehicleDto vehicleDto = modelMapper.map(req, VehicleDto.class);
            ServiceResponseDto<VehicleDto> response = vehicleService.createVehicle(vehicleDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(vehicleDto, HttpStatus.CREATED);
                case BAD_REQUEST:
                	throw new BadRequestException(ApplicationProperties.ERROR_BAD_REQUEST);
                default:
                	throw new InternalServerError(ApplicationProperties._500_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in create vehicle api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById (@PathVariable("id") int id) {
    	ServiceResponseDto<VehicleDto> response = vehicleService.getVehicle(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<VehicleDto>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	        	throw new VehicleNotFoundException(ApplicationProperties.ERROR_VECHICLE_NOT_FOUND+ id);
	        default:
	            throw new InternalServerError(ApplicationProperties._500_ERROR);
    	}
    }
    
    @GetMapping
    public ResponseEntity<List<VehicleDto>> getVehicleList () {
    	ServiceResponseDto<List<VehicleDto>> response = vehicleService.getVehiclesList();
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<List<VehicleDto>>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	        	throw new VehicleNotFoundException(ApplicationProperties.VECHICLE_NOT_FOUND);
	        default:
	        	 throw new InternalServerError(ApplicationProperties._500_ERROR);
    	}
    }
    
    @PutMapping
    public ResponseEntity<Object> updateVehicle(@RequestBody VehicleRequest req) {
        try {
            VehicleDto vehicleDto = modelMapper.map(req, VehicleDto.class);
            ServiceResponseDto<VehicleDto> response = vehicleService.updateVehicle(vehicleDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                case BAD_REQUEST:
                	throw new BadRequestException(ApplicationProperties.ERROR_BAD_REQUEST);
                default:
                	 throw new InternalServerError(ApplicationProperties._500_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in update vehicle api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDto> deleteVehicleById (@PathVariable("id") int id) {
    	ServiceResponseDto<VehicleDto> response = vehicleService.deleteVehicle(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<VehicleDto>(HttpStatus.ACCEPTED);
	        case NOT_FOUND:
	        	throw new VehicleNotFoundException(ApplicationProperties.ERROR_VECHICLE_NOT_FOUND+id);
	        default:
	        	 throw new InternalServerError(ApplicationProperties._500_ERROR);
    	}
    }

}
