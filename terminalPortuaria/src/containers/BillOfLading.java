package containers;

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
