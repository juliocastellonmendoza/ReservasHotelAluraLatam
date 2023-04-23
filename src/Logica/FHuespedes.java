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

public class FHuespedes {
	private Conexion mysql;
	private Connection cn;
	private String sSQL = "";
	private String sSQL2 = "";
	Integer totalregistros;
	
	public FHuespedes() {
		
		mysql = new Conexion();
		cn = mysql.conectar();
	}

	public List<VHuespedes> mostrar(String buscar) {
		
		List<VHuespedes> resultado = new ArrayList<>();

		totalregistros=0;
		
		sSQL="SELECT h.idhuespedes,h.nombre,h.apellido,h.fecha_de_nacimiento,h.nacionalidad,h.telefono,h.idreserva "
				+ "FROM huespedes h inner join reservas r on h.idreserva=r.idreserva WHERE h.idreserva like '%"+buscar+"%' "
				+ "order by idhuespedes desc";
		
		Statement st;
		
		try {
			st=cn.createStatement();
			
			ResultSet rs = st.executeQuery(sSQL);
			
			while(rs.next()) {
				
				resultado.add(new VHuespedes(rs.getInt("h.idhuespedes"),rs.getString("h.nombre"),rs.getString("h.apellido"),
						rs.getDate("h.fecha_de_nacimiento"),rs.getString("h.nacionalidad"),rs.getString("h.telefono"),
						rs.getInt("h.idreserva")));
				
				totalregistros+=1;
			}
			
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Error al mostrar registros de huespedes");
			return null;
		}
		return resultado;
	}
	
	public boolean insertar(VHuespedes dts) {
				
		sSQL2="SELECT idreserva FROM reservas order by idreserva desc limit 1";
		try {
			String[] registro = new String[1];
			Statement pst2 = cn.createStatement();
			final ResultSet rs = pst2.executeQuery(sSQL2);
			
			try(rs){
				while(rs.next()) {
					registro[0]=rs.getString("idreserva");			
				}
				
				dts.setIdreserva(Integer.parseInt(registro[0]));
				
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "Error al ingresar registros de reservas");
				e.printStackTrace();
				return false;
			}
		
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Error al ingresar registros de reservas");
			e.printStackTrace();
			return false;
		}
		
		sSQL="INSERT INTO huespedes (nombre,apellido,fecha_de_nacimiento,"
				+ "nacionalidad,telefono,idreserva)"
				+ "VALUES(?,?,?,?,?,?)";
	
		try {
			
			PreparedStatement pst = cn.prepareStatement(sSQL);
			pst.setString(1, dts.getNombre());
			pst.setString(2, dts.getApellido());
			pst.setDate(3, dts.getFecha_de_nacimiento());
			pst.setString(4, dts.getNacionalidad());
			pst.setString(5, dts.getTelefono());
			pst.setInt(6, dts.getIdreserva());
			
			int n=pst.executeUpdate();
			
			if(n!=0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "Error al ingresar registros de huespedes");
			e.printStackTrace();
			return false;
		}
	}
	
	public int editar(String nombre,String apellido,Date fecha_de_nacimiento,String nacionalidad,String telefono,Integer idreserva,Integer idhuespedes) {
		
		sSQL = "UPDATE huespedes SET nombre=?,apellido=?,fecha_de_nacimiento=?,nacionalidad=?,telefono=?,"
				+ "idreserva=? WHERE idhuespedes=?";
		
		try {
		final PreparedStatement pst = cn.prepareStatement(sSQL);
		try (pst){
			
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			pst.setDate(3, fecha_de_nacimiento);
			pst.setString(4, nacionalidad);
			pst.setString(5, telefono);
			pst.setInt(6, idreserva);
			
			pst.setInt(7, idhuespedes);
			
			pst.execute();
			
			int updateCount = pst.getUpdateCount();
			
			return updateCount;
			
		}	
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, e);
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer idhuespedes) {
		
		sSQL="DELETE FROM huespedes WHERE idhuespedes =?";
		
		try {
			final PreparedStatement pst = cn.prepareStatement(sSQL);
			try(pst){
				pst.setInt(1, idhuespedes);
				pst.execute();
			return pst.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
	}
}
