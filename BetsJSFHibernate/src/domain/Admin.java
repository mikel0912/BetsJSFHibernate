package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Admin extends User implements Serializable{

	public Admin(String username, String password, Integer bankAccount) {
		super(username, password, bankAccount);
	}
	public Admin() {
		super();
	}

}
