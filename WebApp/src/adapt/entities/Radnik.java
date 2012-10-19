package adapt.entities;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

   /** 
   Class generated using Kroki EJBGenerator 
   @Author mrd 
   Creation date: 10.09.2012  15:59:43h
   **/

@Entity
@Table(name = "RADNIK")
public class Radnik implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private java.lang.Long id;

	@Column(name = "RAD_IME", unique = false, nullable = false)
	private java.lang.String name;
	
	@Column(name = "RAD_PREZIME", unique = false, nullable = false)
	private java.lang.String prezime;
	
	@Column(name = "RAD_JMBG", unique = false, nullable = false)
	private java.lang.String jmbg;
	
	
	@ManyToOne
	@JoinColumn(name="naseljenoMesto", referencedColumnName="ID", nullable=false)
	private NaseljenoMesto naseljenoMesto;
	
	
	public Radnik(){
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getPrezime() {
		return this.prezime;
	}
	
	public void setPrezime(java.lang.String prezime) {
		this.prezime = prezime;
	}
	
	public java.lang.String getJmbg() {
		return this.jmbg;
	}
	
	public void setJmbg(java.lang.String jmbg) {
		this.jmbg = jmbg;
	}
	
	public NaseljenoMesto getNaseljenoMesto() {
		return this.naseljenoMesto;
	}
	
	public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
		this.naseljenoMesto = naseljenoMesto;
	}
	
}