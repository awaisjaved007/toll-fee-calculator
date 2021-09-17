package com.assignment.tcs.service.impl;

import com.assignment.tcs.service.HolidayTollCalculatorService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

public class HolidayTollCalculatorServiceImpl implements HolidayTollCalculatorService
{
	/**
	 * Find is provided date is toll free
	 *
	 * @param date - the date for which toll is being calculated
	 * @return - a boolean value true if toll free and false if date is not toll free
	 */
	@Override
	public boolean isDateTollFree(LocalDateTime date)
	{
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();

		return (isWeekEnd(date.getDayOfWeek()) || isTollFreeForSpecificDatesOfYear(year, month, day));
	}

	//Assuming this is 2021 so we have to change year value to 2021
	//Year=2021 needs to be the part of application properties that can be modified later
	private boolean isTollFreeForSpecificDatesOfYear(int year, Month month, int day)
	{
		return (year == 2021 &&
				(month == Month.JANUARY && day == 1 ||
						month == Month.MARCH && (day == 28 || day == 29) ||
						month == Month.APRIL && (day == 1 || day == 30) ||
						month == Month.MAY && (day == 1 || day == 8 || day == 9) ||
						month == Month.JUNE && (day == 5 || day == 6 || day == 21) ||
						month == Month.JULY ||
						month == Month.NOVEMBER && day == 1 ||
						month == Month.DECEMBER && (day == 24 || day == 25 || day == 26 || day == 31)));
	}

	private boolean isWeekEnd(DayOfWeek dayOfWeek)
	{
		return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
	}

}
