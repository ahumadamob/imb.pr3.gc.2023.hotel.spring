package imb.pr3.hotel.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
//import imb.pr3.hotel.entity.Reservacion; TODO: quitar comentario en próximo merge
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Identificador del cliente
    @NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max=50, message = "El nombre no puede tener más de 50 caracteres.")
    private String nombre; // Nombre del cliente
    @NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max=50, message = "El apellido no puede tener más de 50 caracteres.")
    private String apellido; // Apellido del cliente
    private Date fechaNacimiento; // Fecha de nacimiento del cliente
    @NotBlank(message = "La dirección no puede estar vacía.")
	@Size(min = 3, max = 100, message = "Mínimo 3, máximo 100 caracteres.")
    private String domicilio; // Dirección del cliente
    @NotBlank(message = "El número de teléfono no puede estar vacío.")
    @Pattern(regexp = "\\d{13}", message = "El número de teléfono debe contener 13 dígitos numéricos.")
    private String telefono; // Número de teléfono del cliente
    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "Debe proporcionar una dirección de correo electrónico válida.")
    private String correo; // Correo electrónico del cliente
    private boolean habilitado;
    
    public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@OneToMany(mappedBy = "cliente")
    private List<Habitacion> habitaciones;
  
	// Métodos getter y setter para cada campo de la clase
    public String getTelefono() {
		return telefono;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
