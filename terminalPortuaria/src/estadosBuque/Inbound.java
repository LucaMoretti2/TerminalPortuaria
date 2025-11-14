package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
//representa la fase en la cual el buque se encuentra aproximándose a la terminal
public class Inbound implements EstadoDelBuque{

	@Override
	public String nombreEstado() {return "Inbound";}

	@Override
	//Cuando la posición GPS indica que el buque está en la terminal (gps == 0.0),
// se notifica a los consignees su arribo y el buque cambia al estado Arrived.
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		
		if (gps == 0.0) {
			System.out.println("El buque " + buque.nombreBuque + " llegó a terminal");
			//System.out.println("Cambiando el estado a Arrived...");
			terminal.notificarConsignees("El buque llego a destino", buque);
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


