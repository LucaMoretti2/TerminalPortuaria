package serviciosDeContainer;

import containers.Container;
import containers.Reefer;

public class Electricidad extends Servicio {

	private double costoPorKW;
	
	public Electricidad(double precioFijo, double costoPorKW) {
		super(precioFijo);
		this.costoPorKW = costoPorKW;
		
	}
//este servicio calcula el costo basado 
//en el tiempo de conexión y el consumo energetico del Reefer.
	@Override
	public double calcularCostoVariable(Container c) {
		
		
		if (c instanceof Reefer reefer) {
			double horas = reefer.getHorasConectado();
	        return horas * reefer.getConsumoPorHora() * costoPorKW;
	    }
	    return 0;
	    
	}

}
