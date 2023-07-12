package com.assignment.APIAssignment.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.APIAssignment.service.CarService;
import com.assignment.APIAssignment.entity.*;

@RestController
public class CarController {
	
	@Autowired
	private CarService car_service;
	
	//Test Home API
	@RequestMapping("/")
	public String homeAPI() {
		return "first API call- Home";
	}
	
	//Get all car API
	@GetMapping("/cars")
    public List<Car> getAllCars() {
       return car_service.getAllCars();
      
    }
	
	//Save Car API
	@PostMapping("/cars/new")
	public String saveCar(@RequestBody Car c) {
		car_service.saveCar(c);
		return "Car Save successfully";
	}
	
//	//View Car API
//	@GetMapping("/viewcar_api/{car_id}")
//	Car viewCar(@PathVariable long car_id) {
//		return car_service.getCarById(car_id);
//		
//	}
	
	//SearchCarAPI
	@GetMapping("/cars/{keyword}")
	public List<Car> searchCar(@PathVariable String keyword) {
		return car_service.search(keyword);
	}
	
	//DeleteCarAPI
	@DeleteMapping("/cars/{id}")
	public String deleteCar(@PathVariable Long id) {
		boolean deleted = car_service.delete(id);
		if (deleted) {
			return "Car deleted successfully";
		} else {
			return "Failed to delete car";
		}
	}
	
	// Update Car API
		@PutMapping("/cars/{carId}/update")
		public String updateCar(@PathVariable Long carId, @RequestBody Car updatedCar) {
			boolean updated = car_service.updateCar(carId, updatedCar);
			if (updated) {
				return "Car updated successfully";
			} else {
				return "Failed to update car";
			}
		}
	

}

