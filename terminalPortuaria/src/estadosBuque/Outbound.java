package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
// representa la fase en la cual el buque  se encuentra alejándose de la terminal portuaria
public class Outbound implements EstadoDelBuque{

	
	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
	//Si la posición GPS indica que el buque está regresando a puerto   (gps <= 50.0), 
		//se cambia al estado Inbound y se notifica a los consignees.
		if (gps <= 50.0) {
			System.out.println("El buque " + buque.getNombre() + " se encuentra cercano a terminal");
			//System.out.println("Cambiando a Inbound...");
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
	
	

