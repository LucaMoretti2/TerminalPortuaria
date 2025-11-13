package containers;

import actores.Consignee;


public class BillOfLading {
//Contiene la información esencial de la carga:
	
	String tipoDeProducto;
	double peso;
	Consignee importador;

//Información comercial vinculada al container y se
//utiliza para identificar el contenido y al destinatario de la mercancía.
	
	public BillOfLading(String tipoDeProducto, double peso, Consignee importador) {
		this.tipoDeProducto = tipoDeProducto;
		this.peso = peso;
		this.importador = importador;
	}
	
	public String getTipoDeProducto() { 
		return tipoDeProducto; 
	}
	
    public double getPeso() { 
    	return peso; 
    }
    
    public Consignee getImportador() {
    	return importador; 
    }
    
	
}
