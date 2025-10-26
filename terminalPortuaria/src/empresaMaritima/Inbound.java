package empresaMaritima;

public class Inbound implements EstadoDelBuque{

	@Override
	public String nombreEstado() {return "Inbound";}

	@Override
	public void actualizarPosicion(Buque buque, Double gps) {
		
		if (gps == 0.0) {
			buque.setEstado(new Arrived());
			System.out.println("El buque" + buque.nombreBuque + "llegó a terminal");
			System.out.println("Cambiando el estado a Inbound...");
		}
		
	}
	
	@Override
	public void iniciarTrabajo(Buque buque) {}

	@Override
	public void finalizarTrabajo(Buque buque) {}

	//Se envia email a los consignees por el arribo del buque
	@Override
	public void notificar() {}

	@Override
	public void realizarPagosNecesarios() {
		System.out.println("Los pagos por servicios no se pueden operar en la fase actual <INBOUND>");
		
	}

}
