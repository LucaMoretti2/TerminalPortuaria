package ordenes;

import java.time.LocalDateTime;

import actores.Shipper;
import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.Viaje;

//Representa una orden de exportación de un container dentro de la terminal.

public class OrdenDeExportacion extends Orden {

	private Shipper shipper;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turnoAsignado;
	
	public OrdenDeExportacion(Container container, Viaje viaje,Buque buque, Shipper shipper, Camion camion, Chofer chofer, LocalDateTime turnoAsignado) {
		super(container, viaje,buque);
		// TODO Auto-generated constructor stub
		this.shipper = shipper;
		this.camion = camion;
		this.chofer = chofer;
		this.turnoAsignado = turnoAsignado;
		this.buque = buque;
	}

//costo final es la suma de los servicios aplicados
	@Override
	public double calcularCostoTotal() {
		// TODO Auto-generated method stub
		costoTotal = calcularCostoServicios();
		return costoTotal;
	}
//el responsable del pago es el shipper


	@Override
	public String getResposablePago() {
		// TODO Auto-generated method stub
		return getShipper().getNombre();
	}


	public Shipper getShipper() {
		return shipper;
	}


	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}


	public Camion getCamion() {
		return camion;
	}


	public void setCamion(Camion camion) {
		this.camion = camion;
	}


	public Chofer getChofer() {
		return chofer;
	}


	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}


	public LocalDateTime getTurnoAsignado() {
		return turnoAsignado;
	}


	public void setTurnoAsignado(LocalDateTime turnoAsignado) {
		this.turnoAsignado = turnoAsignado;
	}

}
