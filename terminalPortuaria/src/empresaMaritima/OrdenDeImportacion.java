package empresaMaritima;

import java.time.Duration;
import java.time.LocalDateTime;

import containers.Container;

public class OrdenDeImportacion extends Orden {
	
	Consignee consignee;
	Camion camion;
	Chofer chofer;
	LocalDateTime fechaLlegadaCarga;
	LocalDateTime fechaRetiro;
	int diasExcedentes;

	public OrdenDeImportacion(Container container, Viaje viaje, Consignee consignee, LocalDateTime fechaLlegadaCarga) {
		super(container, viaje);
		// TODO Auto-generated constructor stub
		this.consignee = consignee;
		this.fechaLlegadaCarga = fechaLlegadaCarga;
	}
	
	public void registrarRetiro(Camion camion, Chofer chofer, LocalDateTime fechaRetiro) {
		this.camion = camion;
		this.chofer = chofer;
		this.fechaRetiro = fechaRetiro;
		this.diasExcedentes = calcularDiasExcedentes();
	}
	
	private int calcularDiasExcedentes() {
		long horas = Duration.between(fechaLlegadaCarga, fechaRetiro).toHours();
        return (horas > 24) ? (int) ((horas - 24) / 24) : 0;
    }
	
	

	@Override
	public double calcularCostoTotal() {
		// TODO Auto-generated method stub
		double costoServicios = calcularCostoServicios();
		double costoExcedente = diasExcedentes * 1000; //ejemplo, fijarse que hacer
		costoTotal = costoServicios + costoExcedente + viaje.calcularCostoViaje();
		return costoTotal;
	}

	@Override
	public String getResposablePago() {
		// TODO Auto-generated method stub
		return consignee.getNombre();
	}

}
