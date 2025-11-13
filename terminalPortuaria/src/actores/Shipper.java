package actores;
//Representa al exportador
public class Shipper implements ActorPortuario {

    String nombre;
    int idCliente;

    public Shipper(String nombre,int idCliente) {
    	this.nombre=nombre;
    	this.idCliente=idCliente;
    }
    @Override
    public void notificar(String mensaje) {
        System.out.println("Shipper recibió: " + mensaje);
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }
}
