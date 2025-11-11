package reportes;

import empresaMaritima.TerminalGestionada;
import empresaMaritima.Orden;
import empresaMaritima.OrdenDeExportacion;
import empresaMaritima.OrdenDeImportacion;

public class ReporteBuqueVisitor implements ReporteVisitor {

    private StringBuilder xml = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalGestionada terminal) {
        xml.append("<report>\n");

        xml.append("  <import>\n");
        for (Orden o : terminal.getOrdenes()) {
            if (o instanceof OrdenDeImportacion) {
                xml.append("    <item>")
                   .append(o.getContainer().getIDContainer())
                   .append("</item>\n");
            }
        }
        xml.append("  </import>\n");

        xml.append("  <export>\n");
        for (Orden o : terminal.getOrdenes()) {
            if (o instanceof OrdenDeExportacion) {
                xml.append("    <item>")
                   .append(o.getContainer().getIDContainer())
                   .append("</item>\n");
            }
        }
        xml.append("  </export>\n");

        xml.append("</report>");
    }

    public String getXml() {
        return xml.toString();
    }
}
