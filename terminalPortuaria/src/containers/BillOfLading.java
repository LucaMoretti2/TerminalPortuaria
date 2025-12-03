package containers;

import actores.Consignee;


public class BillOfLading {
//Contiene la información esencial de la carga:
	
	String tipoDeProducto;
	double peso;
	Consignee importador;
	TipoCarga tipo;

//Información comercial vinculada al container y se
//utiliza para identificar el contenido y al destinatario de la mercancía.
	
	public BillOfLading(String tipoDeProducto, double d, Consignee consignee, TipoCarga tipo) {
		this.tipoDeProducto = tipoDeProducto;
		this.peso = d;
		this.importador = consignee;
		this.tipo = tipo;
	
		
	}
	
	
	public String toString() {
	    return tipoDeProducto;
	}
	public String getTipoDeProducto() { 
		return tipoDeProducto; 
	}
	
	public TipoCarga getTipo() {
	    return tipo;
	}
	
    public double getPeso() { 
    	return peso; 
    }
    
    public Consignee getImportador() {
    	return importador; 
    }
    
	
}
