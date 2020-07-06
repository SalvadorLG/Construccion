package pack.MaterialesDeCon.View;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import pack.MaterialesDeCon.Main;

public class PagoController {

    @FXML
    private JFXTextField efectivo;

    @FXML
    private JFXButton siguiente;
    @FXML
    private JFXTextField tot;
    
    static float cambio=0;
    static float recibir;
    static float total;
    Main main;
    
    @FXML
    public void pago(ActionEvent e) {
    	float price= VentaController.prePago();
    	float monto= (float) (price*0.16);
    	 total= price+monto;
    	
    	tot.setText(Float.toString(total));
    	recibir= Float.parseFloat(efectivo.getText());
    	
    	
    	if(recibir<total) {
    		JOptionPane.showMessageDialog(null, "Efectivo insufiente para pagar.\nIngrese de nuevo");
    		Stage s = (Stage)siguiente.getScene().getWindow();
        	s.close();
        	s.show();
    	}else {
    		cambio=recibir-total;
    		Stage s = (Stage)siguiente.getScene().getWindow();
        	s.close();
        	main.cagarOrdenVenta();
    	}
    		
    }
    
    public static Float procesoPago(){
    	return cambio;
    }
    
    public static Float efectivoRecibido() {
    	return recibir ;
    }
    
    public void setMain(Main main) {
 		this.main=main;
 	}

}

