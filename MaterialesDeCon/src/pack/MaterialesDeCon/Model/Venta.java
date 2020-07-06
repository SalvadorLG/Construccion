package pack.MaterialesDeCon.Model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venta {
	
	private final IntegerProperty cantidad;
	private final FloatProperty precio;
	private final StringProperty nombreProducto;
	private final StringProperty idProducto;
	
	public Venta(){
		this(null,0,0, null);
	}
	
	
	public Venta(String nombreProducto, int cantidad, float precio, String idProducto) {
		this.nombreProducto= new SimpleStringProperty(nombreProducto);
		this.cantidad= new SimpleIntegerProperty(cantidad);
		this.precio= new SimpleFloatProperty(precio);
		this.idProducto= new SimpleStringProperty(idProducto);
	}
	
	public StringProperty getNombreProducto() {
		return nombreProducto;
	}
	
	public String getNombreProductoProperty() {
		return nombreProducto.get();
	}
	
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto.set(nombreProducto);
	}
	
	public IntegerProperty getCantidad() {
		return cantidad;
	}
	
	public int getCantidadProperty() {
		return cantidad.get();
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad.set(cantidad);
	}
	
	public FloatProperty getPrecio() {
		return precio;
	}
	
	public float getPrecioProperty() {
		return precio.get();
	}
	
	public void setPrecio(float precio) {
		this.precio.set(precio);
	}
	
	public StringProperty getIdProducto() {
		return idProducto;
	}
	
	public String getIdProductoProperty() {
		return idProducto.get();
	}
	
	public void setIdProducto(String idProducto) {
		this.idProducto.set(idProducto);
	}
	
	

}
