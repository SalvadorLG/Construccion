package pack.MaterialesDeCon.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Categoria;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;


public class RegistroProductoController implements Initializable{
	Main main;
	@FXML
    private JFXComboBox<Label> ctg;
	@FXML
    private JFXComboBox<Label> provedor;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField preUni;
    @FXML
    private JFXTextField extc;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton guardar;
    private String Categorias[];
    Connection con=null;
    private PreparedStatement ps;
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ctg.getItems().add(new Label("Acero"));
		ctg.getItems().add(new Label("Azulejo"));
		ctg.getItems().add(new Label("Cemento"));
		ctg.getItems().add(new Label("Lamina"));
		ctg.getItems().add(new Label("Madera"));
		ctg.getItems().add(new Label("Pintura"));
		ctg.setPromptText("Selecciona una categoria");
		//ctg.getSelectionModel().select(3);
		
		
		String query = "select nombreEmpresa from dbo.Proveerdor";
		con = Conexion.getConection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				provedor.getItems().add(new Label(rs.getString(1)));
			}
			provedor.setPromptText("Selecciona un proveedor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verificarFormulario() {
		boolean vacios = false;
		if(nom.getText().isEmpty() || id.getText().isEmpty() || provedor.getSelectionModel().isEmpty() || 
				ctg.getSelectionModel().isEmpty() || preUni.getText().isEmpty() || extc.getText().isEmpty()) {
			vacios = true;
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("Debes llenar todos los campos");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
		return vacios;
	}
	
    @FXML
    private void aniadir(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	verificarFormulario();
    	String Proveedor = verificarProveedor(provedor.getValue().getText());
    	String Categoria = verificarCategoria(ctg.getValue().getText());
    	
    	if(!Proveedor.equals("") && !Categoria.equals("")) {
    		String consulta="INSERT INTO MaterialesDeCon.dbo.Producto VALUES ('"+nom.getText()+"','"+id.getText()+"','"+Proveedor+"','"+Categoria+"','"+preUni.getText()+"','"+extc.getText()+"')";
			ps = con.prepareStatement(consulta);
			ps.executeUpdate();
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("CONFIRMACIÓN");
			alerta.setContentText("El producto se ha agregado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			main.cargarListaMateriales("inventario");
    	}else {
    		Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setContentText("El producto no se ha podido agregar");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
    	}
    	
    	Stage s = (Stage)guardar.getScene().getWindow();
    	s.close();
    }
    
    private String verificarProveedor(String Proveedor) throws SQLException {
    	String query = "select * from dbo.Proveerdor where nombreEmpresa = '"+Proveedor+"'";
    	String prove = "", id = "";
    	con = Conexion.getConection();
		Statement st;
    	st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				id = rs.getString(1);
				prove = rs.getString(3);
			}
		if(prove.equals(Proveedor)) {
			System.out.println(id+"-"+prove);
			return id;
		}else {
			return "";
		}
    }
    
    private String verificarCategoria(String Categoria) throws SQLException {
    	String query = "select * from dbo.Categorias where nombreCategoria = '"+Categoria+"'";
    	String catg = "", id = "";
    	con = Conexion.getConection();
		Statement st;
    	st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				id = rs.getString(1);
				catg = rs.getString(2);
			}
		if(catg.equals(Categoria)) {
			System.out.println(id+"-"+catg);
			return id;
		}else {
			return "";
		}
    }
    
    @FXML
    private void cancelar() {
    	Stage s = (Stage)cancelar.getScene().getWindow();
    	s.close();
    }

    public void setMain(Main main) {
		this.main=main;
	}
}
