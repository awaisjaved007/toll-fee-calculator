package com.assignment.tcs.service;

import com.assignment.tcs.domain.impl.*;
import com.assignment.tcs.service.impl.HolidayTollCalculatorServiceImpl;
import com.assignment.tcs.service.impl.TollCalculatorServiceImpl;
import com.assignment.tcs.service.impl.VehicleSpotterServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TollCalculatorTest
{
	private static TollCalculatorService tollCalculatorService;

	@BeforeAll
	static void initAll()
	{
		tollCalculatorService = new TollCalculatorServiceImpl(new VehicleSpotterServiceImpl(), new HolidayTollCalculatorServiceImpl());
	}

	@Test
	void when_carCrosses_in_multiple_timeSpans_then_highestValuesInEachSpanOf_tollFee_shouldBeAdd_as_totalToll()
	{
		LocalDateTime date1 = getDate(2021, Month.FEBRUARY, 11, 6, 29, 0);
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 6, 45, 0);
		LocalDateTime date3 = getDate(2021, Month.FEBRUARY, 11, 7, 10, 0);
		LocalDateTime date4 = getDate(2021, Month.FEBRUARY, 11, 7, 29, 0);
		LocalDateTime date5 = getDate(2021, Month.FEBRUARY, 11, 7, 45, 0);
		LocalDateTime date6 = getDate(2021, Month.FEBRUARY, 11, 8, 15, 0);
		LocalDateTime date7 = getDate(2021, Month.FEBRUARY, 11, 14, 59, 0);
		LocalDateTime date8 = getDate(2021, Month.FEBRUARY, 11, 15, 29, 0);
		LocalDateTime date9 = getDate(2021, Month.FEBRUARY, 11, 15, 39, 0);

		assertEquals(54, tollCalculatorService.getTollFee(new Car(), date1, date2, date3, date4, date5, date6, date7, date8, date9));
	}

	@Test
	void when_carCrossesAt9Hour_then_tollFee_isGreaterThanZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 8, 30, 0);
		assertEquals(8, tollCalculatorService.getTollFee(new Car(), date2));
	}

	@Test
	void when_diplomatCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 8, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Diplomat(), date2));
	}

	@Test
	void when_emergencyTypeCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 8, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Emergency(), date2));
	}

	@Test
	void when_foreignTypeCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 23, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Foreign(), date2));
	}

	@Test
	void when_militaryTypeCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 23, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Military(), date2));
	}

	@Test
	void when_mototBikeTypeCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 23, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Motorbike(), date2));
	}

	@Test
	void when_TractorTypeCrossesAtAnyHour_then_tollFee_isZero()
	{
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 23, 30, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Tractor(), date2));
	}

	@Test
	void when_carCrossesAt9Hour_in_twoTimes_then_tollFee_isHigherValueOfBoth()
	{

		LocalDateTime date1 = getDate(2021, Month.FEBRUARY, 11, 8, 29, 0);
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 8, 30, 0);

		assertEquals(13, tollCalculatorService.getTollFee(new Car(), date1, date2));
	}

	@Test
	void when_carCrosses_three_time_then_tollFee_isHigherValueOfBoth()
	{

		LocalDateTime date1 = getDate(2021, Month.FEBRUARY, 11, 6, 24, 0);
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 6, 50, 0);
		LocalDateTime date3 = getDate(2021, Month.FEBRUARY, 11, 7, 20, 0);

		assertEquals(18, tollCalculatorService.getTollFee(new Car(), date1, date2, date3));
	}

	@Test
	void when_carCrossesAtSunday_then_tollFee_isZero()
	{
		LocalDateTime date1 = getDate(2021, Month.APRIL, 11, 8, 29, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Car(), date1));
	}



	@Test
	void when_tollFreeCarAndCarCrossesAtSunday_then_tollFee_isZero()
	{
		LocalDateTime date1 = getDate(2021, Month.APRIL, 11, 8, 29, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Motorbike(), date1));
	}

	@Test
	void when_tollFreeCarCrosses_then_tollFee_isZero()
	{
		LocalDateTime date1 = getDate(2021, Month.APRIL, 12, 8, 29, 0);
		assertEquals(0, tollCalculatorService.getTollFee(new Motorbike(), date1));
	}

	@Test
	void when_carPassesMultipleTimesIn_a_day_then_tollFeeCharged_is60(){
		LocalDateTime date1 = getDate(2021, Month.FEBRUARY, 11, 8, 29, 0);
		LocalDateTime date2 = getDate(2021, Month.FEBRUARY, 11, 9, 30, 0);
		LocalDateTime date3 = getDate(2021, Month.FEBRUARY, 11, 10, 30, 0);
		LocalDateTime date4 = getDate(2021, Month.FEBRUARY, 11, 11, 30, 0);
		LocalDateTime date5 = getDate(2021, Month.FEBRUARY, 11, 12, 30, 0);
		LocalDateTime date6 = getDate(2021, Month.FEBRUARY, 11, 13, 30, 0);
		LocalDateTime date7 = getDate(2021, Month.FEBRUARY, 11, 14, 30, 0);
		LocalDateTime date8 = getDate(2021, Month.FEBRUARY, 11, 15, 30, 0);
		LocalDateTime date9 = getDate(2021, Month.FEBRUARY, 11, 16, 30, 0);
		LocalDateTime date10 = getDate(2021, Month.FEBRUARY, 11, 17, 30, 0);
		LocalDateTime date11 = getDate(2022, Month.FEBRUARY, 11, 18, 30, 1);

		assertEquals(
				60,
				tollCalculatorService.getTollFee(new Car(), date1,
													date2, date3, date4, date5,
													date6, date7, date8, date9,
													date10, date11));
	}

	private LocalDateTime getDate(int year, Month month, int dayOfMonth, int hour, int minute, int second)
	{
		return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
	}
}
