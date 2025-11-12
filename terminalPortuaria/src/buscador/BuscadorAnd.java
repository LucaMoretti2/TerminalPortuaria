package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorAnd implements BuscadorDeTrayectosStrategy{
	private BuscadorDeTrayectosStrategy criterio1;
    private BuscadorDeTrayectosStrategy criterio2;

    public BuscadorAnd(BuscadorDeTrayectosStrategy criterio1, BuscadorDeTrayectosStrategy criterio2) {

       this.criterio1 = criterio1;
       this.criterio2 = criterio2;
    }

   @Override
   public List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite) {
       // TODO Auto-generated method stub

       List<CircuitoMaritimo> resultado1 = criterio1.buscar(destino, fechaLimite);
       List<CircuitoMaritimo> resultado2 = criterio2.buscar(destino, fechaLimite);

       List<CircuitoMaritimo> interseccion = new ArrayList<>(resultado1);
       interseccion.retainAll(resultado2); //simula una interseccion

       return interseccion;
   }
}
