package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	private final StringProperty nombre;
	private final StringProperty idUsuario;
	private final StringProperty apellidoPaterno;
	private final StringProperty puesto;
	
	public Usuario(String nombre, String idUsuario, String apellidoPaterno, String puesto) {
		this.nombre= new SimpleStringProperty(nombre);
		this.idUsuario= new SimpleStringProperty(idUsuario);
		this.apellidoPaterno= new SimpleStringProperty(apellidoPaterno);
		this.puesto= new SimpleStringProperty(puesto);
	}
	
	public StringProperty getNombre() {
		return nombre;
	}
	
	public String getNombreProperty() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}
	
	public StringProperty getIdUsuario() {
		return idUsuario;
	}
	
	public String getIdUsuarioProperty() {
		return idUsuario.get();
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario.set(idUsuario);
	}
	
	public StringProperty getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	public String getApellidoPaternoProperty() {
		return apellidoPaterno.get();
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno.set(apellidoPaterno);
	}
	
	public StringProperty getPuesto() {
		return puesto;
	}
	
	public String getPuestoProperty() {
		return puesto.get();
	}
	
	public void setIdProducto(String puesto) {
		this.puesto.set(puesto);;
	}

}
