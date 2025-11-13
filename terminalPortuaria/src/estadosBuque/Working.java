package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
//representa la fase en la cual el buque
//se encuentra realizando operaciones de carga o descarga dentro de la terminal (Working)
public class Working implements EstadoDelBuque {

	@Override
	public String nombreEstado() {return "Working";}
	
//Al actualizar la posición del buque, la terminal da por finalizados los trabajos
// y el estado del buque cambia automáticamente a Departing.
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
