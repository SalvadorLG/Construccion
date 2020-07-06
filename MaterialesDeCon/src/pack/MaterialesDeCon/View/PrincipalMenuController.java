package pack.MaterialesDeCon.View;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pack.MaterialesDeCon.Model.Conexion;

public class PrincipalMenuController implements Initializable{
	Main main;
	@FXML
    private JFXButton productos;

    @FXML
    private JFXButton usuario;

    @FXML
    private JFXButton regisUser;

    @FXML
    private JFXButton infoUser;

    @FXML
    private JFXButton cemento;

    @FXML
    private JFXButton madera;

    @FXML
    private JFXButton acero;

    @FXML
    private JFXButton azulejo;

    @FXML
    private JFXButton pintura;

    @FXML
    private JFXButton lamina;
    @FXML
    private JFXButton registroProducto;
    @FXML
    private JFXButton compra;
    @FXML
    private JFXButton caja;
    @FXML
    private JFXButton seeSuppiler;
    @FXML
    private JFXButton regSuppiler;
    @FXML
    private JFXButton inventario;
    @FXML
    private AnchorPane userPane, provedoresPane, ventaPane, materialesPane, showUserInfoPane,addUserPane, addProveedorPane, editProveedorPane;;
    @FXML
    private JFXTextField campoNombre;

    @FXML
    private JFXTextField campoApellido;

    @FXML
    private JFXTextField campoPuesto;

    @FXML
    private JFXPasswordField campoPassword;

    @FXML
    private JFXPasswordField campoPassword2;
 
    //static float cantidadInicial= Float.parseFloat(JOptionPane.showInputDialog(null, "Inserte el ingreso del dia: "));
	static float cantidadInicial = 0;
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	
	 @FXML
	void registrarUsuario(ActionEvent event) throws SQLException {
		con = Conexion.getConection();
		String consulta = "INSERT INTO MaterialesDeCon.dbo.Usuario VALUES ('"+campoNombre.getText()+"','"+campoApellido.getText()+
							"','"+campoPuesto.getText()+"','"+campoPuesto.getText()+"')";
		ps = con.prepareStatement(consulta);
    	ps.executeQuery();
    	System.out.println(ps);
    	Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("CONFIRMACI�N");
		alerta.setContentText("El usuario se ha registrado");
		alerta.initStyle(StageStyle.UTILITY);
		alerta.setHeaderText(null);
		alerta.showAndWait();
	}
	 
	 @FXML
	void registrarProveedor(ActionEvent event) throws SQLException {
		 
	 }
	
	public void cagarMenuUsuario(ActionEvent event) {
		verificarBotones();
		userPane.setVisible(true);
	}
	
	public void cagarProductos(ActionEvent event) {
		verificarBotones();
		registroProducto.setVisible(true);
		materialesPane.setVisible(true);
	}
	
	public void cagarCaja(ActionEvent event) {
		verificarBotones();
		ventaPane.setVisible(true);
	}
	
	public void cagarProveedores(ActionEvent event) {
		verificarBotones();
		provedoresPane.setVisible(true);
	}
	@FXML
	public void cargarRegistroUsuario() {
		verificarBotones();
		addUserPane.setVisible(true);
	}
	
	@FXML
	void cagarRegisProduc(ActionEvent event) {
		main.cagarRegistroProducto();
	}
	
	
	@FXML
	void cagarListaMateriales(ActionEvent event) {
		
		if(event.getSource() == cemento) {
			System.out.println(event.getSource());
			main.cargarListaMateriales("CMT01DECON");
		} else if(event.getSource() == lamina) {
			main.cargarListaMateriales("LAM04DECON");
		} else if(event.getSource() == madera) {
			main.cargarListaMateriales("MAD03DECON");
		} else if(event.getSource() == pintura) {
			main.cargarListaMateriales("PIN02DECON");
		} else if(event.getSource() == acero) {
			main.cargarListaMateriales("ACE05DECON");
		} else if(event.getSource() == azulejo) {
			main.cargarListaMateriales("AZU06DECON");
		} else if(event.getSource() == inventario) {
			System.out.println(event.getSource());
			main.cargarListaMateriales("inventario");
		}
	}
	
	@FXML
	void cargarVenta(ActionEvent event) {
		Stage s = (Stage)compra.getScene().getWindow();
    	s.close();
		main.cargarVenta();
	}
	
	@FXML
	public void cargarRegistroProveedor() {
		verificarBotones();
		addProveedorPane.setVisible(true);
	}
	
	@FXML
	void cargarListaProveedores() {
		main.cargarListaProveedores();
	}
	
	@FXML
	void cargarCaja(ActionEvent event) {
		main.cagarCorteCaja();
	}
	
	
	public void verificarBotones() {
		addUserPane.setVisible(false);
		addProveedorPane.setVisible(false);
		provedoresPane.setVisible(false);
		ventaPane.setVisible(false);
		materialesPane.setVisible(false);
		userPane.setVisible(false);
		showUserInfoPane.setVisible(false);
		
	}
	
	public Float ingresoInicial() {
		return cantidadInicial;
	}

	public void setMain(Main main) {
		this.main= main;
	}
	
	@FXML
	public void showUserInfo() {
		verificarBotones();
		showUserInfoPane.setVisible(true);
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TextInputDialog dialog = new TextInputDialog("Cantidad de Efectivo en caja");
		dialog.setTitle("Espere");
		dialog.setHeaderText("Por favor introduzca la cantidad de dinero en caja");
		dialog.setContentText("Cantidad:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Cantidad de dinero " + result.get());
		    cantidadInicial = Float.parseFloat(result.get());
		}

		verificarBotones();
		acero.setVisible(true);
		madera.setVisible(true);
		azulejo.setVisible(true);
		cemento.setVisible(true);
		pintura.setVisible(true);
		lamina.setVisible(true);
		compra.setVisible(true);
		caja.setVisible(true);
		regSuppiler.setVisible(true);
		seeSuppiler.setVisible(true);
		regisUser.setVisible(true);
		infoUser.setVisible(true);
	}
}