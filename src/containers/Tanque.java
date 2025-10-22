package containers;

import java.time.LocalDateTime;

public class Tanque extends Container {

	String tipoDeLiquido;
	double capacidadDeLitros;
	BillOfLading bl;
	
	public Tanque(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro, String tipoDeLiquido, double capacidadDeLitros) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
		// TODO Auto-generated constructor stub
		this.tipoDeLiquido = tipoDeLiquido;
		this.capacidadDeLitros = capacidadDeLitros;
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
	
	public void revisarDiaria(LocalDateTime fecha) {
		 
	    System.out.println("Revisión realizada el " + fecha);
	}
}
