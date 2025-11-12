package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorPorFechaDeLlegada implements BuscadorDeTrayectosStrategy {
	
	private List<CircuitoMaritimo> circuitos;
    private LocalDateTime fechaLimite;

    public BuscadorPorFechaDeLlegada(List<CircuitoMaritimo> circuitos, LocalDateTime fechaLimite) {
        this.circuitos = circuitos;
        this.fechaLimite = fechaLimite;
    }


    @Override
    public List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite) {
        // TODO Auto-generated method stub
        List<CircuitoMaritimo> resultado = new ArrayList<>();

        for (CircuitoMaritimo circuito : circuitos) {
            if (circuito.contieneTerminal(destino)) {
                LocalDateTime fechaDeInicio = circuito.getFechaDeInicio();
                long horasTotales = circuito.calcularTiempoTotalHoras();
                LocalDateTime fechaEstimada = fechaDeInicio.plusHours(horasTotales);

                if (fechaEstimada.isBefore(fechaLimite) || fechaEstimada.isEqual(fechaLimite)) {
                    resultado.add(circuito);
                }
            }
        }

        return resultado;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }
}
