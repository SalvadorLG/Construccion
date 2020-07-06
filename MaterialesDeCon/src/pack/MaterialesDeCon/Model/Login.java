package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {
	
	private final StringProperty usuario;
	private final StringProperty password;
	
	public Login(String usuario, String password) {
		this.usuario= new SimpleStringProperty(usuario);
		this.password= new SimpleStringProperty(password);
	}

	public StringProperty getUsuario() {
		return usuario;
	}
	
	public String getUsuarioProperty() {
		return usuario.get();
	}
	
	public void setUsuario(String usuario) {
		this.usuario.set(usuario);
	}
	
	public StringProperty getPassaword() {
		return password;
	}
	
	public String getPasswordProperty() {
		return password.get();
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
}
