/**
 * 
 */
package com.csc.adaas.eshop.registration.common;

/**
 * Class Name : RegistrationConstants<br>
 * 
 * 
 * @author rharini
 */
public final class RegistrationConstants {

	/** private constructor. */
	private RegistrationConstants() {
	}
	
	/** zero */
	public static final int ZERO = 0;
	
	/** hundred */
	public static final int HUNDRED = 100;
	
	/** five hundred */
	public static final int FIVE_HUNDRED = 500;
	
	/** four hundred */
	public static final int FOUR_HUNDRED = 400;
	
	/** max link page */
	public static final int TWENTY = 20;
	
	/** max link page */
	public static final int TWENTY_FIVE = 25;
	
	/** Short date format. */
	public static final String UI_DATE_FORMAT = "dd/MM/yyyy";

	/** SQLFire date format - derby timestamp format */
	public static final String DB_DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
	
	/** SQLFire date format - derby timestamp format */
	public static final String DB_DATE_FORMAT_WITH_NANO = "yyyy-mm-dd hh:mm:ss.N";
	
	/** Schema Name */
	public static final String COMMON_SCHEMA_NAME = "trustmanager";
	
	/** Tenant table name */
	public static final String TENANT_TABLE_NAME = "tenant";
	
	/** User table name */
	public static final String USER_TABLE_NAME = "user";
		
	/** Database create operation */
	public static final String CREATE = "CREATE";

	/** Database update operation */
	public static final String UPDATE = "UPDATE";

	/** Database delete operation */
	public static final String DELETE = "DELETE";
	
	/** where */
	public static final String WHERE = " WHERE ";
	
	/** dollar */
	public static final String DOLLAR = "$";
	
	/** percentage */
	public static final String PERCENTAGE = "%";
	
	/** DOT */
	public static final String DOT = ".";

	/** colon */
	public static final String COLON = ":";
	
	/** semi colon */
	public static final String SEMI_COLON = ";";
	
	/** Equal to */
	public static final String EQUAL_TO = "=";
	
	/** and */
	public static final String AND = "AND";
	
	/** single space */
	public static final String SPACE = " ";
	
	/** blank no spaces */
	public static final String BLANK = "";
	
	/** comma */
	public static final String COMMA = ",";
	
	/** question mark */
	public static final String QUESTION_MARK = "?";
	
	/** CSC */
	public static final String CSC = "CSC";

	/** Line Separator string constant */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/** Y */
	public static final String Y = "Y";
	
	/** Single Quote */
	public static final String QUOTE = "'";
	
	/** ascending */
	public static final String ASC = "ASC";
	
	/** descending */
	public static final String DSC = "DSC";
	
		
	/** select * from */
	public static final String SELECT_STAR_FROM = "SELECT * FROM ";
	
	/** like */
	public static final String LIKE = " LIKE ";

	public static final String TENANT_ID = "tenant_id";
	
	public static final String DOMAIN_NAME = "domain_name";
	
	public static final String COMPANY_NAME = "company_name";
	
	public static final String COMPANY_SHORT_NAME = "company_short_name";
	
	public static final String SCHEMA_KEY = "schema_key";
	
	public static final String TENANT_STATUS = "tenant_status";
	
	public static final String USER_ID = "user_id";
	
	public static final String PASSWORD = "password";
	
	public static final String LANGUAGE = "language";
	
	public static final String DISPLAY_NAME = "display_name";
	
	public static final String LAST_ACCESSED = "last_accessed";
	
	public static final String USER_STATUS = "user_status";
	
	public static final String RETURN_RESULTS = "results";
}
