package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.OrdenVenta;
import pack.MaterialesDeCon.Model.Producto;
import pack.MaterialesDeCon.Model.Venta;
import pack.MaterialesDeCon.View.PrincipalMenuController;

public class CorteCajaController {
	@FXML
    private JFXTextField fecha;

    @FXML
    private JFXTextField ingresoDia;

    @FXML
    private JFXTextField totalGanancias;

    @FXML
    private JFXButton regresar;
    @FXML
    private TableView<OrdenVenta> ingresos;

    @FXML
    private TableColumn<OrdenVenta, Number> ingreso;

    @FXML
    private TableColumn<OrdenVenta, Number> salida;

    @FXML
    private Label flujo;

    @FXML
    private JFXTextField totGan;

    @FXML
    private JFXTextField totSalida;

    @FXML
    private JFXTextField totIng;

    
    Main main;
    Connection conn=null;
    Conexion con = new Conexion();
    ObservableList<OrdenVenta> lista = FXCollections.observableArrayList();
    ObservableList<Float> sumaIngresos= FXCollections.observableArrayList();
    ObservableList<Float> sumaSalidas= FXCollections.observableArrayList();
    static float ingrs;
    @FXML
    public void initialize() throws SQLException {
    	verFlujoCaja();
    }
    
    
    public void verFlujoCaja() throws SQLException{
    	query();
    	cargar();
		ingreso.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getPagoProperty()));
		salida.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getCambioProperty()));
		fecha();
		ingresoDia();
		totalGanancias();
    }
    
    public void query() throws SQLException {
    	String consulta= "select ingreso,salida from MaterialesDeCon.dbo.CorteCaja";
    	conn = con.getConection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			lista.add(new OrdenVenta(null, null, 0,0, rs.getFloat(1), rs.getFloat(2),null));
		}
    }
    
    public void fecha(){
        LocalDate localDate = LocalDate.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / LLLL / yyyy");
    	String formattedString;formattedString = localDate.format(formatter);
		fecha.setText(formattedString);
    }
    
    public void ingresoDia(){
    	ingrs= PrincipalMenuController.cantidadInicial;
    	ingresoDia.setText(Float.toString(ingrs));
    	
    	
    }
    
    public void totalGanancias() throws SQLException {
    	float total=0;
    	float totalSalidas=0;
    	float totalGanancia, gan;
    	String consulta= "select ingreso from MaterialesDeCon.dbo.CorteCaja";
    	conn = con.getConection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			sumaIngresos.add(rs.getFloat(1));
		}
		
		for(int i=0; i<sumaIngresos.size();i++) {
			total+= sumaIngresos.get(i);
		}
		consulta= "select salida from MaterialesDeCon.dbo.CorteCaja";
    	conn = con.getConection();
		st = conn.createStatement();
		rs = st.executeQuery(consulta);
		while(rs.next()) {
			sumaSalidas.add(rs.getFloat(1));
		}
		for(int i=0; i<sumaSalidas.size();i++) {
			totalSalidas+= sumaSalidas.get(i);
		}
		gan= total-totalSalidas;
		totIng.setText(Float.toString(total));
		totSalida.setText(Float.toString(totalSalidas));
		totGan.setText(Float.toString(gan));
		
		totalGanancia= (ingrs+gan);
		totalGanancias.setText(Float.toString(totalGanancia));
		
    }
    
    
    public void cargar() {
    	ingresos.setItems(lista);
    }
    
   
    
    public void setMain(Main main) {
 		this.main=main;
 	}
}
