package pack.MaterialesDeCon.Model;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrdenVenta {
	private final StringProperty idVenta ;
	private final IntegerProperty subTotal;
	private final IntegerProperty total;
	private final FloatProperty pago;
	private final FloatProperty cambio;
	private final ObjectProperty<LocalDate> fecha;
	
	public OrdenVenta(){
		this(null,null,0,0,0,0,null);
	}	
	
	 public OrdenVenta(String idVenta, String nombreProducto, int subTotal, int total, float pago, float cambio, LocalDate fecha) {
				
				this.idVenta = new SimpleStringProperty(idVenta);
				this.subTotal = new SimpleIntegerProperty(subTotal);
				this.total = new SimpleIntegerProperty(total);
				this.pago = new SimpleFloatProperty(pago);
				this.cambio = new SimpleFloatProperty(cambio);
				this.fecha = new SimpleObjectProperty<LocalDate>();
		
			}

		//
		public StringProperty getFolioTicket() {
			return idVenta;
		}
		
		public String getFolioTicketProperty() {
			return idVenta.get();
		}
		
		public void setFolioTicket(String folioTicket) {
			this.idVenta.set(folioTicket);
		}
		//
		public IntegerProperty getSubTotal() {
			return subTotal;
		}
		
		public int getSubTotalProperty() {
			return subTotal.get();
		}
		
		public void setSubTotal(int subTotal) {
			this.subTotal.set(subTotal);
		}
		//
		public IntegerProperty getTotal() {
			return total;
		}
		
		public int getTotalProperty() {
			return total.get();
		}
		
		public void setTotal(int total) {
			this.total.set(total);
		}
		//
		public FloatProperty getPago() {
			return pago;
		}
		
		public float getPagoProperty() {
			return pago.get();
		}
		
		public void setPago(float pago) {
			this.pago.set(pago);
		}
		//
		public FloatProperty getCambio() {
			return cambio;
		}
		
		public float getCambioProperty() {
			return cambio.get();
		}
		
		public void setCambio(float cambio) {
			this.cambio.set(cambio);
		}
		
		public ObjectProperty<LocalDate> getFecha() {
			return fecha;
		}
		
		public void setFecha(LocalDate fecha) {
			this.fecha.set(fecha);
		}
}
