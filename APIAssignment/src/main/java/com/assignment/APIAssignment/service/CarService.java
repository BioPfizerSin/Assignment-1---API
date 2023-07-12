package com.assignment.APIAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.APIAssignment.repository.CarRepository;
import com.assignment.APIAssignment.entity.Car;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

	@Autowired
	private CarRepository carRepository;

	// Constructor
	public CarService(CarRepository car_repo) {
		// TODO Auto-generated constructor stub
		this.carRepository = car_repo;
	}

	public List<Car> getAllCars() {
		System.out.println("In get call car service");
		return carRepository.findAll();
	}

	// used for both update and Save Car
	public void saveCar(Car car) {
		carRepository.save(car);
	}

	public Car getCarById(Long id) {
		return carRepository.findById(id).get();
	}

	public boolean delete(Long id) {
		Optional<Car> optionalCar = carRepository.findById(id);

		if (optionalCar.isPresent()) {
			Car car = optionalCar.get();
			carRepository.delete(car);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateCar(Long carId, Car updatedCar) {
		Optional<Car> optionalCar = carRepository.findById(carId);
		
		if (optionalCar.isPresent()) {
			Car car = optionalCar.get();
			// Update the car properties with the new values
			car.setMake(updatedCar.getMake());
			car.setModel(updatedCar.getModel());
			car.setRegistration(updatedCar.getRegistration());
			car.setPrice(updatedCar.getPrice());
			// Save the updated car
			carRepository.save(car);
			return true;
		} else {
			return false;
		}
	}

	public List<Car> search(String keyword) {
		return carRepository.search(keyword);

	}

}
