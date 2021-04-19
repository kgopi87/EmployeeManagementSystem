package com.jc.ems.modal.common;

public class Calculator {
	public String getWages(String workingDays, String unpaidLeaves, String perDaySalary) {
		int workDay = Integer.valueOf(workingDays);
		int unpaid = Integer.valueOf(unpaidLeaves);	
		int days = (workDay > unpaid) ? (workDay - unpaid) : (unpaid - workDay);
		
		float salary = Float.valueOf(perDaySalary) * Float.valueOf(days);
		return String.valueOf(salary);
	}
	
	public String getNoOfWorkingDays(String workingDays, String unpaidLeaves) {
		int workDay = Integer.valueOf(workingDays);
		int unpaid = Integer.valueOf(unpaidLeaves);
		
		int wdays = (workDay - unpaid);
		return String.valueOf(wdays);
	}
}
