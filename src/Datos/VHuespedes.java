package Datos;

import java.sql.Date;

public class VHuespedes {
	private int idhuespedes;
	private String nombre;
	private String apellido;
	private Date fecha_de_nacimiento;
	private String nacionalidad;
	private String telefono;
	private int idreserva;
	
	public VHuespedes() {
		super();
	}

	public VHuespedes(int idhuespedes, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,
			String telefono, int idreserva) {
		super();
		this.idhuespedes = idhuespedes;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idreserva = idreserva;
	}

	public int getIdhuespedes() {
		return idhuespedes;
	}

	public void setIdhuespedes(int idhuespedes) {
		this.idhuespedes = idhuespedes;
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

	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdreserva() {
		
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	@Override
	public String toString() {
		return "VHuespedes [nombre=" + nombre + ", apellido=" + apellido + ", fecha_de_nacimiento="
				+ fecha_de_nacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idreserva="
				+ idreserva + "]";
	}
	
	

}
