package serviciosDeContainer;

import java.time.Duration;

import containers.Container;

public class Almacenamiento extends Servicio {

	
	//el precioFijo que se asigna en el constructor es por dia.
	public Almacenamiento(double precioFijo) {
		super(precioFijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularCosto(Container c) {
		// TODO Auto-generated method stub
		Duration duracion = Duration.between(c.getFechaDeIngreso(), c.getFechaDeRetiro());
        long diasEnTerminal = duracion.toDays();
		
		return precioFijo * diasEnTerminal;
	}

}
