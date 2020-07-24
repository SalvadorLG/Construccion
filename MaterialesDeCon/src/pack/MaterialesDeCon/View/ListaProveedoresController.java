package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Proveedor;

public class ListaProveedoresController {
	ObservableList<Proveedor> lista = FXCollections.observableArrayList();
	Main main;
	Connection con=null;
    private PreparedStatement ps;
    Proveedor proveedorsito;
    ArrayList<ArrayList<String>> listaProveedores = new ArrayList<ArrayList<String>>();
    
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
    
    @FXML
    private JFXTextField editContacto;

    @FXML
    private JFXTextField editEmpresa;

    @FXML
    private JFXTextField editDireccion;

    @FXML
    private JFXTextField editTelefonoEmpresa;

    @FXML
    private JFXTextField editCiudad;

    @FXML
    private JFXTextField editCodigo;
    
    static String c;
    
    String idProv = "";
 
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
		con = Conexion.getConection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
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
				ArrayList<String> listTemp = new ArrayList<String>();
				if(newValue != null) {
					listaProveedores.clear();
					listTemp.clear();
					listTemp.add(newValue.getIdProveedorProperty());
					listTemp.add(newValue.getContactoReferenciaProperty());
					listTemp.add(newValue.getNombreEmpresaProperty());
					listTemp.add(newValue.getDireccionProperty());
					listTemp.add(newValue.getNumeroTelefonicoProperty());
					listTemp.add(newValue.getCodigoPostalProperty());
					listTemp.add(newValue.getCiudadProperty());
					listaProveedores.add(listTemp);
				}
			}
		});
	}
	
	
	public void eliminar() throws SQLException{
		String consulta = "delete from MaterialesDeCon.dbo.Proveerdor where idProveedor ='"+c+"'";
		con = Conexion.getConection();
		PreparedStatement st = con.prepareStatement(consulta);
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
	 public void showEditViewProveedor() {
		if(!listaProveedores.isEmpty()) {
			ocultar();
			editProveedorPane.setVisible(true);
			//System.out.println(listaProveedores.get(0));
			idProv = listaProveedores.get(0).get(0);
			
			String date02 = listaProveedores.get(0).get(1);
			String date03 = listaProveedores.get(0).get(2);
			String date04 = listaProveedores.get(0).get(3);
			String date05 = listaProveedores.get(0).get(4);
			String date06 = listaProveedores.get(0).get(5);
			String date07 = listaProveedores.get(0).get(6);
			
			System.out.println(" ID: "+idProv 
							+ "\n Contacto: " + date02 
							+ "\n Empresa: " + date03 
							+ "\n Direccion: " + date04 
							+ "\n Telefono: " + date05 
							+ "\n CP: " + date06 
							+ "\n Ciudad: " + date07);
			editContacto.setText(date02);
			editEmpresa.setText(date03);
			editDireccion.setText(date04);
			editTelefonoEmpresa.setText(date05);
			editCodigo.setText(date06);
			editCiudad.setText(date07);
		}
	}
	
	@FXML
	public void editProveedor() {
		
		String consulta = "update dbo.Proveerdor set contactoReferencia='"+editContacto.getText()+"', "
						+ "nombreEmpresa='"+editEmpresa.getText()+"', direccion='"+editDireccion.getText()
						+"', numeroTelefonico='"+editTelefonoEmpresa.getText()+"', codigoPostal='"+editCodigo.getText()+"', "
						+ "ciudad='"+editCiudad.getText()+"' where idProveedor='"+idProv+"';";
		try {
			ps = con.prepareStatement(consulta);
			ps.executeUpdate();
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("CONFIRMACIÓN");
			alerta.setContentText("El proveedor se ha editado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			ocultar();
			listaPane.setVisible(true);
			tablaProveedores.getItems().clear();
			lista.clear();
			initialize();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setContentText("El proveedor no se ha logrado editar");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
	}
	
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	
	@FXML
	public void cancelar() {
		//editEmpresa.setText("");
		//editNombreContacto.setText("");
		//editTelefonoContacto.setText("");
		ocultar();
		listaPane.setVisible(true);
	}
}
	
