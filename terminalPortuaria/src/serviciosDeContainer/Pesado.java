package serviciosDeContainer;

import containers.Container;

public class Pesado extends Servicio {

	public Pesado(double precioFijo) {
		super(precioFijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularCosto(Container c) {
		// TODO Auto-generated method stub
		return precioFijo;
	}

}
