package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public class Arrived implements EstadoDelBuque {

	@Override
	public String nombreEstado() {return "Arrived";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		if (gps == 0.0) {
			terminal.inicioTrabajo();
			System.out.println("Cambiando estado a Working...");
			buque.setEstado(new Working());
		}
	}


}
