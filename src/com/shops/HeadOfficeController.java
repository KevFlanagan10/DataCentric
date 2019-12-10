package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;


@ManagedBean
@SessionScoped
public class HeadOfficeController {
	
	MongoDAO mgDao;
	ArrayList<HeadOffice> headOffice;
	
	public HeadOfficeController() {
		super();
		try {
			mgDao = new MongoDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadHeadOffice() {
		System.out.println("In loadHeadOffice()");
		try {
			headOffice = mgDao.loadHeadOffice();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addHeadOffice(HeadOffice headOffice) {
		try {
			mgDao.addHeadOffice(headOffice);
			return "manageHeadOffice";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: Cant communicate with the Database");
					FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			FacesMessage message = 
					new FacesMessage("Error: " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}
	//delete button
//	public void delete(int pid) {
//		System.out.println(pid);
//		try {
//			mgDao.deleteStore(pid);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public ArrayList<HeadOffice> getHeadOffice() {
		return headOffice;
	}
	
}

