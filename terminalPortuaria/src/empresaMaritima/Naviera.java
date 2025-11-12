package empresaMaritima;

import java.util.Set;

public class Naviera {
	
	public String nombre;
	
	private Set<Buque> flota ;
	
	private Set<CircuitoMaritimo> circuitos;
	
	public Naviera(String nombre) {this.nombre = nombre;}
	
	public void addBuque(Buque buque) { getFlota().add(buque);}
	
	public void addCircuito(CircuitoMaritimo circuito) { getCircuitos().add(circuito);}

	public Set<CircuitoMaritimo> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(Set<CircuitoMaritimo> circuitos) {
		this.circuitos = circuitos;
	}

	public Set<Buque> getFlota() {
		return flota;
	}

	public void setFlota(Set<Buque> flota) {
		this.flota = flota;
	}
	

	
}
               