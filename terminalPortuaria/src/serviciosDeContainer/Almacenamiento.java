package serviciosDeContainer;

import java.time.Duration;

import containers.Container;

public class Almacenamiento extends Servicio {

	
	//el precioFijo que se asigna en el constructor es por dia.
	public Almacenamiento(double precioFijo) {
		super(precioFijo);
		
	}

	@Override
	public double calcularCostoVariable(Container c) {
		
		Duration duracion = Duration.between(c.getFechaDeIngreso(), c.getFechaDeRetiro());
        long diasEnTerminal = duracion.toDays();
		
		return precioFijo * diasEnTerminal;
	}

}
