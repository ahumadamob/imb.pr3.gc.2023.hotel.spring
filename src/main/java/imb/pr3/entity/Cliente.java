package imb.pr3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max=50, message = "El nombre no puede tener más de 50 caracteres.")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max=50, message = "El apellido no puede tener más de 50 caracteres.")
    private String apellido;
    private int edad;
    @NotBlank(message = "La dirección no puede estar vacía.")
	@Size(min = 3, max = 100, message = "Mínimo 3, máximo 100 caracteres.")
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
