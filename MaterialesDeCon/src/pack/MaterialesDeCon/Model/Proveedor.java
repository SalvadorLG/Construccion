package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {
	
	private StringProperty idProveedor;
	private StringProperty contactoReferencia;
	private StringProperty nombreEmpresa;
	private StringProperty direccion;
	private StringProperty numeroTelefonico;
	private StringProperty codigoPostal;
	private StringProperty ciudad;
	
	public Proveedor(){
		this(null,null,null,null,null,null,null);
	}
	
	public Proveedor(String idProveedor, String contactoReferencia,String nombreEmpresa,String direccion,String numeroTelefonico,String codigoPostal,String ciudad) {
		this.idProveedor = new SimpleStringProperty(idProveedor);
		this.contactoReferencia = new SimpleStringProperty(contactoReferencia);
		this.nombreEmpresa = new SimpleStringProperty(nombreEmpresa);
		this.direccion = new SimpleStringProperty(direccion);
		this.numeroTelefonico = new SimpleStringProperty(numeroTelefonico);
		this.codigoPostal = new SimpleStringProperty(codigoPostal);
		this.ciudad = new SimpleStringProperty(ciudad);
	}

	public StringProperty getIdProveedor() {
		return idProveedor;
	}

	public StringProperty getContactoReferencia() {
		return contactoReferencia;
	}

	public StringProperty getNombreEmpresa() {
		return nombreEmpresa;
	}

	public StringProperty getDireccion() {
		return direccion;
	}

	public StringProperty getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public StringProperty getCodigoPostal() {
		return codigoPostal;
	}

	public StringProperty getCiudad() {
		return ciudad;
	}
	
	
	
	
	public String getIdProveedorProperty() {
		return idProveedor.get();
	}

	public String getContactoReferenciaProperty() {
		return contactoReferencia.get();
	}

	public String getNombreEmpresaProperty() {
		return nombreEmpresa.get();
	}

	public String getDireccionProperty() {
		return direccion.get();
	}

	public String getNumeroTelefonicoProperty() {
		return numeroTelefonico.get();
	}

	public String getCodigoPostalProperty() {
		return codigoPostal.get();
	}

	public String getCiudadProperty() {
		return ciudad.get();
	}
	
	
	
	

	public void setIdProveedor(StringProperty idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void setContactoReferencia(StringProperty contactoReferencia) {
		this.contactoReferencia = contactoReferencia;
	}

	public void setNombreEmpresa(StringProperty nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setDireccion(StringProperty direccion) {
		this.direccion = direccion;
	}

	public void setNumeroTelefonico(StringProperty numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public void setCodigoPostal(StringProperty codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setCiudad(StringProperty ciudad) {
		this.ciudad = ciudad;
	}
	
}
