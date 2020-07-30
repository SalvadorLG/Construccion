package pack.MaterialesDeCon.Model;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
	private IntegerProperty id;
	private StringProperty folio;
	private IntegerProperty cantidad;
	private FloatProperty subTotal;
	private FloatProperty total;
	private FloatProperty pago;
	private FloatProperty cambio;

	public Ticket(){
		this(0,null,0,0,0,0,0);
	}
	
	public Ticket(int id, String folio, int cantidad, float subTotal, float total, float pago, float cambio){
		this.id =  new SimpleIntegerProperty(id);
		this.folio = new SimpleStringProperty(folio);
		this.cantidad =  new SimpleIntegerProperty(cantidad);
		this.subTotal = new SimpleFloatProperty(subTotal);
		this.total = new SimpleFloatProperty(total);
		this.pago = new SimpleFloatProperty(pago);
		this.cambio = new SimpleFloatProperty(cambio);
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

	public IntegerProperty getCantidad() {
		return cantidad;
	}
	
	public int getCantidadProperty() {
		return cantidad.get();
	}

	public FloatProperty getSubTotal() {
		return subTotal;
	}
	
	public float getSubTotalProperty() {
		return subTotal.get();
	}

	public FloatProperty getTotal() {
		return total;
	}
	
	public float getTotalProperty() {
		return total.get();
	}

	public FloatProperty getPago() {
		return pago;
	}
	
	public float getPagoProperty() {
		return pago.get();
	}

	public FloatProperty getCambio() {
		return cambio;
	}
	
	public float getCambioProperty() {
		return cambio.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}

	public void setFolio(String producto) {
		this.folio.set(producto);
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad.set(cantidad);
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal.set(subTotal);
	}

	public void setTotal(Float total) {
		this.total.set(total);
	}

	public void setPago(Float pago) {
		this.pago.set(pago);
	}

	public void setCambio(Float cambio) {
		this.cambio.set(cambio);
	}	
}
