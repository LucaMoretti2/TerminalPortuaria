package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorPorPuertoDestino implements BuscadorDeTrayectosStrategy {

	List<CircuitoMaritimo> circuitos;

    public BuscadorPorPuertoDestino(List<CircuitoMaritimo> circuitos) {

        this.circuitos = circuitos;
    }


    @Override
    public List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite) {
        // TODO Auto-generated method stub

        List<CircuitoMaritimo> resultado = new ArrayList<>();

         for (CircuitoMaritimo circuito : circuitos) {

                if (circuito.contieneTerminal(destino)) {
                    resultado.add(circuito);
                }
            }

         return resultado;
    }
}
