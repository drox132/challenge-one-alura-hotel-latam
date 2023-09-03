package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conectionFactory.ConectionFactory;
import model.Huesped;

public class HuespedDao {
	
	final private Connection con;
	
	public HuespedDao (Connection con) {
		this.con=con;
	}

	public Integer save(Huesped huesped) {
		
		Connection con = new ConectionFactory().establecerConexion();

		Date nacimiento = huesped.getFechaNacimiento();
		 String nacimientoformat = formatFecha(nacimiento);
		 
		String sql = "INSERT INTO HUESPEDES (NOMBRE,APELLIDO,FECHA_NACIMIENTO,TELEFONO,ID_RESERVA,NACIONALIDAD)"
				+ "VALUES (?,?,?,?,?,?)";
			try(con){
				
				final PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				 try(ps){
					ps.setString(1, huesped.getNombre());
					ps.setString(2, huesped.getApellido());
					ps.setString(3, nacimientoformat);
					ps.setString(4, huesped.getTelefono());
					ps.setInt(5,huesped.getIdReserva());
					ps.setString(6, huesped.getNacionalidad());
					
					ps.execute();
					ResultSet rs = ps.getGeneratedKeys();
					Integer idGenerado=0 ;
					if(rs.next()) {
						idGenerado = rs.getInt(1);
					}
					System.out.println("Se genero huesped con id: "+ idGenerado);
					return idGenerado;
				 }
				 
			 }catch (Exception e) {
				 throw new RuntimeException(e);
			 }
	}
	
	
	//conversor de fechas a formato decente
		
	    private  String formatFecha(Date fecha) {
			 String nuevoFormato = "yyyy-MM-dd";
	        SimpleDateFormat formatoFecha = new SimpleDateFormat(nuevoFormato);
	        return formatoFecha.format(fecha);
	    }

		public List<Huesped> listar() {
			Connection con = new ConectionFactory().establecerConexion();
			
			List<Huesped> listaHuespedes = new ArrayList<>();
			Huesped huesped ;
			String sql = "SELECT * FROM HUESPEDES";
			try {
				try(con){
					PreparedStatement ps = con.prepareStatement(sql);
					try(ps){
						ResultSet rs = ps.executeQuery();
						try(rs){
							while(rs.next()) {
								huesped= new Huesped();
								huesped.setId(rs.getInt("ID"));
								huesped.setNombre(rs.getString("NOMBRE"));
								huesped.setApellido(rs.getString("APELLIDO"));
								huesped.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
								huesped.setTelefono(rs.getString("TELEFONO"));
								huesped.setIdReserva(rs.getInt("ID_RESERVA"));
								huesped.setNacionalidad(rs.getString("NACIONALIDAD"));
								
								listaHuespedes.add(huesped);
								
							}
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			
			return listaHuespedes;
		}

		public String eliminar(Integer idHuespedEliminar) {
			Connection con = new ConectionFactory().establecerConexion();
			String sql = "DELETE FROM HUESPEDES WHERE ID = ? ";
			String mensaje="";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				try(ps){
					ps.setInt(1, idHuespedEliminar);
					int rowAffected = ps.executeUpdate();
					if(rowAffected!=0) {
						mensaje ="El Hueped ha sido eliminado correctamente";
					}
				}
				
			} catch (Exception e) {
				mensaje="No se pudo eliminar el Huesped";
				throw new RuntimeException(e);
			}
			return mensaje;
		}

		public void modificar(Huesped huesped) {
			System.out.println(huesped);
			Connection con = new ConectionFactory().establecerConexion();
			String sql ="UPDATE HUESPEDES SET NOMBRE = ? , APELLIDO = ? , FECHA_NACIMIENTO = ? , TELEFONO = ? ,ID_RESERVA = ? ,NACIONALIDAD = ? WHERE ID = ? ";
			String  fechaNacimientoFormateada = this.formatFecha(huesped.getFechaNacimiento());
			try {
				try(con){
					PreparedStatement ps = con.prepareStatement(sql);
					try(ps){
						ps.setString(1, huesped.getNombre());
						ps.setString(2, huesped.getApellido());
						ps.setString(3, fechaNacimientoFormateada);
						ps.setString(4, huesped.getTelefono());
						ps.setInt(5, huesped.getIdReserva());
						ps.setString(6, huesped.getNacionalidad());
						ps.setInt(7, huesped.getId());
						
						int rowAffected = ps.executeUpdate();
						if(rowAffected!=0) {
							System.out.println("Los datos del huesped han sido modificados correctamente");
						}
					}
					
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

}
