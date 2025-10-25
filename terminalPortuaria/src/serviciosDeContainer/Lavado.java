package serviciosDeContainer;

import containers.Container;

public class Lavado extends Servicio {

	double volumenPorContainer;
	double precioMayorSetenta;
	double precioMenorSetenta;
	
	public Lavado(double precioFijo, double volumenPorContainer, double precioMayorSetenta, double precioMenorSetenta) {
		super(precioFijo);
		// TODO Auto-generated constructor stub
		this.volumenPorContainer = volumenPorContainer;
		this.precioMayorSetenta = precioMayorSetenta;
		this.precioMenorSetenta = precioMenorSetenta;
	}
	
	@Override
	public double calcularCostoVariable(Container c) {
		// TODO Auto-generated method stub
		double volumen = c.getAncho() * c.getLargo() * c.getAlto();
		if (volumen > 70) {
			return precioMayorSetenta;
		}
		return precioMenorSetenta;
	}

}
