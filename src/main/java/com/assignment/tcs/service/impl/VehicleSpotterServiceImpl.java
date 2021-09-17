package com.assignment.tcs.service.impl;

import com.assignment.tcs.constant.TollFreeVehicles;
import com.assignment.tcs.domain.Vehicle;
import com.assignment.tcs.service.VehicleSpotterService;

public class VehicleSpotterServiceImpl implements VehicleSpotterService
{
	/**
	 * Find is provided vehicle is toll free
	 *
	 * @param vehicle - the vehicle
	 * @return - boolean value true if toll free and false if vehicle is not toll free
	 */
	@Override
	public boolean isTollFreeVehicle(Vehicle vehicle) {
		if(vehicle == null) return false;
		String vehicleType = vehicle.getType();
		return vehicleType.equals(TollFreeVehicles.MOTORBIKE.getType()) ||
				vehicleType.equals(TollFreeVehicles.TRACTOR.getType()) ||
				vehicleType.equals(TollFreeVehicles.EMERGENCY.getType()) ||
				vehicleType.equals(TollFreeVehicles.DIPLOMAT.getType()) ||
				vehicleType.equals(TollFreeVehicles.FOREIGN.getType()) ||
				vehicleType.equals(TollFreeVehicles.MILITARY.getType());
	}
}
