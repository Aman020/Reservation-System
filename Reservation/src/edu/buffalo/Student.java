package edu.buffalo;
import java.util.*;


/**
 *The Student class is a worker class that encapsulates student related information.
 * @author 	Meghana, Prabhleen, Aman, Amlan, Deboshree
 * @version	1.0
 * @since	2019-05-03
 */

public class Student {

    private String emailId;
    private Date banStartDate;
    
    public Student(String emailId, Date banStartDate )
    {
        this.emailId = emailId;
        this.banStartDate = banStartDate;
    }


    //Get the email id of the student
	public String getEmailId() {
		return emailId;
	}

	//Set the email id of a student
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	//Get the banned date of the student
	public Date getBanStartDate() {
		return banStartDate;
	}

	//Set the banned date of the student
	public void setBanStartDate(Date banStartDate) {
		this.banStartDate = banStartDate;
	}
    
    




}
