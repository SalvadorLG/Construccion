package pack.MaterialesDeCon.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pack.MaterialesDeCon.Main;
import pack.MaterialesDeCon.Model.Comprados;
import pack.MaterialesDeCon.Model.Conexion;
import pack.MaterialesDeCon.Model.Producto;
import pack.MaterialesDeCon.Model.Ticket;

public class ListaTicketsController {
	ObservableList<Ticket> listaTicket = FXCollections.observableArrayList();
	ObservableList<Comprados> listaComprados = FXCollections.observableArrayList();
	Main main;
	Connection con=null;
    private PreparedStatement ps;
    Ticket ticket;

    @FXML
    private AnchorPane listaTickets, datosTicket; 

    @FXML
    private TableView<Ticket> tablaTickets;

    @FXML
    private TableColumn<Ticket, String> folio;
    
    @FXML
    private TableColumn<Ticket, Number> cantidad;

    @FXML
    private TableColumn<Ticket, Number> subTotal, totalIVA, pago, cambio;
    
    
    @FXML
    private TableView<Comprados> tablaDatosTickets;
    
    @FXML
    private TableColumn<Comprados, String> folioCompras, productoCompras;
    
    @FXML
    private TableColumn<Comprados, Number> cantidadCompras, precioCompras;
    
    @FXML
    private JFXButton salir;
    @FXML
    private JFXButton ver;
    
    static int c;
    static String folioActual = "";

    @FXML
	public void initialize() throws SQLException {
		agregar();
		select();
		
    	folio.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getFolioProperty()));
		cantidad.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getCantidadProperty()));
		subTotal.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getSubTotalProperty()));
		totalIVA.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getTotalProperty()));
		pago.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getPagoProperty()));
		cambio.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getCambioProperty()));
		listaTickets.setVisible(true);
		
		seleccion();
	}
    
    @FXML
    public void dataTicket() throws SQLException {
    	add();
    	selectComprados();
    	folioCompras.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getFolioProperty()));
		productoCompras.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getProductoProperty()));
		cantidadCompras.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getCantidadIndividualProperty()));
		precioCompras.setCellValueFactory(c-> new SimpleFloatProperty(c.getValue().getPrecioIndividualProperty()));
    }

	public void select() throws SQLException {
		String consulta = "select * from MaterialesDeCon.dbo.Ticket";
		con = Conexion.getConection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			listaTicket.add(new Ticket(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7)));
		}
	}
	
	public void selectComprados() throws SQLException {
		String consulta = "select * from MaterialesDeCon.dbo.Comprados where folio = '" + folioActual + "'";
		con = Conexion.getConection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(consulta);
		while(rs.next()) {
			listaComprados.add(new Comprados(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5)));
		}
	} 
	
	public void seleccion() throws SQLException{
		tablaTickets.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ticket>() {

			@Override
			public void changed(ObservableValue<? extends Ticket> observable, Ticket oldValue, Ticket newValue) {
				if(newValue != null) {
					c = newValue.getIdProperty();
					folioActual = newValue.getFolioProperty();
					System.out.println("Folio Actual: "+folioActual);
				}
			}
		});
	}
	
	@FXML
	public void MostrarDatosTicket() throws SQLException{
		if(!folioActual.isEmpty()) {
			ocultar();
			dataTicket();
			datosTicket.setVisible(true);
		}else {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("");
			alerta.setContentText("No ha seleccionado un Ticket");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
	}
	
	public void agregar() {
		tablaTickets.setItems(listaTicket);
	}
	
	public void add() {
		tablaDatosTickets.setItems(listaComprados);
	}

	public void setMain(Main main) {
			this.main=main;
	}
	
	@FXML
	public void regresar() {
		ocultar();
		listaComprados.clear();
		listaTickets.setVisible(true);
	}
	
	private void ocultar() {
		listaTickets.setVisible(false);
		datosTicket.setVisible(false);
	}

	@FXML
	public void salir(ActionEvent event) {
		Stage s = (Stage)salir.getScene().getWindow();
    	s.close();
	}
}

