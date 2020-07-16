package pack.MaterialesDeCon.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Venta;
import pack.MaterialesDeCon.View.VentaController;
import pack.MaterialesDeCon.View.PagoController;

public class OrdenPedidoController {

    @FXML
    private JFXTextField subTotal;

    @FXML
    private JFXTextField totalPagar;

    @FXML
    private JFXButton cancelar;

    @FXML
    private JFXButton guardar;

    @FXML
    private JFXTextField fecha;

    @FXML
    private JFXTextField idCompra;
    @FXML
    private JFXTextField montoIva;

    @FXML
    private JFXTextField pagoCon;

    @FXML
    private JFXTextField cambio;

    @FXML
    private TableView<Venta> tablaTicket;

    @FXML
    private TableColumn<Venta, String> idT;

    @FXML
    private TableColumn<Venta, String> nombreProductoT;

    @FXML
    private TableColumn<Venta, Number> cantidadT;
    
    Main main;
    Connection conn=null;
    Conexion con = new Conexion();
    static String formattedString;
    static float total, cam;
     
    
    public void recibir() {
    	VentaController.carrito();
    	PagoController.efectivoRecibido();
    	PagoController.procesoPago();
    }
    
    public void initialize() throws SQLException {
		agregar();
		idT.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getIdProductoProperty()));
		nombreProductoT.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getNombreProductoProperty()));
		cantidadT.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getCantidadProperty()));
		fecha();
		pagos();
		
	}
    
    public void fecha() {
        LocalDate localDate = LocalDate.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / LLLL / yyyy");
		formattedString = localDate.format(formatter);
		fecha.setText(formattedString);
    }
    
    public void pagos(){
    	float price= VentaController.prePago();
         cam= PagoController.procesoPago();
    	float efectivo= PagoController.efectivoRecibido();
    	float monto= (float) (price*0.16);
    	total= price+monto;
    	subTotal.setText(Float.toString(price));
    	montoIva.setText(Float.toString(monto));
    	totalPagar.setText(Float.toString(total));
    	cambio.setText(Float.toString(cam));
    	pagoCon.setText(Float.toString(efectivo));
    	
    	String consulta="INSERT INTO MaterialesDeCon.dbo.OrdenVenta(fecha,totalPagar)" + "values (?,?)";
	   	 try {
				conn = con.getConection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("no se pudo");
			}
	   	try {
	   		PreparedStatement a = conn.prepareStatement(consulta);
	   		a.setString(1, formattedString);
	   		a.setFloat(2, total);
	   		
	   		a.execute();
	   		System.out.println("si pude");
	   	}catch(Exception e) {
	   		System.out.println("no se puso perro");
	   	}
	   	
		caja();
    }
    
    public void caja() {
    	String consulta="INSERT INTO MaterialesDeCon.dbo.CorteCaja(ingreso,fecha,salida)" + "values (?,?,?)";
	   	 try {
				conn = con.getConection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("no se pudo");
			}
	   	try {
	   		PreparedStatement a = conn.prepareStatement(consulta);
	   		a.setFloat(1, Float.parseFloat(pagoCon.getText()));
	   		a.setString(2, formattedString);
	   		a.setFloat(3, cam);
	   		
	   		a.execute();
	   		System.out.println("si pude");
	   	}catch(Exception e) {
	   		System.out.println("no se puso perro");
	   	}
    	
    }
    
    public void agregar() {
    	tablaTicket.setItems(VentaController.carrito());
	}
    
    @FXML
    /*public void cargarMenu(ActionEvent e) {
    	VentaController.carrito().clear();
    	VentaController.subTotal=0;
    	Stage s = (Stage)guardar.getScene().getWindow();
    	s.close();
    	main.loadMenu();
    }*/
    
    
    public void setMain(Main main) {
		this.main=main;
	}

}

