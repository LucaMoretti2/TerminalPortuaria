package empresaMaritima;

public class Shipper  implements ActorPortuario{

	String nombre;
	int idCliente; 

	@Override
	public void notificar(String mensaje) {
		System.out.println("Shipper recibió: " + mensaje);
	}
}

