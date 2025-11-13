package ordenes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Viaje;
import serviciosDeContainer.Servicio;

/*
 Clase abstracta que representa una Orden dentro de la operatoria de la terminal.
 
 Cada orden está asociada a un container, un viaje y un buque, y registra una
  fecha de creación. Además, puede tener múltiples servicios adicionales que contribuyen al costo final.
  
 La clase provee el comportamiento común: Registrar la fecha de creación,Agregar servicios y Calcular el costo acumulado de los servicios
 
 Las subclases deben implementar:calcularCostoTotal getResponsablePago 
 
 */
public abstract class Orden {

	int id;
	Container container;
	Viaje viaje;
	private LocalDateTime fechaDeRegistro;
	List<Servicio> servicios = new ArrayList<>();
	double costoTotal;
	Buque buque;
	
	public Orden(Container container, Viaje viaje,Buque buque) {
		this.container = container;
		this.viaje = viaje;
		this.fechaDeRegistro= LocalDateTime.now();
		this.servicios = new ArrayList<>();
		this.buque = buque;
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

	public Buque getBuque() {
		// TODO Auto-generated method stub
		return buque;
	}

	public List<Servicio> getServicios() {
		// TODO Auto-generated method stub
		return servicios;
	}


	
}
