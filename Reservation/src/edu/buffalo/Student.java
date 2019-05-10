package edu.buffalo;
import java.util.*;

public class Student {

    private String emailId;
    private Date banStartDate;
    
    
    public Student(String emailId, Date banStartDate )
    {
        this.emailId = emailId;
        this.banStartDate = banStartDate;
    }


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Date getBanStartDate() {
		return banStartDate;
	}


	public void setBanStartDate(Date banStartDate) {
		this.banStartDate = banStartDate;
	}
    
    




}
