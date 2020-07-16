package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Proveedor;

public class ListaProveedoresController {
	ObservableList<Proveedor> lista = FXCollections.observableArrayList();
	Main main;
	Connection conn=null;
    Conexion con = new Conexion();
    Proveedor proveedorsito;
    
    @FXML
    private AnchorPane listaPane, editProveedorPane; 
    
    @FXML
    private TableView<Proveedor> tablaProveedores;

    @FXML
    private TableColumn<Proveedor, String> idProveedor;

    @FXML
    private TableColumn<Proveedor, String> nombreEmpresa;

    @FXML
    private TableColumn<Proveedor, String> contactReferencia;

    @FXML
    private TableColumn<Proveedor, String> direccion;

    @FXML
    private TableColumn<Proveedor, String> numeroTelefonico;

    @FXML
    private TableColumn<Proveedor, String> codigoPostal;

    @FXML
    private TableColumn<Proveedor, String> ciudad;

  
    @FXML
    private JFXButton eliminar;

    @FXML
    private JFXButton modificar;

    @FXML
    private JFXButton salir;
    
    static String c;
    
 
    @FXML
	public void initialize() throws SQLException {
		agregar();
		select();
		idProveedor.setCellValueFactory(cellData->cellData.getValue().getIdProveedor());
		contactReferencia.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getContactoReferenciaProperty()));
		nombreEmpresa.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreEmpresaProperty()));
		direccion.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDireccionProperty()));
		numeroTelefonico.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNumeroTelefonicoProperty()));
		codigoPostal.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCodigoPostalProperty()));
		ciudad.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCiudadProperty()));
		listaPane.setVisible(true);
		
		seleccion();
		
	}
    
    public void iniciar(ActionEvent e) throws SQLException {
    	eliminar();
    	
    }
	
	public void select() throws SQLException {
		String consulta = "select * from MaterialesDeCon.dbo.Proveerdor";
		conn = con.getConection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			//System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3)+"-"+rs.getString(4)+"-"+rs.getString(5)+"-"+rs.getString(6)+"-"+rs.getString(7));
			lista.add(new Proveedor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
	}
	public void agregar() {
		tablaProveedores.setItems(lista);
	}

	
	public void seleccion() throws SQLException{
		tablaProveedores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Proveedor>() {

			@Override
			public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
				// TODO Auto-generated method stub
				if(newValue != null) {
					c=newValue.getIdProveedorProperty();
					System.out.println("id proveedor"+c);
				}
				
			}
		});
	}
	
	
	public void eliminar() throws SQLException{
		String consulta = "delete from MaterialesDeCon.dbo.Proveerdor where idProveedor ='"+c+"'";
		conn = con.getConection();
		PreparedStatement st = conn.prepareStatement(consulta);
		st.executeUpdate();
		c = "";
		tablaProveedores.getItems().clear();
		lista.clear();
		initialize();
		
	}
	public void setMain(Main main) {
			this.main=main;
	}
	
	private void ocultar() {
		listaPane.setVisible(false);
		editProveedorPane.setVisible(false);
	}
	
	@FXML
	public void editProveedor() {
		ocultar();
		editProveedorPane.setVisible(true);
	}
	
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	
	///// edicion de proveedor
	
	@FXML
    private JFXTextField editEmpresa, editNombreContacto, editTelefonoContacto;

	
	@FXML
	public void guardarEdicionProveedor(){
		String empresa = editEmpresa.getText();
		String nombre = editNombreContacto.getText();
		String telefono  = editTelefonoContacto.getText();
		
		System.out.println(empresa);
		System.out.println(nombre);
		System.out.println(telefono);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Éxito");
		alert.setHeaderText(null);
		alert.setContentText("El proveedor se ha modificado correctamente");
		alert.showAndWait();
		
		ocultar();
	}
	
	@FXML
	public void cancelar() {
		editEmpresa.setText("");
		editNombreContacto.setText("");
		editTelefonoContacto.setText("");
		ocultar();
		listaPane.setVisible(true);
	}
}
	
