package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name="ID_USER")
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id_user) {
		this.id = id_user;
	}

	@Column(name="USERNAME")
	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="PASSWORD")
	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="ENABLED")
	int enabled;

	public boolean isEnabled() {
		return ( (enabled>0) ? true : false);
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authority> authorities = new HashSet<>();

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
