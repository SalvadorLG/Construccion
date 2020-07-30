package pack.MaterialesDeCon.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    Connection con=null;
    private PreparedStatement ps;
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
	   		 con = Conexion.getConection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("no se pudo");
			}
	   	try {
	   		PreparedStatement a = con.prepareStatement(consulta);
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
	   		con = Conexion.getConection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("no se pudo");
			}
	   	try {
	   		PreparedStatement a = con.prepareStatement(consulta);
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
    public void finalizarVenta() throws SQLException {
    	saveTicket();
    	Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Compra realizada");
		alerta.setContentText("El ticket se ha guardado");
		alerta.initStyle(StageStyle.UTILITY);
		alerta.setHeaderText(null);
		alerta.showAndWait();
		cerrar();
		
    }
    
    ArrayList<String> precioIND = new ArrayList<String>();
    ArrayList<String> productos = new ArrayList<String>(); 
    ArrayList<String> cantidadIND = new ArrayList<String>();
    ArrayList<String> folios = new ArrayList<String>();
    
    public void saveTicket() throws SQLException {
    	ObservableList<Venta> listaCompra =  VentaController.carrito();
    	int cantidadTotal = 0;
    	for(int i = 0; i < listaCompra.size(); i++) {
    		String product = listaCompra.get(i).getNombreProductoProperty();
    		System.out.println(listaCompra.get(i).getNombreProductoProperty());
    		productos.add(product);
    		
    		String cantInd = String.valueOf(listaCompra.get(i).getCantidadProperty());
    		System.out.println(cantInd);
    		cantidadIND.add(cantInd);
    		cantidadTotal = cantidadTotal + listaCompra.get(i).getCantidadProperty();
    		
    		String priceInd = String.valueOf(listaCompra.get(i).getPrecioProperty());
    		System.out.println(priceInd);
    		precioIND.add(priceInd);
    	}
    	
    	System.out.println(productos);
    	System.out.println(cantidadIND);
    	System.out.println(precioIND);
    	String folio = verificarFolio();
    	System.out.println("folio: "+folio);
    	System.out.println(cantidadTotal);
    	System.out.println("subTotal: " + subTotal.getText());
    	System.out.println("total + IVA: " + totalPagar.getText());
		System.out.println("pagoCon: " + pagoCon.getText());
		System.out.println("cambio: " + cambio.getText());
		
		String query = "INSERT INTO MaterialesDeCon.dbo.Ticket VALUES"
				+ " ('"+folio+"','"+cantidadTotal+"','"+subTotal.getText()+"','"+totalPagar.getText()+"',"
				+ "'"+pagoCon.getText()+"','"+cambio.getText()+"')";
		
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		comprados(folio, productos, cantidadIND, precioIND);
    }
    
    public String verificarFolio() {
    	Random r = new Random();
    	int num = r.nextInt(10000)+1000;
    	String folio = String.valueOf(num);
    	
    	String query = "select folio from dbo.Ticket";
    	con = Conexion.getConection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				folios.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < folios.size(); i++) {
			if(folios.get(i).equals(folio)) {
				System.out.println("Folio Repetido");
				verificarFolio();
			}
		}
		
		return folio;
    }
   
    public void cerrar() {
    	Stage s = (Stage)guardar.getScene().getWindow();
    	s.close();
    }

    public void comprados(String folio, ArrayList productos, ArrayList cantidadInd, ArrayList precioIND) throws SQLException {
    	
    	for(int p = 0; p < productos.size(); p++) {
    		String query = "INSERT INTO MaterialesDeCon.dbo.Comprados VALUES"
    				+ " ('"+folio+"','"+productos.get(p)+"','"+cantidadInd.get(p)+"','"+precioIND.get(p)+"')";
    		ps = con.prepareStatement(query);
    		ps.executeUpdate();
    	}
    }
    
    public void setMain(Main main) {
		this.main=main;
	}

}

