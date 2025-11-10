package reportes;

import empresaMaritima.TerminalGestionada;
import empresaMaritima.Orden;
import java.time.format.DateTimeFormatter;

public class ReporteMuelleVisitor implements ReporteVisitor {

    private StringBuilder texto = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalGestionada terminal) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        texto.append("=== REPORTE DE MUELLE ===\n");

        terminal.getOrdenes().forEach(o -> {
            texto.append("Buque: ").append(o.getViaje().getBuque().getNombre()).append("\n")
                 .append("Arribo: ").append(o.getViaje().getArribo().format(fmt)).append("\n")
                 .append("Partida: ").append(o.getViaje().getPartida().format(fmt)).append("\n")
                 .append("Contenedores operados: ")
                 .append(o.getViaje().getContenedores().size()).append("\n\n");
        });
    }

    public String getResultado() {
        return texto.toString();
    }
}
