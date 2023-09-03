package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import conectionFactory.ConectionFactory;
import model.Reserva;

public class ReservaDao {
	
	  final private Connection con;
	  
	  public ReservaDao(Connection con) {
		  this.con=con;
		  }
	 

	public Integer save(Reserva reserva) {
		Connection conn = new ConectionFactory().establecerConexion();
	

		Date desde = reserva.getFechaEntrada();
		Date hasta =reserva.getFechaSalida();
		 String desdeFormateada = formatFecha(desde);
		 String hastaFormateada = formatFecha(hasta);
		 System.out.println(desdeFormateada+hastaFormateada);

		String sql = "INSERT INTO RESERVAS (FECHA_ENTRADA, FECHA_SALIDA , VALOR , FORMA_PAGO)"
				+ "VALUES (?,?,?,?)";
		
		try(conn){
			PreparedStatement ps =  conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
				ps.setString(1, desdeFormateada);
				ps.setString(2, hastaFormateada);
				ps.setFloat(3, reserva.getValor());
				ps.setString(4, reserva.getFormaPago());
				
				ps.execute();
				ResultSet rs = ps.getGeneratedKeys();
				Integer idGenerado =0;
				if(rs.next()) {
					idGenerado = rs.getInt(1);
					
				}
				System.out.println(idGenerado);
				return idGenerado;

				
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
//conversor de fechas a formato decente
	
    private  String formatFecha(Date fecha) {
		 String nuevoFormato = "yyyy-MM-dd";

        SimpleDateFormat formatoFecha = new SimpleDateFormat(nuevoFormato);
        return formatoFecha.format(fecha);
    }

   

	public List<Reserva> listar() {
		 Connection conn = new ConectionFactory().establecerConexion();
		String sql ="SELECT * FROM RESERVAS";
		List<Reserva> listaReservas = new ArrayList<>();
		try {
			try(conn){
				PreparedStatement ps = conn.prepareStatement(sql);
				try(ps){
					ResultSet rs = ps.executeQuery();
					try(rs){
						while(rs.next()) {
							Reserva reserva= new Reserva();
							reserva.setId(rs.getInt("ID"));
							reserva.setFechaEntrada(rs.getDate("FECHA_ENTRADA"));
							reserva.setFechaSalida(rs.getDate("FECHA_SALIDA"));
							reserva.setValor(rs.getFloat("VALOR"));
							reserva.setFormaPago(rs.getString("FORMA_PAGO"));
							
							listaReservas.add(reserva);
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			throw new RuntimeException(e);
		}
		System.out.println(listaReservas);
		return listaReservas;
	}


	public void modificar(Reserva reserva) {
		 Connection conn = new ConectionFactory().establecerConexion();
		 String sql= "UPDATE RESERVAS SET FECHA_ENTRADA = ?, FECHA_SALIDA = ?, VALOR = ?, FORMA_PAGO = ? WHERE ID = ? ";
		 String fechaFormateadaEntrada = this.formatFecha(reserva.getFechaEntrada());
		 String fechaDormateadaSalida = this.formatFecha(reserva.getFechaSalida());
		 try {
			 try(conn){
				 PreparedStatement ps = conn.prepareStatement(sql);
				 try(ps){
					 ps.setString(1,fechaFormateadaEntrada );
					 ps.setString(2, fechaDormateadaSalida);
					 ps.setFloat(3, reserva.getValor());
					 ps.setString(4, reserva.getFormaPago());
					 ps.setInt(5, reserva.getId());
					 
					 int rowAffected = ps.executeUpdate();
					 if(rowAffected!=0) {
						 System.out.println("registro actualizado con exito");
					 }
				 }
			 }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}


	public String eliminar(Integer idReservaEliminar) {
		 Connection conn = new ConectionFactory().establecerConexion();
		 String sql = "DELETE FROM RESERVAS WHERE ID = ? ";
		 try {
			try(conn){
				PreparedStatement ps = conn.prepareStatement(sql);
				try(ps){
					ps.setInt(1, idReservaEliminar);
					int rowAffected = ps.executeUpdate();
					if(rowAffected!=0) {
					}
				}
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			return "No se pudo eliminar la Reserva debido a integridad de datos ese id esta relacionado con otra tabla";
		}catch (Exception e) {
			throw new RuntimeException(e);	
		}
		 return "Se elimino la reserva con exito";
	}
	

}
