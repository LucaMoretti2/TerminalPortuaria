package containers;

import java.time.LocalDateTime;

//Representa un contenedor Tanque, utilizado para transportar líquidos

public class Tanque extends Container {

	String tipoDeLiquido; //sustancia transportada en el tanque
	double capacidadDeLitros; //volumen máximo del contenedor
	BillOfLading bl;
	TipoCarga tipoPermitido;
	
	
	public Tanque(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro, String tipoDeLiquido, double capacidadDeLitros) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
		this.tipoDeLiquido = tipoDeLiquido;
		this.capacidadDeLitros = capacidadDeLitros;
		this.tipo = "Tanque";
		this.tipoPermitido = TipoCarga.LIQUIDA;
	}
	
	public double getCapacidadDeLitros(){
		return capacidadDeLitros;
	}
	
	public String getTipoDeLiquido() {
		return tipoDeLiquido;
	}
	
	public BillOfLading getBl() {
		return bl;
	}
	
	@Override
	public String toString() {
	    return idContainer;
	}
	public void setBl(BillOfLading bl) {
		if (!aceptaCarga(bl)) {
	        throw new IllegalArgumentException("Tanque no puede transportar carga de tipo: " + bl.getTipo());
	    }
	    this.bl = bl;
    }


	//simula la inspección obligatoria del tanque en una fecha determinada.
	public void revisarDiaria(LocalDateTime fecha) {
		 
	    System.out.println("Revisión realizada el " + fecha);
	}

	@Override
	public TipoCarga getTipoPermitido() {
		// TODO Auto-generated method stub
		return tipoPermitido;
	}

	
		  
}
