package containers;

import java.time.LocalDateTime;
/*
Representa un contenedor Tanque, utilizado para transportar líquidos
 Extiende la clase abstracta Container e incorpora datos específicos:
 - tipoDeLiquido: sustancia transportada en el tanque
 - capacidadDeLitros: volumen máximo del contenedor
- Bill of Lading asociado a la carga líquida

Funcionalidades adicionales:
- revisarDiaria(): simula la inspección obligatoria del tanque en una fecha determinada.
 */
public class Tanque extends Container {

	String tipoDeLiquido;
	double capacidadDeLitros;
	BillOfLading bl;
	
	public Tanque(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro, String tipoDeLiquido, double capacidadDeLitros) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
		// TODO Auto-generated constructor stub
		this.tipoDeLiquido = tipoDeLiquido;
		this.capacidadDeLitros = capacidadDeLitros;
		this.tipo = "Tanque";
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
