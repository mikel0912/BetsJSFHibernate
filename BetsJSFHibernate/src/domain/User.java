package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User implements Serializable{
	
	@Id
	private String usrname;
	private String password;
	private Integer bankAccount;

	public User(String usrname, String password, Integer bankAccount) {
		super();
		this.usrname = usrname;
		this.password = password;
		this.bankAccount = bankAccount;
	}
	
	protected User() {
		super();
	}

	public String getUsername() {
		return usrname;
	}

	public void setUsername(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Override
	public String toString() {
		return this.usrname;
	}
}
