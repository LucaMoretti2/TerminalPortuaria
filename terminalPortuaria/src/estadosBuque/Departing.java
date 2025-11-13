package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public class Departing implements EstadoDelBuque {

	@Override
	public String nombreEstado() { return "Departing";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		if (gps >= 50.0) {
			System.out.println("El buque" + buque.getNombre() + "se encuentra saliendo de la terminal");
			System.out.println("Cambiando a Outbound...");
			terminal.notificarShippers("El buque" + buque.getNombre() + "esta saliendo de la terminal");
			buque.setEstado(new Outbound());
			}

	}

	@Override
	public void informarExportacion(Buque buque) {
		System.out.println(" NO es posible informar exportaciones en la fase <WORKING>");
	}

	@Override
	public void informarImportacion(Buque buque) {
		System.out.println(" NO es posible informar importaciones en la fase <WORKING>");
	}

	@Override
	public void realizarPagos(Buque buque) {
		System.out.println(" NO es posible realizar pagos en la fase <WORKING>");
	}

}
