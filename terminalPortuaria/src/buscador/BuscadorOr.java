package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorOr implements BuscadorDeTrayectosStrategy {
	
	private BuscadorDeTrayectosStrategy criterio1;
    private BuscadorDeTrayectosStrategy criterio2;

    public BuscadorOr(BuscadorDeTrayectosStrategy criterio1, BuscadorDeTrayectosStrategy criterio2) {

        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
    }

    @Override
    public List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite) {
       

        List<CircuitoMaritimo> resultado1 = criterio1.buscar(destino, fechaLimite);
        List<CircuitoMaritimo> resultado2 = criterio2.buscar(destino, fechaLimite);

        Set<CircuitoMaritimo> union = new HashSet<>(resultado1);
        union.addAll(resultado2); //para no repetir, simula una union

        return new ArrayList<>(union);
    }
}
