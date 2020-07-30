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
import javafx.scene.control.Label;
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
    private JFXButton listUser;

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
    
    @FXML
    private Label infoName;
    
    @FXML
    private Label infoApellido;
    
    @FXML
    private Label infoPuesto;
    //--------------------------------------------------------------------------------------------------------------------------------
    
    @FXML
    private JFXTextField campoId;

    @FXML
    private JFXTextField campoContactoReferencia;

    @FXML
    private JFXTextField campoEmpresa;

    @FXML
    private JFXTextField campoDireccion;

    @FXML
    private JFXTextField campoTelefono;

    @FXML
    private JFXTextField campoCodPostal;

    @FXML
    private JFXTextField campoCiudad;
 
    //static float cantidadInicial= Float.parseFloat(JOptionPane.showInputDialog(null, "Inserte el ingreso del dia: "));
	static float cantidadInicial = 0;
	private Connection con = null;
	private PreparedStatement ps;
	private ResultSet res;
	
	 @FXML
	void registrarUsuario(ActionEvent event) throws SQLException {
		 if(!camposVaciosUser()) {
			 if(campoPassword.getText().equals(campoPassword2.getText())) {
				 
				con = Conexion.getConection();
				String consulta = "INSERT INTO MaterialesDeCon.dbo.Usuario VALUES ('"+campoNombre.getText()+"','"+campoApellido.getText()+
										"','"+campoPuesto.getText()+"','"+campoPassword.getText()+"')";
				
				ps = con.prepareStatement(consulta);
			    ps.executeUpdate();
			    System.out.println(ps);
			    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
				alerta.setTitle("CONFIRMACIÓN");
				alerta.setContentText("El usuario se ha registrado");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
				verificarBotones();
				userPane.setVisible(true);
			 }else {
				 Alert alerta = new Alert(Alert.AlertType.INFORMATION);
					alerta.setTitle("Advertencia");
					alerta.setContentText("Las constraseñas no coinciden");
					alerta.initStyle(StageStyle.UTILITY);
					alerta.setHeaderText(null);
					alerta.showAndWait();
			 }
		 }
	}
	 
	public boolean camposVaciosUser() {
		boolean uservacio = false;
		
		if(campoNombre.getText().isEmpty() || campoApellido.getText().isEmpty() || campoPuesto.getText().isEmpty() || campoPassword.getText().isEmpty() || campoPassword2.getText().isEmpty()) {
			uservacio = true;
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Debes llenar todos los campos");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
		return uservacio;
	}
	 
	 @FXML
	void registrarProveedor(ActionEvent event) throws SQLException {
		 if(!camposVaciosProveedor()) {
			 System.out.println("paso todo");
		    con = Conexion.getConection();
			String consulta = "INSERT INTO MaterialesDeCon.dbo.Proveerdor VALUES ('"+campoId.getText()+"','"+campoContactoReferencia.getText()+
									"','"+campoEmpresa.getText()+"','"+campoDireccion.getText()+"','"+campoTelefono.getText()+"',"
									+ "'"+campoCodPostal.getText()+"','"+campoCiudad.getText()+"')";
			
			ps = con.prepareStatement(consulta);
		    ps.executeUpdate();
		    System.out.println(ps);
		    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("CONFIRMACIÓN");
			alerta.setContentText("El nuevo proveedor se ha registrado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			verificarBotones();
			provedoresPane.setVisible(true);
		 }
	 }
	 
	 public boolean camposVaciosProveedor() {
			boolean proveedorvacio = false;
			
			if(campoId.getText().isEmpty() || campoContactoReferencia.getText().isEmpty() || 
				campoEmpresa.getText().isEmpty() || campoDireccion.getText().isEmpty() || 
				campoTelefono.getText().isEmpty() || campoCodPostal.getText().isEmpty() || 
				campoCiudad.getText().isEmpty()) {
				
				proveedorvacio = true;
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("Debes llenar todos los campos");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
			}
			return proveedorvacio;
		}
	 
	 @FXML
	public void regresar(ActionEvent event) {
		infoName.setText("");
		infoApellido.setText("");
		infoPuesto.setText("");
		verificarBotones();
		userPane.setVisible(true);
	}
	
	public void cagarMenuUsuario(ActionEvent event) {
		verificarBotones();
		if(!main.puesto.equals("admin")) {
			regisUser.setVisible(false);
			listUser.setVisible(false);
		}
		userPane.setVisible(true);
	}
	
	public void cagarProductos(ActionEvent event) {
		verificarBotones();
		if(main.puesto.equals("admin")) {
			registroProducto.setVisible(true);
		}
		materialesPane.setVisible(true);
	}
	
	public void cagarCaja(ActionEvent event) {
		verificarBotones();
		ventaPane.setVisible(true);
	}
	
	public void cagarProveedores(ActionEvent event) {
		verificarBotones();
		if(!main.puesto.equals("admin")) {
			regSuppiler.setVisible(false);
		}
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
		//Stage s = (Stage)compra.getScene().getWindow();
    	//s.close();
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
	
	@FXML
	void cargarTickets() {
		main.cargarListaTickets();
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
		infoName.setText(main.name);
		infoApellido.setText(main.apellido);
		infoPuesto.setText(main.puesto);
		showUserInfoPane.setVisible(true);
		
	}
	
	@FXML
	public void showUsers() {
		main.cargarListaUsuarios();
	}
	
	public void IniciarTodo() {
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
		listUser.setVisible(true);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TextInputDialog dialog = new TextInputDialog("0");
		dialog.setTitle("Espere");
		dialog.setHeaderText("Por favor introduzca la cantidad de dinero en caja \nPor Default hay $0");
		dialog.setContentText("Cantidad:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Cantidad de dinero " + result.get());
		    cantidadInicial = Float.parseFloat(result.get());
		    IniciarTodo();
		}
	}
}
