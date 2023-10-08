package imb.pr3.hotel.entity;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Reservacion {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    private Integer habitacionId;
	    private String puntoCardinal;
	    private Date fechaEntrada;
	    private Date fechaSalida;
	    private Double precioFinal;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getHabitacionId() {
			return habitacionId;
		}
		public void setHabitacionId(Integer habitacionId) {
			this.habitacionId = habitacionId;
		}
		public String getPuntoCardinal() {
			return puntoCardinal;
		}
		public void setPuntoCardinal(String puntoCardinal) {
			this.puntoCardinal = puntoCardinal;
		}
		public Date getFechaEntrada() {
			return fechaEntrada;
		}
		public void setFechaEntrada(Date fechaEntrada) {
			this.fechaEntrada = fechaEntrada;
		}
		public Date getFechaSalida() {
			return fechaSalida;
		}
		public void setFechaSalida(Date fechaSalida) {
			this.fechaSalida = fechaSalida;
		}
		public Double getPrecioFinal() {
			return precioFinal;
		}
		public void setPrecioFinal(Double precioFinal) {
			this.precioFinal = precioFinal;
		}
	    
}
 