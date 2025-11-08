package empresaMaritima;

import java.time.LocalDateTime;
import java.util.List;


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
	
	public long getDuracionTotalHoras() {
		return circuito.calcularTiempoTotalHoras();
	}
	
	public LocalDateTime calcularFechaArribo(TerminalGestionada destino) {
		long horas = circuito.calcularDuracionHasta(destino);
		return fechaInicio.plusHours(horas);
	}
	
	public double calcularCostoViaje() {
		return circuito.calcularPrecioTotal();
	}
	
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
	
	public LocalDateTime getFechaINicio() {
		return fechaInicio;
	}
	
	public LocalDateTime getFechaArriboDestino() {
		return fechaArriboDestino;
	}
	
	
}
