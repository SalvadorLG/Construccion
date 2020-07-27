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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;
import pack.MaterialesDeCon.Model.Venta;

public class VentaController {


	@FXML
    private TableView<Producto> tablaVenta;

    @FXML
    private TableColumn<Producto, String> idVenta;

    @FXML
    private TableColumn<Producto, String> nombreVenta;
    @FXML
    private TableColumn<Producto, Number> cantidadVenta;

    @FXML
    private TableColumn<Producto, Number> precioVenta;

    @FXML
    private JFXButton agregar;
    
    @FXML
    private JFXButton salir;

    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXTextField nomVenta;

    @FXML
    private JFXTextField identificador;

    @FXML
    private JFXButton finalizar;

    @FXML
    private ImageView carrito;

    @FXML
    private JFXTextField total;

    @FXML
    private JFXTextField efectivo;
    @FXML
    private Label carito;
    
    Main main;
    Connection conn=null;
    Conexion con = new Conexion();
    ObservableList<Producto> lista = FXCollections.observableArrayList();
    static ObservableList<Venta> canasta= FXCollections.observableArrayList();
    static String va, name;
    float preUni=0;
    int contador=0;
    public static float subTotal=0;
    float montoIva=0;
    float price=0;
    String nom=null;
    int cantidadactual= 0;
    int cant=0;
    boolean comprarealizada = false; 
    @FXML
    public void initialize() throws SQLException {
		query();
		agregar();
		
		idVenta.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdProductoProperty()));
		nombreVenta.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreoProperty()));
		cantidadVenta.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getExistenciaProperty()));
		precioVenta.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getPrecioUnitarioProperty()));
		seleccion();
	}
	
    
    
    public void query() throws SQLException {
    	String consulta= "select idProducto,nombreProducto,precioUnitario,existencia from MaterialesDeCon.dbo.Producto";
    	conn = con.getConection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			lista.add(new Producto(rs.getString(1), rs.getString(2), null, null, rs.getInt(4), rs.getFloat(3)));
		}
    }
    
    public void agregar() {
    	tablaVenta.setItems(lista);
	}
    
    public void seleccion() {
    	tablaVenta.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Producto>() {

			@Override
			public void changed(ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) {
				// TODO Auto-generated method stub
					nomVenta.setText(newValue.getNombreoProperty());
					identificador.setText(newValue.getIdProductoProperty());
					va= newValue.getIdProductoProperty();
					preUni=newValue.getPrecioUnitarioProperty();
					nom=newValue.getNombreoProperty();
					cantidadactual=newValue.getExistenciaProperty();
			}
		});
    }
    
    private void disminuirInvetario() throws SQLException{
    	int dis = cantidadactual-cant;
    	String query = "update dbo.Producto set existencia = '"+String.valueOf(dis)+"' where nombreProducto = '"+nomVenta.getText()+"'";
    	conn = con.getConection();
		Statement st = conn.createStatement();
		st.executeUpdate(query);
    }
    
    public boolean verificarProductoSeleccionado() {
    	boolean vacios = false;
    	if(nomVenta.getText().isEmpty() || identificador.getText().isEmpty()){
    		vacios = true;
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("No has seleccionado ningun producto");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
    	}
    	
    	return vacios;
    }
    
    public boolean verificarCantidadIngresada() {
    	boolean vacios = false;
    	if(cantidad.getText().isEmpty()) {
    		vacios=true;
    		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Advertencia");
			alerta.setContentText("No has agregado una cantidad");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
    	}
    	return vacios;
    }
    
	 public void agregarProducto(ActionEvent even) throws SQLException{
		 if(!verificarProductoSeleccionado()) {
			 if(!verificarCantidadIngresada()) {
				 comprarealizada = true;
				 cant= Integer.parseInt(cantidad.getText());
			    	price= preUni*cant;
			    	Venta ventita = new Venta(nomVenta.getText(), cant, price, va);
			    	canasta.add(ventita);
			
			        subTotal+=price;
			        System.out.println(subTotal);
			    	
			    	total.setText(Float.toString(subTotal));
			    	contador++;
			    	carito.setText(Integer.toString(contador));
			    	
			    	String consulta="INSERT INTO MaterialesDeCon.dbo.VentaDetalle(precio,cantidad,nombreProducto)" + "values (?,?,?)";
			    	 try {
						conn = con.getConection();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("no se pudo");
					}
			    	try {
			    		PreparedStatement a = conn.prepareStatement(consulta);
			    		a.setFloat(1, preUni);
			    		a.setInt(2, cant);
			    		a.setString(3, nom);
			    		a.execute();
			    		System.out.println("si pude");
			    	}catch(Exception e) {
			    		System.out.println("no se puso perro");
			    	}
			    	disminuirInvetario();
			 }
		 }
	  }
	    
	 public static ObservableList<Venta> carrito(){
	    	return canasta;
	  }
	 
	 @FXML
		void cargarPagoEfec(ActionEvent event) {
		 	//Stage s = (Stage)finalizar.getScene().getWindow();
	    	//s.close();
		 if(comprarealizada) {
			 main.cagarPago(); 
		 }else {
			 Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Advertencia");
				alerta.setContentText("No has agregado ningun producto");
				alerta.initStyle(StageStyle.UTILITY);
				alerta.setHeaderText(null);
				alerta.showAndWait();
		 }
		}
	    
	  public static Float prePago(){
	    	return subTotal;
	  }
	    
  @FXML
    private void salir() {
    	Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
    }
    
    public void setMain(Main main) {
		this.main=main;
	}

}