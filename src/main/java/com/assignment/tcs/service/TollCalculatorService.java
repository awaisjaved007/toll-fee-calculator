package com.assignment.tcs.service;

import com.assignment.tcs.domain.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;

public interface TollCalculatorService
{
	 int getTollFee(Vehicle vehicle, LocalDateTime... dates);
}
