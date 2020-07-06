package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrdenEnvio {
	
	private final StringProperty idEnvio;
	private final StringProperty idVenta;
	private final StringProperty direccion;
	private final StringProperty codigoPostal;
	private final StringProperty referencia;
	private final StringProperty numeroTelefonico;
	private final StringProperty nombreCliente;
	private final StringProperty ciudad;
	
	public OrdenEnvio(String idEnvio, String idVenta, String direccion, String codigoPostal, String refencia, String numeroTelefonico, String nombreCliente, String ciudad) {
		this.idEnvio= new SimpleStringProperty(idEnvio);
		this.idVenta= new SimpleStringProperty(idVenta);
		this.direccion= new SimpleStringProperty(direccion);
		this.codigoPostal= new SimpleStringProperty(codigoPostal);
		this.referencia= new SimpleStringProperty(refencia);
		this.numeroTelefonico= new SimpleStringProperty(numeroTelefonico);
		this.nombreCliente= new SimpleStringProperty(nombreCliente);
		this.ciudad= new SimpleStringProperty(ciudad);
		
	}
	
	
	public StringProperty getIdEnvio() {
		return idEnvio;
	}
	
	public String geIdEnvio() {
		return idEnvio.get();
	}
	
	public void setIdEnvio(String idEnvio) {
		this.idEnvio.set(idEnvio);
	}
	
	public StringProperty getIdVenta() {
		return idVenta;
	}
	
	public String getIdVentaProperty() {
		return idVenta.get();
	}
	
	public void setIdVenta(String idVenta) {
		this.idVenta.set(idVenta);
	}
	
	public StringProperty getDireccion() {
		return direccion;
	}
	
	public String getDireccionProperty() {
		return direccion.get();
	}
	
	public void setDireccion(String direccion) {
		this.direccion.set(direccion);
	}
	
	public StringProperty getCodigoPostal() {
		return codigoPostal;
	}
	
	public String getCodigoPostalProperty() {
		return codigoPostal.get();
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal.set(codigoPostal);
	}
	
	public StringProperty getReferencia() {
		return referencia;
	}
	
	public String getReferenciaProperty() {
		return referencia.get();
	}
	
	public void setReferencia(String referencia) {
		this.referencia.set(referencia);
	}
	
	public StringProperty getNumeroTelefonico() {
		return numeroTelefonico;
	}
	
	public String getNumeroTelefonicoProperty() {
		return numeroTelefonico.get();
	}
	
	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico.set(numeroTelefonico);
	}
	
	public StringProperty getNombreCliente() {
		return nombreCliente;
	}
	
	public String getNombreClienteProperty() {
		return nombreCliente.get();
	}
	
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente.set(nombreCliente);
	}
	
	public StringProperty getCuidad() {
		return ciudad;
	}
	
	public String getCuidadProperty() {
		return ciudad.get();
	}
	
	public void setCuidad(String cuidad) {
		this.ciudad.set(cuidad);
	}

}
