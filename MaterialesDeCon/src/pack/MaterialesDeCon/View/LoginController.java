package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;

public class LoginController {
	Main main;
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	@FXML
	private JFXButton access;
	@FXML
	private JFXTextField user;
	@FXML
	private JFXPasswordField password;
	String idUsuario = "", name = "", puesto = "", apellido = "";
	
	@FXML
	void cagarMenu(ActionEvent event) throws SQLException{
		if(!RevisarCampos()) {
			if(VerificarUser()) {
				main.loadMenu(idUsuario, name, apellido, puesto);
			}else {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("El usuario o contraseņa no son validas");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
			}
		}
	}
	
	private boolean RevisarCampos() {
		boolean CampoVacio = false;
		
		if(user.getText().isEmpty() || password.getText().isEmpty()) {
			CampoVacio = true;
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Debes llenar todos los campos");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
		return CampoVacio;
	}
	
	private boolean VerificarUser() throws SQLException {
		boolean verificar = false;
		System.out.println(user.getText());
		try{
			String passwordIngresado = password.getText();
			con = Conexion.getConection();
			ps = con.prepareStatement("SELECT * FROM dbo.Usuario WHERE nombre ='" + user.getText()+"'");
			res = ps.executeQuery();
			while (res.next()) {
				String password = res.getString("password");
				if(passwordIngresado.equals(password)) {
					idUsuario = res.getString("idUsuario");
					name = res.getString("nombre");
					apellido = res.getString("apellidoPaterno");
					puesto = res.getString("puesto");
					verificar = true;
				}
				
			}
		}catch (Exception e) {
			System.out.println("EL usuario no existe");
		}
		
		return verificar;
	}
	
	public void setMain(Main main) {
		this.main= main;
	}

}
