package empresaMaritima;


import java.util.*;

import containers.Container;
import estadosBuque.EstadoDelBuque;
import reportes.ReporteVisitable;
import reportes.ReporteVisitor;

public class Buque  implements ReporteVisitable{
	
	public String nombreBuque;
	
	private Double gps;
	
	private Set<Container> cargas;
	
	EstadoDelBuque estado;		// El estado con el que se encuentra al momento es 'Outbound'
	
	TerminalGestionada terminalDestino;
	
	TerminalGestionada terminalOrigen;
	
	public Buque(String nombre, Double gps, EstadoDelBuque estado) {
		
		this.nombreBuque = nombre;
		this.estado = estado;
		this.gps = gps;
	}
	
	public void addContainer(Container container) {getCargas().add(container);}
	
	public void actualizarPosicion(Buque buque, Double gps) {
		this.gps = gps;
		estado.actualizarPosicion(this, gps, terminalDestino);
	}
	
	public EstadoDelBuque getEstadoBuque() { return this.estado;}
	
	public void setEstado(EstadoDelBuque estadoNuevo) {
	
	System.out.println("Cambiando estado:" + estadoNuevo.nombreEstado());
	this.estado = estadoNuevo;
	}
	
	
	public String getNombre() {
		return nombreBuque;
	}

	public Set<Container> getCargas() {
		return cargas;
	}

	public void setCargas(Set<Container> cargas) {
		this.cargas = cargas;
	}

	public Double getGps() {
		return gps;
	}
	
	public void accept(ReporteVisitor visitor) {
        visitor.visit(this);
        for (Container c : cargas) {
            c.accept(visitor); 
        }
	}
	
}

