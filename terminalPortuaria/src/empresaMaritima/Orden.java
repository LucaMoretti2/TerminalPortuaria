package empresaMaritima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import containers.Container;
import serviciosDeContainer.Servicio;

public abstract class Orden {

	int id;
	Container container;
	Viaje viaje;
	private LocalDateTime fechaDeRegistro;
	List<Servicio> servicios = new ArrayList<>();
	double costoTotal;
	
	public Orden(Container container, Viaje viaje) {
		this.container = container;
		this.viaje = viaje;
		this.fechaDeRegistro= LocalDateTime.now();
		this.servicios = new ArrayList<>();
	}
	
	public void agregarServicio(Servicio servicio) {
		servicios.add(servicio);
	}

	public double calcularCostoServicios(){
		double costoHastaAhora = 0;
		for(Servicio s : servicios) {
			costoHastaAhora += s.calcularCosto(container);
		}
		return costoHastaAhora;
	}
	
	public Viaje getViaje() {
		return viaje;
	}
	
	public Container getContainer() {
		return container;
	}
	
	public abstract double calcularCostoTotal();
	
	public abstract String getResposablePago();

	public LocalDateTime getFechaDeRegistro() {
		return fechaDeRegistro;
	}


	
}
