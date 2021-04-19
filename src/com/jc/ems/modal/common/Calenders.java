package com.jc.ems.modal.common;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.jc.ems.constant.CommonConstant;


public class Calenders {
	
	private Calendar getGregorianCalendar() {
		return new GregorianCalendar();
	}
	
	public Integer getDaysInMonth() {
		return getGregorianCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public String getCurrentMonth() {
		return CommonConstant.months[getGregorianCalendar().get(Calendar.MONTH)];
	}
	
	public LocalDate getLocalDate(String dateValue) {
		return LocalDate.parse(dateValue);
	}
}
