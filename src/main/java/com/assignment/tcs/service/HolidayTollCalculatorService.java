package com.assignment.tcs.service;

import java.time.LocalDateTime;

public interface HolidayTollCalculatorService
{
	boolean isDateTollFree(LocalDateTime date);
}
