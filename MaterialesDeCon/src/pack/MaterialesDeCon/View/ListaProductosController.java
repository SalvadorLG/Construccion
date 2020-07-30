package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;

public class ListaProductosController {
	ObservableList<Producto> lista = FXCollections.observableArrayList();
	Main main;
	Connection con=null;
    private PreparedStatement ps;
    Producto productito;
    @FXML
    private AnchorPane materialesPane, editProductosPane;
    
    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> idCategoria;

    @FXML
    private TableColumn<Producto, String> nombreProducto;

    @FXML
    private TableColumn<Producto, String> categoriaProducto;

    @FXML
    private TableColumn<Producto, Number> price;

    @FXML
    private TableColumn<Producto, Number> existencia;

    @FXML
    private TableColumn<Producto, String> proveedor;
    @FXML
    private JFXButton eliminar;

    @FXML
    private JFXButton modificar;

    @FXML
    private JFXButton salir;
    
    @FXML
    private JFXTextField editNombre;

    @FXML
    private JFXTextField editPrecio;

    @FXML
    private JFXTextField editExistencia;

    @FXML
    private JFXComboBox<Label> editCategoria;

    @FXML
    private JFXComboBox<Label> editProveedor;
    
    static String c = "";
    StringProperty cat;
    
    ArrayList<ArrayList<String>> listaProductos = new ArrayList<ArrayList<String>>();
    ArrayList<String> listProv = new ArrayList<String>();
    ArrayList<String> listProvId = new ArrayList<String>();
    ArrayList<String> listCat = new ArrayList<String>();
    ArrayList<String> listCatId = new ArrayList<String>();
    
    int ind, inx;
    String idProduct = "";
    
    @FXML
	public void initialize() throws SQLException {
    	addValuestoComboboxCatgandProv();
		agregar();
		select();
		idCategoria.setCellValueFactory(cellData->cellData.getValue().getIdProducto());
		nombreProducto.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreoProperty()));
		categoriaProducto.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdCategoriaProperty()));
		price.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getPrecioUnitarioProperty()));
		existencia.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getExistenciaProperty()));
		proveedor.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdProvedorProperty()));
		seleccion();
	}
    
    public void addValuestoComboboxCatgandProv() {
    	//editCategoria.getItems().add(new Label("Acero"));
    	//editCategoria.getItems().add(new Label("Azulejo"));
    	//editCategoria.getItems().add(new Label("Cemento"));
    	//editCategoria.getItems().add(new Label("Lamina"));
    	//editCategoria.getItems().add(new Label("Madera"));
    	//editCategoria.getItems().add(new Label("Pintura"));
    	
    	String consulta = "select categoriaId, nombreCategoria from dbo.Categorias";
		 
		con = Conexion.getConection();
		Statement stc;
		try {
			stc = con.createStatement();
			ResultSet rs = stc.executeQuery(consulta);
			while(rs.next()) {
				editCategoria.getItems().add(new Label(rs.getString(2)));
				listCatId.add(rs.getString(1));
				listCat.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	String query = "select idProveedor, nombreEmpresa from dbo.Proveerdor";
		 
		con = Conexion.getConection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				editProveedor.getItems().add(new Label(rs.getString(2)));
				listProvId.add(rs.getString(1));
				listProv.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void iniciar(ActionEvent e) throws SQLException {
    	eliminar();
    	
    }
	
	public void select() throws SQLException {
		
		String caty = Main.categoria;
		String consulta = "";
		System.out.println(caty);
		
		if(caty == "inventario") {
			consulta = "select nombreProducto, idProducto, nombreEmpresa, nombreCategoria, precioUnitario, existencia from Producto "+
					"inner join Categorias on Producto.idCategoria = Categorias.categoriaId "+ 
					"inner join Proveerdor on Producto.idProveerdor = Proveerdor.idProveedor";
		}else{
			consulta = "select nombreProducto, idProducto, nombreEmpresa, nombreCategoria, precioUnitario, existencia from Producto "+ 
					"inner join Categorias on Producto.idCategoria = Categorias.categoriaId "+ 
					"inner join Proveerdor on Producto.idProveerdor = Proveerdor.idProveedor where idCategoria = '"+caty+"'";
		}
		
		con = Conexion.getConection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			lista.add(new Producto(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4), rs.getInt(6), rs.getFloat(5)));
		}
	}
	public void agregar() {
		tablaProductos.setItems(lista);
	}

	
	public void seleccion() throws SQLException{
		tablaProductos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producto>() {

			@Override
			public void changed(ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) {
				ArrayList<String> listTemp = new ArrayList<String>();
				if(newValue != null) {
					listaProductos.clear();
					listTemp.clear();
					c = newValue.getIdProductoProperty();
					listTemp.add(newValue.getIdProductoProperty());
					listTemp.add(newValue.getNombreoProperty());
					listTemp.add(newValue.getIdCategoriaProperty());
					listTemp.add(String.valueOf(newValue.getPrecioUnitarioProperty()));
					listTemp.add(String.valueOf(newValue.getExistenciaProperty()));
					listTemp.add(newValue.getIdProvedorProperty());
					listaProductos.add(listTemp);
				}
			}
		});
	}
	
	@FXML
	public void agregarProducto() {
		main.cagarRegistroProducto();
		System.out.println("Espero tiempo");
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	public void editar(){
		String consulta = "update dbo.Producto set nombreProducto = '"+editNombre.getText()+"', "
						+ "idProveerdor = '"+listProvId.get(inx)+"', idCategoria = '"+listCatId.get(ind)+"', "
						+ "precioUnitario = '"+editPrecio.getText()+"', existencia='"+editExistencia.getText()+"' "
						+ "where idProducto='"+idProduct+"';";
		try {
			ps = con.prepareStatement(consulta);
			ps.executeUpdate();
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("CONFIRMACIÓN");
			alerta.setContentText("El producto se ha editado");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
			ocultar();
			materialesPane.setVisible(true);
			tablaProductos.getItems().clear();
			lista.clear();
			initialize();
			
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setContentText("El producto no se ha logrado editar");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
	}
	
	public void eliminar() throws SQLException{
		String consulta = "delete from MaterialesDeCon.dbo.Producto where idProducto ='"+c+"'";
		con = Conexion.getConection();
		PreparedStatement st = con.prepareStatement(consulta);
		st.executeUpdate();
		c = "";
		tablaProductos.getItems().clear();
		lista.clear();
		initialize();
	}
	
	private void ocultar() {
		 materialesPane.setVisible(false);
		 editProductosPane.setVisible(false);
	 }
	 
	 @FXML
	 public void showEditView() {
		 if(!listaProductos.isEmpty()) {
			 ocultar();
			 editProductosPane.setVisible(true);
			 String date01 = listaProductos.get(0).get(0);
			 idProduct = date01;
			 String date02 = listaProductos.get(0).get(1);
			 String date03 = listaProductos.get(0).get(2);
			 String date04 = listaProductos.get(0).get(3);
			 String date05 = listaProductos.get(0).get(4);
			 String date06 = listaProductos.get(0).get(5);
			 
			 System.out.println(date01 + " - " + date02 + " - " + date03 + " - " + date04 + " - " + date05 + " - " + date06);
			 
			 editNombre.setText(date02);
			 editPrecio.setText(date04);
			 editExistencia.setText(date05);
			 
			 ind = CategoriaDefault(date03);
			 editCategoria.getSelectionModel().select(ind);
			 
			 inx = ProveedorDefault(date06);
			 editProveedor.getSelectionModel().select(inx);
		 }
		 else {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("");
				alerta.setContentText("No ha seleccionado un Producto");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
			}
	 }
	 
	 private int CategoriaDefault(String date03) {
		 int index = 0;
		 if(date03.equals("Acero")) {
			 index = 0;
			 }else if (date03.equals("Azulejo")) {
				 index = 1; 
			 }else if (date03.equals("Cemento")) {
				 index = 2;
			 }else if (date03.equals("Lamina")) {
				 index = 3;
			 }else if (date03.equals("Madera")) {
				 index = 4;
			 }else if (date03.equals("Pintura")) {
				 index = 5;
			 }
		 return index;
	 }
	 
	 private int ProveedorDefault(String date06) {
		int index = 0;
		int tam = listProv.size();
		int i = 0;
		while(i < tam) {
			if(date06.equals(listProv.get(i))) {
				index = i;
				break;
			}
			i++;
		}
		return index;
	 }
	
	 @FXML
	public void cancelar() {
		editNombre.setText("");
		editPrecio.setText("");
		editExistencia.setText("");
		//editCategoria
		//editProveedor
		ocultar();
		materialesPane.setVisible(true);
	}
 
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
}
