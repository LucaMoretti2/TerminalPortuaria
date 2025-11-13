package empresaMaritima;

//Representa un buque portacontenedores dentro de la operatoria marítima.


import java.util.*;

import containers.Container;
import estadosBuque.EstadoDelBuque;
import reportes.ReporteVisitable;
import reportes.ReporteVisitor;

public class Buque  implements ReporteVisitable{
	
	public String nombreBuque;
	
	private Double gps;
	
	private Set<Container> cargas;
	
	EstadoDelBuque estado;		// El estado con el que se encuentra al momento es 'Outbound' e implementa el patrón State,
	
	TerminalGestionada terminalDestino;
	
	TerminalGestionada terminalOrigen;
	
	public Buque(String nombre, Double gps, EstadoDelBuque estado) {
		
		this.nombreBuque = nombre;
		this.estado = estado;
		this.gps = gps;
	}
// agrega contenedores al buque
	public void addContainer(Container container) {getCargas().add(container);}
//actualiza coordenadas y delega al estado la logica correspondiente (patrón State)
	public void actualizarPosicion(Buque buque, Double gps) {
		this.gps = gps;
		estado.actualizarPosicion(this, gps, terminalDestino);
	}
	
	public EstadoDelBuque getEstadoBuque() { return this.estado;}
// permite cambiar el estado operacional del buque
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
//permite que un ReporteVisitor procese tanto el buque
//como cada uno de los contenedores que transporta, facilitando la
//generación de reportes sin acoplar la logica a la clase Buque.
	public void accept(ReporteVisitor visitor) {
        visitor.visit(this);
        for (Container c : cargas) {
            c.accept(visitor); 
        }
	}
	
}

