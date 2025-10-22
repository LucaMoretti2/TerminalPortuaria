package serviciosDeContainer;

import containers.Container;
import containers.Reefer;

public class Electricidad extends Servicio {

	public Electricidad(double precioFijo) {
		super(precioFijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularCosto(Container c) {
		// TODO Auto-generated method stub
		
		if (c instanceof Reefer reefer) {
			double horas = reefer.getHorasConectado();
	        return horas * reefer.getConsumoPorHora() * precioFijo;
	    }
	    return 0;
	    
	}

}
