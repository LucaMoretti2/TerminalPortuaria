package reportes;

import empresaMaritima.TerminalGestionada;
import empresaMaritima.Orden;

public class ReporteAduanaVisitor implements ReporteVisitor {

    private StringBuilder html = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalGestionada terminal) {
        html.append("<html><body>");
        html.append("<h1>Reporte Aduana</h1>");

        for (Orden o : terminal.getOrdenes()) {
            html.append("<h2>Buque: ").append(o.getViaje().getBuque().getNombre()).append("</h2>")
                .append("<p>Arribo: ").append(o.getViaje().getArribo()).append("<br>")
                .append("Partida: ").append(o.getViaje().getPartida()).append("</p>")
                .append("<ul>");
            o.getViaje().getContenedores().forEach(c ->
                html.append("<li>").append(c.getTipo()).append(" - ").append(c.getIDContainer()).append("</li>")
            );
            html.append("</ul>");
        }

        html.append("</body></html>");
    }

    public String getHtml() {
        return html.toString();
    }
}
