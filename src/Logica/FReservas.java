package Logica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Conexion.Conexion;
import Datos.VHuespedes;
import Datos.VReservas;

public class FReservas {

	private Conexion mysql;
	private Connection cn;
	private String sSQL = "";
	Integer totalregistros;
	
	
	
	public FReservas() {
		mysql = new Conexion();
		cn = mysql.conectar();
	}

	public List<VReservas> mostrar(String buscar) {
		List<VReservas> resultado = new ArrayList<>();;
		
		totalregistros=0;
		
		sSQL="SELECT * FROM reservas WHERE idreserva like '%"+buscar+"%' order by idreserva desc";
	
		Statement st;
		
		try {
			st=cn.createStatement();
			
			ResultSet rs = st.executeQuery(sSQL);
			
			while(rs.next()) {
				
				resultado.add(new VReservas(rs.getInt("idreserva"),rs.getDate("fecha_entrada"),rs.getDate("fecha_salida"),
						rs.getDouble("valor"),rs.getString("forma_pago")));
				
				totalregistros+=1;

			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Error al mostrar registros de reservas");
			return null;
		}
		return resultado;
	}
	
	public boolean insertar(VReservas dts) {
		
		sSQL="INSERT INTO reservas (fecha_entrada,fecha_salida,valor,"
				+ "forma_pago)VALUES(?,?,?,?)";
		
		try {
			PreparedStatement pst = cn.prepareStatement(sSQL);
			
			pst.setDate(1, dts.getFecha_entrada());
			pst.setDate(2, dts.getFecha_salida());
			pst.setDouble(3, dts.getValor());
			pst.setString(4, dts.getForma_pago());	
				
			int n=pst.executeUpdate();
				
			if(n!=0) {
				
				return true;
			}else {
				return false;
			}	
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Error al ingresar registros de reservas");
			e.printStackTrace();
			return false;
		}
	}
	
	public int editar(Date fecha_entrada, Date fecha_salida, double valor, String forma_pago, Integer idreserva) {
	
		sSQL = "UPDATE reservas SET fecha_entrada=?,fecha_salida=?,valor=?,"
				+ "forma_pago=? WHERE idreserva=?";
		try {
		final PreparedStatement pst = cn.prepareStatement(sSQL);
		try (pst){
			
			pst.setDate(1, fecha_entrada);
			pst.setDate(2, fecha_salida);
			pst.setDouble(3, valor);
			pst.setString(4, forma_pago);
			
			pst.setInt(5, idreserva);
			
			pst.execute();
			
			int updateCount = pst.getUpdateCount();
			
			return updateCount;
			
		}	
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, e);
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer idreserva) {
		
		sSQL="DELETE FROM reservas WHERE idreserva =?";
		
		try {
			final PreparedStatement pst = cn.prepareStatement(sSQL);
			try(pst){
				pst.setInt(1, idreserva);
				pst.execute();
			return pst.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
	}
}
