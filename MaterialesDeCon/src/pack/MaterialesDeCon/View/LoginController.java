package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private Button access;
	@FXML
	private JFXTextField user;
	@FXML
	private JFXPasswordField password;
	
	@FXML
	void cagarMenu(ActionEvent event) throws SQLException{
		if(VerificarUser()) {
			main.loadMenu();
		}else {
			   Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("Contraseña, usuario o cantidad igresada no valida");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
		   }
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
				//System.out.println(res.next());
				String password = res.getString("password");
				if(passwordIngresado.equals(password)) {
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
