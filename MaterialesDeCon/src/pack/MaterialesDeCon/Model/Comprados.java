package pack.MaterialesDeCon.Model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comprados {
	
	private IntegerProperty id;
	private StringProperty folio;
	private StringProperty producto;
	private IntegerProperty cantidadIndividual;
	private FloatProperty precioIndividual;
	
	public Comprados() {
		this(0,null,null,0,0);
	}
	
	public Comprados(int id, String folio, String producto, int cantidadIndividual, float precioIndividual) {
		this.id =  new SimpleIntegerProperty(id);
		this.folio = new SimpleStringProperty(folio);
		this.producto = new SimpleStringProperty(producto);
		this.cantidadIndividual =  new SimpleIntegerProperty(cantidadIndividual);
		this.precioIndividual = new SimpleFloatProperty(precioIndividual);
	}

	public IntegerProperty getId() {
		return id;
	}
	
	public int getIdProperty() {
		return id.get();
	}

	public StringProperty getFolio() {
		return folio;
	}
	
	public String getFolioProperty() {
		return folio.get();
	}
	
	public StringProperty getProducto() {
		return producto;
	}
	
	public String getProductoProperty() {
		return producto.get();
	}

	public IntegerProperty getCantidadIndividual() {
		return cantidadIndividual;
	}
	
	public Integer getCantidadIndividualProperty() {
		return cantidadIndividual.get();
	}

	public FloatProperty getPrecioIndividual() {
		return precioIndividual;
	}
	
	public Float getPrecioIndividualProperty() {
		return precioIndividual.get();
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public void setFolio(StringProperty folio) {
		this.folio = folio;
	}

	public void setProducto(StringProperty producto) {
		this.producto = producto;
	}

	public void setCantidadIndividual(IntegerProperty cantidadIndividual) {
		this.cantidadIndividual = cantidadIndividual;
	}

	public void setPrecioIndividual(FloatProperty precioIndividual) {
		this.precioIndividual = precioIndividual;
	}

}
