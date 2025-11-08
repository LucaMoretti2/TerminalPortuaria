package empresaMaritima;

import java.time.LocalDateTime;

import containers.Container;

public class OrdenDeExportacion extends Orden {

	Shipper shipper;
	Camion camion;
	Chofer chofer;
	LocalDateTime turnoAsignado;
	
	public OrdenDeExportacion(Container container, Viaje viaje, Shipper shipper, Camion camion, Chofer chofer, LocalDateTime turnoAsignado) {
		super(container, viaje);
		// TODO Auto-generated constructor stub
		this.shipper = shipper;
		this.camion = camion;
		this.chofer = chofer;
		this.turnoAsignado = turnoAsignado;
	}

	
	@Override
	public double calcularCostoTotal() {
		// TODO Auto-generated method stub
		costoTotal = calcularCostoServicios();
		return costoTotal;
	}

	@Override
	public String getResposablePago() {
		// TODO Auto-generated method stub
		return shipper.getNombre();
	}

}
