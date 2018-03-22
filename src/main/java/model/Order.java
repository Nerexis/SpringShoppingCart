package model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDER")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_ORDER",unique=true, nullable = false)
	private int id;

	@Column(name="DATE")
	private Date date;

	@Column(name="NAME")
	private String name;

	@Column(name="SURNAME")
	private String surname;

	@Column(name="ADDRESS")
	private String address;

	@Column(name="PHONE")
	private String phone;
}
