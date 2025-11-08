package serviciosDeContainer;

import containers.Container;

public abstract class Servicio {
	
	protected double precioFijo;
	
	public Servicio(double precioFijo) {
		this.precioFijo = precioFijo;
	}
	
	public double calcularCosto(Container container) {
		double costoBase = this.precioFijo;
		double costoVariable = this.calcularCostoVariable(container);
		return costoBase + costoVariable;
	}
	
	protected abstract double calcularCostoVariable(Container container);
}
