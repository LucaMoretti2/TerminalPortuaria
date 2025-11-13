package serviciosDeContainer;

import containers.Container;
// Representa un servicio de pesado o un servicio fijo sin costo variable. 
// este servicio únicamente cobra un precio fijo y no depende de ninguna característica del contenedor.
public class Pesado extends Servicio {

	public Pesado(double precioFijo) {
		super(precioFijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularCostoVariable(Container container) {
		// TODO Auto-generated method stub
		return 0;
	}

}
