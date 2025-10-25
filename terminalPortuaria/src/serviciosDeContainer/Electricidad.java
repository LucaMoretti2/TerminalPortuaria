package serviciosDeContainer;

import containers.Container;
import containers.Reefer;

public class Electricidad extends Servicio {

	private double costoPorKW;
	
	public Electricidad(double precioFijo, double costoPorKW) {
		super(precioFijo);
		this.costoPorKW = costoPorKW;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularCostoVariable(Container c) {
		// TODO Auto-generated method stub
		
		if (c instanceof Reefer reefer) {
			double horas = reefer.getHorasConectado();
	        return horas * reefer.getConsumoPorHora() * costoPorKW;
	    }
	    return 0;
	    
	}

}
