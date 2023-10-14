package imb.pr3.hotel.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Habitacion {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    private Integer piso;
	    private Integer numero;
	    private String puntoCardinal;
	    private Integer capacidad;
	    private Integer camas;
	    private boolean suite;
	    private boolean baño;
	    private boolean balcon;
	    
	    @ManyToOne(cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "cliente_id")
	    private Cliente cliente;
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getPiso() {
			return piso;
		}
		public void setPiso(Integer piso) {
			this.piso = piso;
		}
		public Integer getNumero() {
			return numero;
		}
		public void setNumero(Integer numero) {
			this.numero = numero;
		}
		public String getPuntoCardinal() {
			return puntoCardinal;
		}
		public void setPuntoCardinal(String puntoCardinal) {
			this.puntoCardinal = puntoCardinal;
		}
		public Integer getCapacidad() {
			return capacidad;
		}
		public void setCapacidad(Integer capacidad) {
			this.capacidad = capacidad;
		}
		public Integer getCamas() {
			return camas;
		}
		public void setCamas(Integer camas) {
			this.camas = camas;
		}
		public boolean isSuite() {
			return suite;
		}
		public void setSuite(boolean suite) {
			this.suite = suite;
		}
		public boolean isBaño() {
			return baño;
		}
		public void setBaño(boolean baño) {
			this.baño = baño;
		}
		public boolean isBalcon() {
			return balcon;
		}
		public void setBalcon(boolean balcon) {
			this.balcon = balcon;
		}
	
	
}
