package empresaMaritima;

import java.util.Set;

public class Buque {
	
	String nombreBuque;
	
	Double gps;
	
	Set<Container> cargas;
	
	EstadoDelBuque estado;		// El estado con el que se encuentra al momento es 'Outbound'
	
	public Buque(String nombre, Double gps, EstadoDelBuque estado) {
		
		this.nombreBuque = nombre;
		this.estado = estado;
		this.gps = gps;
	}
	
	public void addContainer(Container container) {cargas.add(container);}
	
	public void gps(Double nuevalocalizacion) {this.gps= nuevalocalizacion;}
	
	public void setEstado(EstadoDelBuque estadoNuevo) {this.estado = estadoNuevo;}		
	
	
	

	
}

