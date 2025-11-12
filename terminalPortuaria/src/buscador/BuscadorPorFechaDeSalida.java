 package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorPorFechaDeSalida implements BuscadorDeTrayectosStrategy {
	List<CircuitoMaritimo> circuitos;

    public BuscadorPorFechaDeSalida(List<CircuitoMaritimo> circuitos) {

        this.circuitos = circuitos;
    }


    @Override
    public List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite) {
        // TODO Auto-generated method stub
        List<CircuitoMaritimo> resultado = new ArrayList<>();

        for (CircuitoMaritimo circuito : circuitos) {

            if (circuito.contieneTerminal(destino)) {
                LocalDateTime fechaDeInicio = circuito.getFechaDeInicio();


                if (fechaDeInicio.isAfter(fechaLimite) || fechaDeInicio.isEqual(fechaLimite)) {
                    resultado.add(circuito);
                }
            }
        }

        return resultado;

    }

}
