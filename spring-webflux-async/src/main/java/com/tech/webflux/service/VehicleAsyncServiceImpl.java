package com.tech.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.tech.webflux.db.domain.Vehicle;
import com.tech.webflux.db.repository.VehicleRepository;
import com.tech.webflux.dto.VehicleDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class VehicleAsyncServiceImpl implements VehicleAsyncService{
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
    private ModelMapper modelMapper;

	@Override
	public Mono<VehicleDto> createVehicleAsync(VehicleDto vehicleDto) {
		CompletableFuture<VehicleDto> future = CompletableFuture.supplyAsync(() -> {
			Vehicle vehicleDomain = modelMapper.map(vehicleDto, Vehicle.class);
			vehicleDomain = vehicleRepo.save(vehicleDomain);
			vehicleDto.setId(vehicleDomain.getId());
			return vehicleDto;
		});
		Mono<VehicleDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
	
	@Override
	public Mono<VehicleDto> getVehicleAsync(int vehicleId) {
		CompletableFuture<VehicleDto> future = CompletableFuture.supplyAsync(() -> {
			
			Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
			VehicleDto dto = null;
			if (vehicleOptional.isPresent()) {
				Vehicle v = vehicleOptional.get();
				dto = modelMapper.map(v, VehicleDto.class);
			}
			return dto;
		});
		Mono<VehicleDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Flux<VehicleDto> getVehicleListAsync() {
		return Flux.create((emitter) -> {
			CompletableFuture<List<VehicleDto>> future = CompletableFuture.supplyAsync( () -> {
				List<Vehicle> vehicleList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
				List<VehicleDto> vehicleDtoList = vehicleList.parallelStream().map( (vehicle) -> {
					return modelMapper.map(vehicle, VehicleDto.class);
				}).collect(Collectors.toList());
				return vehicleDtoList;
			});
			future.whenComplete( (vehicleDtoList, exception) -> {
				if (exception == null) {
					vehicleDtoList.forEach(emitter::next);
					emitter.complete();
				} else {
					emitter.complete();
				}
			});
		});
	}

	@Override
	public Mono<Void> updateVehicleAsync(VehicleDto vehicleDto) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Vehicle vehicleDomain = modelMapper.map(vehicleDto, Vehicle.class);
			vehicleDomain = vehicleRepo.save(vehicleDomain);
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Mono<Void> deleteVehicleAsync(int vehicleId) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			
			Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
			if (vehicleOptional.isPresent()) {
				vehicleRepo.delete(vehicleOptional.get());;
			}
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
}
