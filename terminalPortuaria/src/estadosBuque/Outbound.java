package estadosBuque;

import empresaMaritima.Buque;

public class Outbound implements EstadoDelBuque{

	
	@Override
	public void actualizarPosicion(Buque buque, Double gps) {
			
		if (gps <= 50.0) {
			buque.setEstado(new Inbound());}
		
		System.out.println("El buque" + buque.nombreBuque + "se encuentra cercano a terminal");
		System.out.println("Cambiando a Inbound...");
		}

	@Override
	public String nombreEstado() {return "Outbound";}
	
	// El inicio y la finalizacion de los trabajos de carga y/o descarga:
	@Override
	public void iniciarTrabajo(Buque buque) {
		
		System.out.println("El buque" + buque.nombreBuque + "no puede iniciar trabajos en estado OUTBOUND");
	}
	
	@Override
	public void finalizarTrabajo(Buque buque) {
		
		System.out.println("El buque" + buque.nombreBuque + "no puede finalizar trabajos en estado OUTBOUND");
	}
	
	@Override
	public void notificar() {} // En estado OUTBOUND SE PUEDEN INFORMAR EXPORTACIONES 
	
	
	//Los pagos necesarios se refieren a los de los servicios por los containers del buque.
	@Override
	public void realizarPagosNecesarios() {
		System.out.println("Los pagos por servicios no se pueden operar en la fase actual <OUTBOUND>");
	}	
}
	
	
	
	

