package imb.pr3.hotel.entity;

public class Proveedor {

	
	private Integer id;    
    private String  razonsocial;
    private Integer cuit;
    private String direccion;
    private Integer tipodeproveedor;
    private String estado;
    private Integer telefono;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	public Integer getCuit() {
		return cuit;
	}
	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getTipodeproveedor() {
		return tipodeproveedor;
	}
	public void setTipodeproveedor(Integer tipodeproveedor) {
		this.tipodeproveedor = tipodeproveedor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
    
    
  
}
