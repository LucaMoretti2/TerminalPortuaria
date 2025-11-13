package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public class Working implements EstadoDelBuque {

	@Override
	public String nombreEstado() {return "Working";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		terminal.ordenDeparting();
		buque.setEstado(new Departing());
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
