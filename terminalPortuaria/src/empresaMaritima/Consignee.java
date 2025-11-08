package empresaMaritima;

public class Consignee implements ActorPortuario {

	String nombre;
    int idCliente;

	@Override
	public void notificar(String mensaje) {
		System.out.println("Consignee recibió: " + mensaje);
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
}

