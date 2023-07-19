package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	
	private String role;
	private ArrayList<String> securityIncidents;
	
	
	public AdminUser(Integer id, String role) {
		super(id);
		this.role = role;
		this.securityIncidents = new ArrayList<String>();
	}
	
	
	// TO DO: Implement HIPAACompliantAdmin!
	
	
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    
    
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
    
	
	@Override
	public boolean assignPin(int pin) {
		if(pin < 100000 || pin > 999999) {
			return false;
		}
		this.pin = pin;
		return true;
	}
	
	
	
	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if(confirmedAuthID != this.id) {
			this.authIncident();
			return false;
		}
		return true;
	}
	
	
	
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return this.securityIncidents;
	}


    
    // TO DO: Setters & Getters
    
	
	public Integer getEmployeeID() {
		return getEmployeeID();
	}

	public Integer setEmployeeID(Integer setEmployeeID) {
		return setEmployeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}

}
