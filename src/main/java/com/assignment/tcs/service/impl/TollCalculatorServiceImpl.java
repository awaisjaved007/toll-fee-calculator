package com.assignment.tcs.service.impl;

import com.assignment.tcs.domain.Vehicle;
import com.assignment.tcs.service.HolidayTollCalculatorService;
import com.assignment.tcs.service.TollCalculatorService;
import com.assignment.tcs.service.VehicleSpotterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.String.format;

public class TollCalculatorServiceImpl implements TollCalculatorService
{

  private static final Logger LOGGER = LogManager.getLogger(TollCalculatorServiceImpl.class);
  private final VehicleSpotterService vehicleSpotterService;
  private final HolidayTollCalculatorService holidayTollCalculatorService;

  public TollCalculatorServiceImpl(VehicleSpotterService vehicleSpotterService, HolidayTollCalculatorService holidayTollCalculatorService)
  {
    this.vehicleSpotterService = vehicleSpotterService;
    this.holidayTollCalculatorService = holidayTollCalculatorService;
  }

  /**
   * Calculate the total toll fee for one day
   *
   * @param vehicle - the vehicle
   * @param dates   - date and time of all passes on one day
   * @return - the total toll fee for that day
   *
   * Assumption: dates should in Ascending order
   */
  @Override
  public int getTollFee(Vehicle vehicle, LocalDateTime... dates) {
    // Added a line for null date return zero toll
    if(dates == null || dates.length ==0) return 0;

    LOGGER.info(String.format("Request arguments: { Vehicle:%s , dates:%s}", vehicle, Arrays.toString(dates)));
    LocalDateTime intervalStart = dates[0];
    int totalFee =0;
    int preFeeCharged = 0;
    for (LocalDateTime date : dates) {
      long minutes = intervalStart.until(date, ChronoUnit.MINUTES);
      if(minutes > 60){
        intervalStart = date;
      }
      int nextFee = getTollFeeByDateAndVehicle(date, vehicle);

      if (minutes <= 60) {
        if (totalFee == 0) {
          totalFee = nextFee;
          preFeeCharged = nextFee;
        } else  if (nextFee > preFeeCharged) {
          totalFee += nextFee - preFeeCharged;
          preFeeCharged = nextFee;
        }
      }
      else {
        totalFee += nextFee;
        preFeeCharged = nextFee;
      }
    }
    LOGGER.info(format("Result before applying max value toll fee validation:{totalFee:%d}", totalFee));
    if (totalFee > 60) {totalFee = 60;}
    LOGGER.info(format("Result after applying max value toll fee validation:{totalFee:%d}", totalFee));
    return totalFee;
  }

  /**
   * Calculate toll fee for vehicle on a specific date & time
   *
   * @param vehicle - the vehicle
   * @param date    - date and time
   * @return - the toll fee for that day & time
   */
  private int getTollFeeByDateAndVehicle(final LocalDateTime date, Vehicle vehicle)
  {
    /*Updated condition here to achieve results*/
    int tollFee = 0;
    if (!holidayTollCalculatorService.isDateTollFree(date) && !vehicleSpotterService.isTollFreeVehicle(vehicle))
      tollFee = getTollFeeByHourAndMinute(date.getHour(), date.getMinute());
    return tollFee;
  }

  private int getTollFeeByHourAndMinute(int hour, int minute)
  {
    if (hour == 6 && minute >= 0 && minute <= 29) return 8;
    else if (hour == 6 && minute >= 30 && minute <= 59) return 13;
    else if (hour == 7 && minute >= 0 && minute <= 59) return 18;
    else if (hour == 8 && minute >= 0 && minute <= 29) return 13;
    else if ((hour == 8 && minute >= 30 && minute <= 59) || (hour >= 9 && hour <= 14 && minute >= 0 && minute <= 59)) return 8;
    else if (hour == 15 && minute >= 0 && minute <= 29) return 13;
    else if ((hour == 15 && minute >= 30 && minute <= 59) || (hour == 16 && minute >= 0 && minute <= 59)) return 18;
    else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
    else if (hour == 18 && minute >= 0 && minute <= 29) return 8;
    else return 0;
  }

 }

