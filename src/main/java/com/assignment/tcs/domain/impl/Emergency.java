package com.assignment.tcs.domain.impl;

import com.assignment.tcs.domain.Vehicle;

public class Emergency implements Vehicle
{
	@Override
	public String getType()
	{
		return "Emergency";
	}
}