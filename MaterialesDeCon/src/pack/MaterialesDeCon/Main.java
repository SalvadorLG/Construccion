package pack.MaterialesDeCon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pack.MaterialesDeCon.View.CorteCajaController;
//import pack.MaterialesDeCon.View.EditarProductoController;
import pack.MaterialesDeCon.View.ListaProductosController;
import pack.MaterialesDeCon.View.ListaProveedoresController;
import pack.MaterialesDeCon.View.ListaUsuariosController;
import pack.MaterialesDeCon.View.LoginController;
import pack.MaterialesDeCon.View.OrdenPedidoController;
import pack.MaterialesDeCon.View.PagoController;
import pack.MaterialesDeCon.View.PrincipalMenuController;
import pack.MaterialesDeCon.View.RegistroProductoController;
import pack.MaterialesDeCon.View.VentaController;
 

public class Main extends Application {
	Stage menu= new Stage() ;
	Stage login= new Stage();
	Stage registroProductos= new Stage();
	Stage verCaja= new Stage();
	Stage verlistaProductos= new Stage();
	Stage verlistaProveedores= new Stage();
	Stage verVenta= new Stage();
	Stage verOrdenPedido=new Stage();
	Stage verPago= new Stage();
	Stage listaUsuarios = new Stage();
	public static String categoria = "";
	public static String nick = "", pass = "";
	public static String idUsuario = "", name = "", puesto = "", apellido = "";


	@Override
	public void start(Stage primaryStage) {
		loadLogin();
	}
	
	public void loadMenu(String idUsuario, String name,String apellido,String puesto) {
		try {
			login.close();
			this.idUsuario = idUsuario;
			this.name = name;
			this.apellido = apellido;
			this.puesto = puesto;
			
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/PrincipalMenu.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			PrincipalMenuController controlador = loader.getController();
			controlador.setMain(this);
			
			menu.setScene(scene);
			menu.show();
			
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void loadLogin() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/LoginDeCon.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			LoginController controlador = loader.getController();
			controlador.setMain(this);
			
			login.setScene(scene);
			login.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cagarRegistroProducto() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/RegistroProducto.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			RegistroProductoController controlador= loader.getController();
			controlador.setMain(this);
			registroProductos.setScene(scene);
			registroProductos.show();
			
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public void cagarEditarProducto() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/EditarProducto.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			EditarProductoController controlador= loader.getController();
			controlador.setMain(this);
			registroProductos.setScene(scene);
			registroProductos.show();
			
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}*/	
	
	public void cargarListaProveedores() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/ListaProveedores.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			ListaProveedoresController controlador= loader.getController();
			controlador.setMain(this);
			verlistaProveedores.setScene(scene);
			verlistaProveedores.show();
			
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarListaMateriales(String Category) {
		this.categoria = Category;
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/ListaMateriales.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			ListaProductosController controlador= loader.getController();
			controlador.setMain(this);
			verlistaProductos.setScene(scene);
			verlistaProductos.show();
			
			
		} catch  (IOException e) {
			e.printStackTrace();
	}
	}
	
	public void cargarListaUsuarios() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/ListaUsuarios.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			ListaUsuariosController controlador= loader.getController();
			controlador.setMain(this);
			listaUsuarios.setScene(scene);
			listaUsuarios.show();
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarVenta() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/Venta.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			VentaController controlador= loader.getController();
			controlador.setMain(this);
			verVenta.setScene(scene);
			verVenta.show();
			
		} catch  (IOException e) {
			e.printStackTrace();
	}
	}
	
	public void cagarOrdenVenta(){
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/OrdenVenta.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			OrdenPedidoController controlador= loader.getController();
			controlador.setMain(this);
			verOrdenPedido.setScene(scene);
			verOrdenPedido.show();
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cagarPago() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/Pago.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			PagoController controlador= loader.getController();
			controlador.setMain(this);
			verPago.setScene(scene);
			verPago.show();
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cagarCorteCaja() {
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("View/CorteCaja.fxml"));
			AnchorPane root=(AnchorPane)loader.load();
			Scene scene= new Scene(root);
			CorteCajaController controlador= loader.getController();
			controlador.setMain(this);
			verCaja.setScene(scene);
			verCaja.show();
			
		} catch  (IOException e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
