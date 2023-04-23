package Datos;

import java.sql.Date;

public class VReservas {
	private int idreserva;
	private Date fecha_entrada;
	private Date fecha_salida;
	private double valor;
	private String forma_pago;
	private String m;
	
	public VReservas() {
		super();
	}

	public VReservas(int idreserva, Date fecha_entrada, Date fecha_salida, double valor, String forma_pago) {
		super();
		this.idreserva = idreserva;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	@Override
	public String toString() {
		return "VReservas [fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", valor=" + valor
				+ ", forma_pago=" + forma_pago + "]";
	}
	
	
	
}
