package empresaMaritima;

import java.util.Set;
//Representa una Naviera que opera buques y circuitos maritimos dentro del sistema
//esponsables del transporte nternacional de contenedores.
public class Naviera {
	
	public String nombre;
	
	private Set<Buque> flota ;
	
	private Set<CircuitoMaritimo> circuitos;
	
	public Naviera(String nombre) {this.nombre = nombre;}
	
	//permiten registrar buques y circuitos dentro de la naviera
	
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

//calcula la duración del viaje entre dos terminales,
//siempre que exista un circuito que contenga a ambas. Si no lo hay,
//devuelve -1.
	public long tiempoDeRecorrido(TerminalGestionada origen, TerminalGestionada destino) {
        for (CircuitoMaritimo circuito : circuitos) {
            if (circuito.contieneTerminal(origen) && circuito.contieneTerminal(destino)) {
                return circuito.calcularDuracionHasta(destino);
            }
        }
        return -1; 
    }

	
}
               