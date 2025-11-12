package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public class Inbound implements EstadoDelBuque{

	@Override
	public String nombreEstado() {return "Inbound";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		
		if (gps == 0.0) {
			System.out.println("El buque" + buque.nombreBuque + "llegó a terminal");
			System.out.println("Cambiando el estado a Arrived...");
			terminal.notificarConsignees("El buque llego a destino");
			buque.setEstado(new Arrived());
		}
		
	}
	

	@Override
	public void informarExportacion(Buque buque) {
		System.out.println("No es posible informar exportaciones en el estado actual <INTBOUND>");
		
	}

	@Override
	public void informarImportacion(Buque buque) {
		System.out.println("Informe de importacion enviado exitosamente");
		
	}

	@Override
	public void realizarPagos(Buque buque) {
		System.out.println("No se pueden realizar pago en la fase actual <INBOUND>");}
	
}


