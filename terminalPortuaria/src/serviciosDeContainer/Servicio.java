package serviciosDeContainer;

import containers.Container;
// Clase abstracta que representa un servicio aplicado a un contenedor dentro de la terminal portuaria. 
//Cada servicio tiene un precio fijo y puede definir un costo variable adicional 
public abstract class Servicio {
	
	public double precioFijo;
	
	public Servicio(double precioFijo) {
		this.precioFijo = precioFijo;
	}
	
	public double calcularCosto(Container container) {
		double costoBase = this.precioFijo;
		double costoVariable = this.calcularCostoVariable(container);
		return costoBase + costoVariable;
	}
	
	protected abstract double calcularCostoVariable(Container container);
	
	public String toString() {
	    return this.getClass().getSimpleName() ;
	}
}
