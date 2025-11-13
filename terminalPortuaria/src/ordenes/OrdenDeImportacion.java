package ordenes;

import java.time.Duration;
import java.time.LocalDateTime;

import actores.Consignee;
import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.Viaje;

public class OrdenDeImportacion extends Orden {
	
	Consignee consignee;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime fechaLlegadaCarga;
	private LocalDateTime fechaRetiro;
	private int diasExcedentes;

	public OrdenDeImportacion(Container container, Viaje viaje,Buque buque, Consignee consignee, LocalDateTime fechaLlegadaCarga) {
		super(container, viaje, buque);
		// TODO Auto-generated constructor stub
		this.consignee = consignee;
		this.setFechaLlegadaCarga(fechaLlegadaCarga);
		this.buque = buque;
	}
	
	public void registrarRetiro(Camion camion, Chofer chofer, LocalDateTime fechaRetiro) {
		this.setCamion(camion);
		this.setChofer(chofer);
		this.setFechaRetiro(fechaRetiro);
		this.setDiasExcedentes(calcularDiasExcedentes());
	}
	
	private int calcularDiasExcedentes() {
		long horas = Duration.between(getFechaLlegadaCarga(), getFechaRetiro()).toHours();
        return (horas > 24) ? (int) ((horas - 24) / 24) : 0;
    }
	
	

	@Override
	public double calcularCostoTotal() {
		// TODO Auto-generated method stub
		double costoServicios = calcularCostoServicios();
		double costoExcedente = getDiasExcedentes() * 1000; //ejemplo, fijarse que hacer
		costoTotal = costoServicios + costoExcedente + viaje.calcularCostoViaje();
		return costoTotal;
	}

	@Override
	public String getResposablePago() {
		// TODO Auto-generated method stub
		return consignee.getNombre();
	}

	public Consignee getConsignee() {
		// TODO Auto-generated method stub
		return consignee;
	}

	public LocalDateTime getFechaLlegadaCarga() {
		return fechaLlegadaCarga;
	}

	public void setFechaLlegadaCarga(LocalDateTime fechaLlegadaCarga) {
		this.fechaLlegadaCarga = fechaLlegadaCarga;
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

	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public int getDiasExcedentes() {
		return diasExcedentes;
	}

	public void setDiasExcedentes(int diasExcedentes) {
		this.diasExcedentes = diasExcedentes;
	}

}
