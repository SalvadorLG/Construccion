package pack.MaterialesDeCon.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categoria {

	private  StringProperty nombreCategoria;
	private  StringProperty id;
	private  StringProperty descripcion;
	
	public Categoria(String nombreCategoria, String id, String descripcion){
		this.nombreCategoria= new SimpleStringProperty(nombreCategoria);
		this.id= new SimpleStringProperty(id);
		this.descripcion= new SimpleStringProperty(descripcion);
	}
	
	public String getCategoria() {
		return nombreCategoria.get();
	}
	public void setCategoria(String nombreCategoria) {
		this.nombreCategoria = new SimpleStringProperty(nombreCategoria);
	}
	public StringProperty CategoriaProperty() {
		return nombreCategoria;
	}
	//Metodos atributo: id
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}
	public StringProperty IdProperty() {
		return id;
	}
	//Metodos atributo: descripcion
	public String getDescripcion() {
		return descripcion.get();
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = new SimpleStringProperty(descripcion);
	}
	public StringProperty DescripcionProperty() {
		return descripcion;
	}
	
	public String toString() {
		return nombreCategoria.get();
	}

}
