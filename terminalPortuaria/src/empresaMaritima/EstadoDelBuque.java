package empresaMaritima;

public interface EstadoDelBuque {

	String nombreEstado();
	
	void actualizarPosicion(Buque buque, Double gps);
	
	void iniciarTrabajo(Buque buque);
	
	void finalizarTrabajo(Buque buque);
	
	void notificar();
	
	void realizarPagosNecesarios();
	
	
	
}
