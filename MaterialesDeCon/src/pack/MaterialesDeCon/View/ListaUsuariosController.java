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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;
import pack.MaterialesDeCon.Model.Usuario;

public class ListaUsuariosController {
	ObservableList<Usuario> lista = FXCollections.observableArrayList();
	Main main;
	Connection con=null;
    private PreparedStatement ps;
    Usuario usuario;
    
    @FXML
    private AnchorPane listaUserPane, editUserPane; 
    
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
    
    ArrayList<ArrayList<String>> listaUsuarios = new ArrayList<ArrayList<String>>();
    
    String idUser = "";
    
    @FXML
    private JFXTextField editNombre, editApellidos, editPuesto;
    
 
    @FXML
	public void initialize() throws SQLException {
		agregar();
		select();
		tableId.setCellValueFactory(cellData->cellData.getValue().getIdUsuario());
		tableNombre.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreProperty()));
		tableApellidos.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getApellidoPaternoProperty()));
		tablePuesto.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPuestoProperty()));
		listaUserPane.setVisible(true);		
		seleccion();	
	}
    
    public void iniciar(ActionEvent e) throws SQLException {
    	eliminar();
    	
    }
	
	public void select() throws SQLException {
		String consulta = "select * from MaterialesDeCon.dbo.Usuario";
		con = Conexion.getConection();
		Statement st = con.createStatement();
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
				ArrayList<String> listTemp = new ArrayList<String>();
				if(newValue != null) {
					listaUsuarios.clear();
					listTemp.clear();
					listTemp.add(newValue.getIdUsuarioProperty());
					listTemp.add(newValue.getNombreProperty());
					listTemp.add(newValue.getApellidoPaternoProperty());
					listTemp.add(newValue.getPuestoProperty());
					listaUsuarios.add(listTemp);
				}	
			}
		});
	}
	
	
	public void eliminar() throws SQLException{
		String consulta = "delete from MaterialesDeCon.dbo.Usuario where idUsuario ='"+c+"'";
		con = Conexion.getConection();
		PreparedStatement st = con.prepareStatement(consulta);
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
		listaUserPane.setVisible(false);
		editUserPane.setVisible(false);
	}
	
	@FXML
	public void ShowViewEditUser() {
		if(!listaUsuarios.isEmpty()) {
			ocultar();
			editUserPane.setVisible(true);
			idUser = listaUsuarios.get(0).get(0);
			
			String date02 = listaUsuarios.get(0).get(1);
			String date03 = listaUsuarios.get(0).get(2);
			String date04 = listaUsuarios.get(0).get(3);
			
			System.out.println(" ID: "+idUser 
					+ "\n Nombre: " + date02 
					+ "\n Apellidos: " + date03 
					+ "\n Puesto: " + date04);
			
			editNombre.setText(date02);
			editApellidos.setText(date03);
			editPuesto.setText(date04);
		}
	}
	
	@FXML
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	@FXML
	public void guardarEdicionUsuario(){
		String consulta = "update dbo.Usuario set nombre='"+editNombre.getText()+"', "
				+ "apellidoPaterno='"+editApellidos.getText()+"', puesto='"+editPuesto.getText()
				+"' where idUsuario='"+idUser+"';";
		try {
			ps = con.prepareStatement(consulta);
			ps.executeUpdate();
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("CONFIRMACIÓN");
			alerta.setContentText("El Usuario se ha editado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			ocultar();
			listaUserPane.setVisible(true);
			tablaUsuarios.getItems().clear();
			lista.clear();
			initialize();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setContentText("El usuario no se ha logrado editar");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
	}
	
	@FXML
	public void cancelar() {
		//editNombre.setText("");
		//editApellidos.setText("");
		//editPuesto.setText("");
		ocultar();
		listaUserPane.setVisible(true);
	}
}
	
