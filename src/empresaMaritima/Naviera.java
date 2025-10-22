package empresaMaritima;

import java.util.Set;

public class Naviera {
	
	public String nombre;
	
	Set<Buque> flota ;
	
	Set<CircuitoMaritimo> circuitos;
	
	public Naviera(String nombre) {
		
		this.nombre = nombre;
	}
	
	
	public void addBuque(Buque buque) { flota.add(buque);}
	
	public void addCircuito(CircuitoMaritimo circuito) { circuitos.add(circuito);}
	

	
}
               