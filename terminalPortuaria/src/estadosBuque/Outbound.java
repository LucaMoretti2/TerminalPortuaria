package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public class Outbound implements EstadoDelBuque{

	
	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
			
		if (gps <= 50.0) {
			System.out.println("El buque" + buque.getNombre() + "se encuentra cercano a terminal");
			System.out.println("Cambiando a Inbound...");
			terminal.notificarConsignees("El buque esta pronto al arrivo", buque);
			buque.setEstado(new Inbound());
			}
	}

	@Override
	public String nombreEstado() {return "Outbound";}

	@Override
	public void informarExportacion(Buque buque) {
		System.out.println("Informe de exportacion enviado exitosamente");
		
	}

	@Override
	public void informarImportacion(Buque buque) {
		System.out.println("No es posible informar importaciones en el estado actual <OUTBOUND>");
	}

	@Override
	public void realizarPagos(Buque buque) {
		System.out.println("No se pueden realizar pago en la fase actual <OUTBOUND>");}
	
}
	
	

