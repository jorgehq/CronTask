package Domain.Server.Controlador;

import Domain.Server.Enums.Tiposreporte;
import Domain.Servicios.ServicioReporte;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ReporteController {


    public void pantallaPrincipal(Context ctx) {
        Map<String, Object> model = ctx.attribute("sharedData");;
        String eleccion = ctx.queryParam("eleccion");
        if (eleccion == null) {

            TemplateRender.render(ctx, "/Reporte.html.hbs", model);
        } else {
            switch (Tiposreporte.valueOf(eleccion)) {
                case FALLAS: //Fallas por heladera
                    model = ServicioReporte.generarReporteFallas(model);
                    break;
                case VIANDASRETIRADAS: //Viandas retiradas y colocadas
                    model = ServicioReporte.generarReporteViandasRetiradas(model);
                    break;
                case VIANDASPORCOLABORADOR: //Viandas por colaborador
                    model = ServicioReporte.generarReporteViandasPorColaborador(model);
                    break;
                case PUNTAJECOLABORADOR: //COlaboradores puntaje
                    model = ServicioReporte.generarReportePuntajeColaborador(model);
                    break;
                default:
                    break;
            }


            TemplateRender.render(ctx, "/Reporte.html.hbs", model);
        }

    }
    public void descargarReporte (Context ctx){
        Map<String, Object> model = new HashMap<>();
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));

        TemplateRender.render(ctx, "/Reporte.html.hbs", model);
    }
}