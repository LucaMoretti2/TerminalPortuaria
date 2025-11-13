package empresaMaritima;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import containers.Container;
//Representa un viaje maritimo realizado por un buque dentro de un circuito  específico. 
//Cada viaje tiene una fecha de inicio, un buque asignado,
//una naviera responsable y una ruta definida por un CircuitoMaritimo.

public class Viaje {

	int idViaje;
	LocalDateTime fechaInicio;
	LocalDateTime fechaArriboDestino;
	Buque buque;
	Naviera naviera;
	CircuitoMaritimo circuito;
	TerminalGestionada terminalOrigen;
	TerminalGestionada terminalDestino;
	
	public Viaje(int idViaje, LocalDateTime fechaInicio, Buque buque, Naviera naviera, CircuitoMaritimo circuito, TerminalGestionada origen, TerminalGestionada destino) {
		this.idViaje = idViaje;
		this.fechaInicio = fechaInicio;
		this.buque = buque;
		this.naviera = naviera;
		this.circuito = circuito;
		this.terminalOrigen = origen;
		this.terminalDestino = destino;
	}
//devuelve la duración total sumando todos los tramos del circuito
	public long getDuracionTotalHoras() {
		return circuito.calcularTiempoTotalHoras();
	}
//calcula la fecha estimada de llegada a una terminal destino
	public LocalDateTime calcularFechaArribo(TerminalGestionada destino) {
		long horas = circuito.calcularDuracionHasta(destino);
		return fechaInicio.plusHours(horas);
	}
//suma los precios de todos los tramos del circuito
	public double calcularCostoViaje() {
		return circuito.calcularPrecioTotal();
	}
// devuelve la lista completa de tramos del circuito
	public List<Tramo> obtenerTramos() {
		return circuito.getTramos();
	}
	
	public Buque getBuque() {
		return buque;
	}
	
	public CircuitoMaritimo getCircuito() {
		return circuito;
	}
	
	public Naviera getNaviera() {
		return naviera;
	}
	

	public LocalDateTime getArribo() {
		return fechaArriboDestino;
	}
	
	public LocalDateTime getPartida() {
		return fechaInicio;
	}
//devuelve los contenedores cargados en el buque
	public Set<Container> getContenedores(){
		return buque.getCargas();
	}
	
	
}
