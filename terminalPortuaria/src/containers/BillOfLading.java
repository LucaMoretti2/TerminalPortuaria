package containers;

/*
 Representa el Bill of Lading asociado a un container.
Contiene la información esencial de la carga:
- Tipo de producto transportado
- Peso total de la carga
- Consignee (importador) responsable de recibirla
 Este documento modela la información comercial vinculada al container y se
utiliza para identificar el contenido y al destinatario de la mercancía.
 */
import actores.Consignee;


public class BillOfLading {
	
	String tipoDeProducto;
	double peso;
	Consignee importador;
	
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
