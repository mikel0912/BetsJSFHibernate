package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Registered extends User implements Serializable{

	public Registered(String username, String password, Integer bankAccount) {
		super(username, password, bankAccount);
	}
	public Registered() {
		super();
	}
}
