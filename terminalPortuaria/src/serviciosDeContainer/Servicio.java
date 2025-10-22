package serviciosDeContainer;

import containers.Container;

public abstract class Servicio {
	
	public double precioFijo;
	
	public Servicio(double precioFijo) {
		this.precioFijo = precioFijo;
	}
	
	public abstract double calcularCosto(Container c);
}
