package pack.MaterialesDeCon.Model;

import java.time.LocalDate;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CorteCaja {

	private final ObjectProperty<LocalDate> fecha;
	private final FloatProperty primeraEntrada;
	private final FloatProperty ingreso;
	private final FloatProperty salida;
	private final FloatProperty totalVentas;
	
	public CorteCaja(LocalDate fecha, float primeraEntrada, float ingreso, float salida, float totalVentas) {
		this.fecha= new SimpleObjectProperty<LocalDate>();
		this.primeraEntrada= new SimpleFloatProperty(primeraEntrada);
		this.ingreso= new SimpleFloatProperty(ingreso);
		this.salida= new SimpleFloatProperty(salida);
		this.totalVentas= new SimpleFloatProperty(totalVentas);
		
	}
	
	
	public ObjectProperty<LocalDate> getFecha(){
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha.set(fecha);
	}
	
	public FloatProperty getPrimeraEntrada() {
		return primeraEntrada;
	}
	
	public float getPrimeraEntradaProperty() {
		return primeraEntrada.get();
	}
	
	public void setPrimeraEntrada(float primeraEntrada) {
		this.primeraEntrada.set(primeraEntrada);
	}
	
	public FloatProperty getIngreso() {
		return ingreso;
	}
	
	public float getIngresoProperty() {
		return ingreso.get();
	}
	
	public void setIngreso(float ingreso) {
		this.ingreso.set(ingreso);
	}
	
	public FloatProperty getSalida() {
		return salida;
	}
	
	public float getSalidaProperty() {
		return salida.get();
	}
	
	public void setSalida(float salida) {
		this.salida.set(salida);
	}
	
	public FloatProperty getTotalVentas() {
		return totalVentas;
	}
	
	public float getTotalVentasProperty() {
		return totalVentas.get();
	}
	
	public void setTotalVentas(float totalVentas) {
		this.totalVentas.set(totalVentas);
	}
	
}
