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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;
import pack.MaterialesDeCon.Model.Usuario;

public class ListaUsuariosController {
	ObservableList<Usuario> lista = FXCollections.observableArrayList();
	Main main;
	Connection conn=null;
    Conexion con = new Conexion();
    Usuario usuario;
    
    @FXML
    private AnchorPane listaPane, editUserPane; 
    
    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, String> tableId;

    @FXML
    private TableColumn<Usuario, String> tableNombre;

    @FXML
    private TableColumn<Usuario, String> tableApellidos;

    @FXML
    private TableColumn<Usuario, String> tablePuesto;

  
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
		tableId.setCellValueFactory(cellData->cellData.getValue().getIdUsuario());
		tableNombre.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreProperty()));
		tableApellidos.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getApellidoPaternoProperty()));
		tablePuesto.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPuestoProperty()));
		listaPane.setVisible(true);		
		seleccion();	
	}
    
    public void iniciar(ActionEvent e) throws SQLException {
    	eliminar();
    	
    }
	
	public void select() throws SQLException {
		String consulta = "select * from MaterialesDeCon.dbo.Usuario";
		conn = con.getConection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			lista.add(new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
	}
	public void agregar() {
		tablaUsuarios.setItems(lista);
	}

	
	public void seleccion() throws SQLException{
		tablaUsuarios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {

			@Override
			public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
				// TODO Auto-generated method stub
				if(newValue != null) {
					c=newValue.getIdUsuarioProperty();
					System.out.println(newValue.getNombreProperty());
					System.out.println(newValue.getApellidoPaternoProperty());
					System.out.println(newValue.getPuestoProperty());
				}	
			}
		});
	}
	
	
	public void eliminar() throws SQLException{
		String consulta = "delete from MaterialesDeCon.dbo.Usuario where idUsuario ='"+c+"'";
		conn = con.getConection();
		PreparedStatement st = conn.prepareStatement(consulta);
		st.executeUpdate();
		c = "";
		tablaUsuarios.getItems().clear();
		lista.clear();
		initialize();
	}
	public void setMain(Main main) {
			this.main=main;
	}
	
	private void ocultar() {
		listaPane.setVisible(false);
		editUserPane.setVisible(false);
	}
	
	@FXML
	public void editProveedor() {
		ocultar();
		editUserPane.setVisible(true);
	}
	
	@FXML
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	///// edicion de Usuario
	
	@FXML
    private JFXTextField editNombre, editApellidos, editPuesto;

	
	@FXML
	public void guardarEdicionUsuario(){
		String nombre = editNombre.getText();
		String apellidos = editApellidos.getText();
		String puesto  = editPuesto.getText();
		
		System.out.println(nombre);
		System.out.println(apellidos);
		System.out.println(puesto);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Éxito");
		alert.setHeaderText(null);
		alert.setContentText("El Usuario se ha modificado correctamente");
		alert.showAndWait();
		
		ocultar();
		listaPane.setVisible(true);
	}
	
	@FXML
	public void cancelar() {
		editNombre.setText("");
		editApellidos.setText("");
		editPuesto.setText("");
		ocultar();
		listaPane.setVisible(true);
	}
}
	
