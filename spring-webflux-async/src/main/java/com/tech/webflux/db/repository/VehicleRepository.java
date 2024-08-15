package com.tech.webflux.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.webflux.db.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
