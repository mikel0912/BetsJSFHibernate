package KlaseBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Registered;
import domain.User;
import facade.bean.FacadeBean;

public class LoginRegisterBean {
	private String izena;
	private String pasahitza;
	private Integer bankAccount;
	private String userMota;
	private boolean disableCreate;
	private User u;
	private static BLFacade facadeBL;
	public LoginRegisterBean(){
		facadeBL =FacadeBean.getBusinessLogic();
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getPasahitza() {
		return pasahitza;
	}
	public void setPasahitza(String pasahitza) {
		this. pasahitza = pasahitza;
	}
	
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public static BLFacade getFacadeBL() {
		return facadeBL;
	}
	public static void setFacadeBL(BLFacade facadeBL) {
		LoginRegisterBean.facadeBL = facadeBL;
	}
	
	public Integer getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public String egiaztatu() {
		if(izena=="") {
			izena="";
			pasahitza="";
			bankAccount=null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Erabiltzaile izena beharrezkoa da"));
			return null;
		}
		if(pasahitza=="") {
			izena="";
			pasahitza="";
			bankAccount=null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Pasahitza beharrezkoa da"));
			return null;
		}
		u= facadeBL.login(izena, pasahitza);
		izena=null;
		pasahitza=null;
		bankAccount=null;
		if(u instanceof Admin) {
			this.userMota="Admin";
		}else if (u instanceof Registered) {
			this.userMota="Registered";
		}
		this.disableCreate();
		if(u!=null) {
			return "true";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: erabiltzaile izena eta pasahitza ez datoz bat."));
			return null;
		}
	}
	
	public String register() {
		if(izena=="") {
			izena="";
			pasahitza="";
			bankAccount=null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Erabiltzaile izena beharrezkoa da"));
			return null;
		}
		if(pasahitza=="") {
			izena="";
			pasahitza="";
			bankAccount=null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Pasahitza beharrezkoa da"));
			return null;
		}
		if(bankAccount==null) {
			izena="";
			pasahitza="";
			bankAccount=null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: Banku kontua beharrezkoa da"));
			return null;
		}
		u = facadeBL.erregistratu(izena, pasahitza, bankAccount);
		izena=null;
		pasahitza=null;
		bankAccount=null;
		if(u!=null) {
			return "true";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Errorea: erabiltzailea dagoeneko existitzen da"));
			return null;
		}
	}
	
	public String getUserMota() {
		return this.userMota;
	}
	public void setUserMota(String userMota) {
		if(u instanceof Admin) {
			this.userMota="Admin";
		}else if (u instanceof Registered) {
			this.userMota="Registered";
		}
	}
	
	public void disableCreate() {
		if(this.userMota=="Admin") {
			this.disableCreate= true;
		}else {
			this.disableCreate= false;
		}
	}
	public boolean isDisableCreate() {
		return disableCreate;
	}
	public void setDisableCreate(boolean disableCreate) {
		this.disableCreate = disableCreate;
	}
	
	public String logOut() {
		u=null;
		return "logOut";
	}
	
	
}