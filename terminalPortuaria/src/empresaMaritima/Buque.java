package empresaMaritima;


import java.util.*;

import containers.Container;
import estadosBuque.EstadoDelBuque;

public class Buque {
	
	String nombreBuque;
	
	Double gps;
	
	Set<Container> cargas;
	
	EstadoDelBuque estado;		// El estado con el que se encuentra al momento es 'Outbound'
	
	List<Observer> observadores = new ArrayList<>();
	
	public Buque(String nombre, Double gps, EstadoDelBuque estado) {
		
		this.nombreBuque = nombre;
		this.estado = estado;
		this.gps = gps;
	}
	
	public void addContainer(Container container) {cargas.add(container);}
	
	public void gps(Double nuevalocalizacion) {this.gps= nuevalocalizacion;}
	
	public EstadoDelBuque getEstadoBuque() { return this.estado;}
	
	public void setEstado(EstadoDelBuque estadoNuevo) {
	
	System.out.println("Cambiando estado:" + estadoNuevo.nombreEstado());
	this.estado = estadoNuevo;
	}
	
	//Patron State
	
	public void actualizarPosicion(Double gps) { estado.actualizarPosicion(this,gps);}
	
	public void iniciarTrabajo() { estado.iniciarTrabajo(this);}
	
	public void finalizarTrabajo() {estado.finalizarTrabajo(this);}
	
	public void notificar() { estado.notificar();}
	
	public void realizarPagoNecesarios() { estado.realizarPagosNecesarios();}
	
	
	//Patron Observer
	
	public void agregarObservador(Observer obs) { observadores.add(obs);}
	
	public void eliminarObservador(Observer obs) { observadores.remove(obs);}
	
	public void notificarEvento() {
		for (Observer obs : observadores) { obs.actualizarEvento(this);} //posiblemente necesite de mas parametros
	
	}
}

