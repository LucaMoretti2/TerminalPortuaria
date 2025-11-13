package ordenes;

import java.time.LocalDateTime;

import actores.Shipper;
import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.Viaje;

/*
 Representa una orden de exportación de un container dentro de la terminal.
 Extiende la clase abstracta Orden e incorpora información específica de la operatoria de exportación:
  - Shipper (quien contrata y paga la operación)
 - Camión y Chofer que ingresan el container a la terminal
 - Turno asignado para el ingreso
Implementa los métodos abstractos de Orden:
 - calcularCostoTotal(): el costo final es la suma de los servicios aplicados
- getResponsablePago(): el responsable del pago es el shipper

 */
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

	
	@Override
	public double calcularCostoTotal() {
		// TODO Auto-generated method stub
		costoTotal = calcularCostoServicios();
		return costoTotal;
	}

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
