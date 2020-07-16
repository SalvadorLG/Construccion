package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	private final StringProperty idUsuario;
	private final StringProperty nombre;
	private final StringProperty apellidoPaterno;
	private final StringProperty puesto;
	private StringProperty password;
	
	public Usuario(String idUsuario, String nombre, String apellidoPaterno, String puesto, String password) {
		this.idUsuario= new SimpleStringProperty(idUsuario);
		this.nombre= new SimpleStringProperty(nombre);
		this.apellidoPaterno= new SimpleStringProperty(apellidoPaterno);
		this.puesto= new SimpleStringProperty(puesto);
		this.password = new SimpleStringProperty(password);
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
		this.puesto.set(puesto);
	}
	
	public StringProperty getPassword() {
		return password;
	}

	public String getPasswordProperty() {
		return password.get();
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

}
