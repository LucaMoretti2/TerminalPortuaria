package estadosBuque;

import empresaMaritima.Buque;

public interface EstadoDelBuque {

	String nombreEstado();
	
	void actualizarPosicion(Buque buque, Double gps);
	
	void iniciarTrabajo(Buque buque);
	
	void finalizarTrabajo(Buque buque);
	
	void notificar();
	
	void realizarPagosNecesarios();
	
	
	
}
