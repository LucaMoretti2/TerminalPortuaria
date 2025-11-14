package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

// representa al buque cuando ya arribó a la terminal 
public class Arrived implements EstadoDelBuque {

	@Override
	public String nombreEstado() {return "Arrived";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps, TerminalGestionada terminal) {
		if (gps == 0.0) {
			terminal.inicioTrabajo();
			//System.out.println("Cambiando estado a Working...");
			buque.setEstado(new Working());
		}
	}   
	
	@Override
    public void informarExportacion(Buque buque) {
        System.out.println(" NO es posible informar exportaciones en la fase <ARRIVED>");
    }

    @Override
    public void informarImportacion(Buque buque) {
        System.out.println(" NO es posible informar importaciones en la fase <ARRIVED>");
    }

    @Override
    public void realizarPagos(Buque buque) {
        System.out.println(" NO es posible realizar pagos en la fase <ARRIVED>");
    }



}
