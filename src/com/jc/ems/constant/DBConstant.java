package com.jc.ems.constant;

/**
 * @author janani.c
 * @since 17-April-2021
 * @category Modal
 * @version 1.0
 * @apiNote All database constant information are stored
 *
 */
public class DBConstant {

	/**
	 * @implNote Database connection string class. DONOT MODIFIY THIS!!!!
	 */
	public static final String dbClassName = "net.ucanaccess.jdbc.UcanaccessDriver";
	public static final String connectionString = "jdbc:ucanaccess://libs/Database.mdb";

	
	/**
	 * @implNote All select query for access database
	 */
	public static final String loginQuery = "SELECT DISABLED FROM LOGIN WHERE USERNAME=? AND PASSWORD=?";
	public static final String employeeList = "SELECT EMPID,GIVENNAME,SURNAME,GENDER,TITLE,DOB, DISABLED FROM DEMOGRAPHICS";
	public static final String employeeId = "SELECT COUNT(*) AS EMPID_COUNT FROM DEMOGRAPHICS WHERE DEMOGRAPHICS.EMPID=?";
	public static final String configuration = "SELECT APPKEY,APPVALUE FROM CONFIG WHERE ENABLED=?";
	public static final String demographics = "SELECT EMPID,DISABLED,GIVENNAME,SURNAME,TITLE,DOJ,ADDRESS,DOB,GENDER,PHONE,EMAIL,PROOF,PROOFVAL,MARITAL,"
			+ "CHILDERN,DEPARTMENT FROM DEMOGRAPHICS WHERE EMPID=?";
	public static final String education = "SELECT EMPID,EDUCATION1,QUALIFICATION1,FROM1,TO1,EDUCATION2,QUALIFICATION2,FROM2,TO2,EDUCATION3,QUALIFICATION3, "
			+ "FROM3,TO3 FROM EDUCATION WHERE EMPID=?";
	public static final String leaves = "SELECT MONTHS,UNPAID,LEAVEDATE FROM LEAVES WHERE EMPID=? AND MONTHS=?";
	public static final String memos = "SELECT EMPID,MEMO1,MEMO2,MEMO3 FROM MEMOS WHERE EMPID=?";
	public static final String wages = "SELECT EMPID,MONTHS,WORKINGDAYS,LOP,PAIDLEAVES,WAGE,ADDEDBY FROM WAGES WHERE EMPID=?";
	public static final String paidLeave = "SELECT COUNT(LEAVEDATE) FROM LEAVES WHERE EMPID=? AND MONTHS=? AND UNPAID=?";

	/**
	 * @implNote All insert query for access database
	 */
	public static final String insertDemographics = "INSERT INTO DEMOGRAPHICS "
			+ "(EMPID,DISABLED,GIVENNAME,SURNAME,TITLE,DOJ,ADDRESS,DOB,GENDER,PHONE,EMAIL,PROOF,PROOFVAL,MARITAL,CHILDERN,DEPARTMENT) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String insertEducation = "INSERT INTO EDUCATION "
			+ "(EMPID,EDUCATION1,QUALIFICATION1,FROM1,TO1,EDUCATION2,QUALIFICATION2,FROM2,TO2,EDUCATION3,QUALIFICATION3,FROM3,TO3) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String insertLeaves = "INSERT INTO LEAVES (EMPID,UNPAID,LEAVEDATE,MONTHS) VALUES (?,?,?,?)";
	public static final String insertMemos = "INSERT INTO MEMOS (EMPID,MEMO1,MEMO2,MEMO3) VALUES (?,?,?,?)";
	public static final String insertWages = "INSERT INTO WAGES "
			+ "(EMPID,MONTHS,WORKINGDAYS,LOP,PAIDLEAVES,WAGE,ADDEDBY) VALUES (?,?,?,?,?,?,?)";

	/**
	 * @implNote All update query for access database
	 */
	public static final String updateDemographics = "UPDATE DEMOGRAPHICS "
			+ "SET DISABLED=?,GIVENNAME=?,SURNAME=?,TITLE=?,DOJ=?,ADDRESS=?,DOB=?,GENDER=?,PHONE=?,EMAIL=?,PROOF=?,PROOFVAL=?,MARITAL=?,CHILDERN=?,DEPARTMENT=? "
			+ "WHERE EMPID=?";
	public static final String updateMemos = "UPDATE MEMOS SET MEMO1=?,MEMO2=?,MEMO3=? WHERE EMPID=?";
	public static final String updateEducation = "UPDATE EDUCATION "
			+ "SET EDUCATION1=?,QUALIFICATION1=?,FROM1=?,TO1=?,EDUCATION2=?,QUALIFICATION2=?,FROM2=?,TO2=?,EDUCATION3=?,QUALIFICATION3=?,FROM3=?,TO3=?,"
			+ "WHERE EMPID=?";

	/**
	 * @implNote All report query for access database
	 */
	public static final String currentMonthPayrollReport = "SELECT WAGES.EMPID,GIVENNAME,SURNAME,DISABLED,WORKINGDAYS,LOP,WAGE AS SALARY,ADDEDBY FROM WAGES, DEMOGRAPHICS WHERE MONTHS=? AND DEMOGRAPHICS.EMPID = WAGES.EMPID";
	public static final String currentMonthleave = "SELECT MONTHS, LEAVES.EMPID,GIVENNAME,SURNAME,DOJ,COUNT(LEAVES.UNPAID) AS LEAVE_COUNT FROM LEAVES, DEMOGRAPHICS WHERE MONTHS=? AND UNPAID=? AND DEMOGRAPHICS.EMPID = LEAVES.EMPID GROUP BY LEAVES.EMPID,MONTHS,GIVENNAME,SURNAME,DOJ";
	public static final String activeEmployee = "SELECT EMPID,GIVENNAME,SURNAME,TITLE,DOJ,DOB,GENDER FROM  DEMOGRAPHICS WHERE DISABLED=?";
}
