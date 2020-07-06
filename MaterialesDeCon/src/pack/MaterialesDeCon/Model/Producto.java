package pack.MaterialesDeCon.Model;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {

		
		private final StringProperty idProducto;
		private final StringProperty nombre;
		private final StringProperty idProvedor;
		private final StringProperty idCategoria;
		private final IntegerProperty existencia;
		private final FloatProperty precioUnitario;
		
		public Producto(){
			this(null,null,null,null,0,0);
		}
		
		public Producto(String idProducto, String nombre, String idProvedor, String idCategoria, int existencia, float precioUnitario) {
			this.idProducto= new SimpleStringProperty(idProducto);
			this.nombre= new SimpleStringProperty(nombre);
			this.idProvedor= new SimpleStringProperty(idProvedor);
			this.idCategoria= new SimpleStringProperty(idCategoria);
			this.existencia= new SimpleIntegerProperty(existencia);
			this.precioUnitario= new SimpleFloatProperty(precioUnitario);
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

		public StringProperty getNombre() {
			return nombre;
		}
		public String getNombreoProperty() {
			return nombre.get();
		}
		
		public void setNombre(String nombre) {
			this.nombre.set(nombre);
		}

		public StringProperty getIdProvedor() {
			return idProvedor;
		}
		
		public String getIdProvedorProperty() {
			return idProvedor.get();
		}
		
		public void setIdProvedor(String idProvedor) {
			this.idProvedor.set(idProvedor);
		}

		public StringProperty getIdCategoria() {
			return idCategoria;
		}
		
		public String getIdCategoriaProperty() {
			return idCategoria.get();
		}
		
		public void setIdCategoria(String idCategoria) {
			this.idCategoria.set(idCategoria);
		}

		public IntegerProperty getExistencia() {
			return existencia;
		}
		
		public int getExistenciaProperty() {
			return existencia.get();
		}
		
		public void setExistencia(int existencia) {
			this.existencia.set(existencia);
		}

		public FloatProperty getPrecioUnitario() {
			return precioUnitario;
		}
		
		public float getPrecioUnitarioProperty() {
			return precioUnitario.get();
		}
		
		public void setPrecioUnitario(float precioUnitario) {
			this.precioUnitario.set(precioUnitario);
		}

		
		
		
		

}

