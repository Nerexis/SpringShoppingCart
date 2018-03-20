package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AUTHORITY")
public class Authority {
	public Authority(String authority) {
		this.setAuthority(authority);
	}
	@Id
	@Column(name="ID_AUTHORITY")
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="ROLE")
	String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}




}
