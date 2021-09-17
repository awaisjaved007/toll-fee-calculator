package com.assignment.tcs.domain.impl;

import com.assignment.tcs.domain.Vehicle;

public class Foreign implements Vehicle
{
	@Override
	public String getType()
	{
		return "Foreign";
	}
}