package Domain.Servicios;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.TipoDeColaboracion.DonarVianda;
import Domain.Heladera.Heladera;
import Domain.Incidentes.FallaTecnica;
import Domain.Repositorios.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioReporte {
    public static Map<String, Object> generarReporteFallas(Map<String, Object> model) {
        Map<Heladera, Integer> reporteFallasPorHeladera = new HashMap<>();
        List<Heladera> heladeras = RepoHeladera.getInstance().obtenerTodos().stream().toList();

        for (Heladera h : heladeras) {
            int cantidad = 0;
            List<FallaTecnica> fallas = RepoFallaTecnica.getInstance().obtenerTodos().stream().toList();
            if (fallas.size() == 0) {
                System.out.println("===============No hay fallas en esta heladera==============");
                cantidad = 0;
            } else {
                cantidad += fallas.stream().filter(f -> f.getHeladera().getId() == h.getId()).toList().size();
            }
            reporteFallasPorHeladera.put(h, cantidad);
        }
        model.put("tiporeporte", "Fallas por heladera");
        model.put("heladeras", reporteFallasPorHeladera);
        return model;
    }

    public static Map<String, Object> generarReporteViandasRetiradas(Map<String, Object> model) {
        Map<Heladera, Integer> reporteEntradaSalida = new HashMap<>();
        List<Heladera> heladeras2 = RepoHeladera.getInstance().obtenerTodos().stream().toList();
        List<DonarVianda> colaboraciones = RepoColaboraciones.getInstance().obtenerDonacionesViandas();

        int cantidad = 0;
        for (Heladera h : heladeras2) {
            cantidad += colaboraciones.stream()
                    .filter(d -> d.getVianda() != null)
                    .filter(d -> d.getVianda().getHeladera() != null)
                    .filter(d -> d.getVianda().getHeladera().getId().equals(h.getId()))
                    .toList().size();

            cantidad += RepoMovimientoTarjeta.getInstance().obtenerTodos().stream()
                    .filter(m -> m.heladera != null)
                    .filter(m -> m.heladera.getId().equals(h.getId()))
                    .toList().size();

            reporteEntradaSalida.put(h, cantidad);
            cantidad = 0;
        }
        model.put("tiporeporte", "Viandas retiradas y colocadas");
        model.put("heladeras", reporteEntradaSalida);
        return model;
    }

    public static Map<String, Object> generarReporteViandasPorColaborador(Map<String, Object> model) {
        Map<Colaborador, Integer> reporteViandasPorColaborador = new HashMap<>();
        List<Colaborador> colaboradores = RepoColaboradores.getInstance().obtenerTodos();
        List<DonarVianda> donaciones = RepoColaboraciones.getInstance().obtenerDonacionesViandas();

        for (Colaborador c : colaboradores) {
            int cantidad3 = 0;
            cantidad3 += donaciones.stream()
                    .filter(d -> d.getVianda() != null)
                    .filter(d -> d.getVianda().getColaborador() != null)
                    .filter(d -> d.getVianda().getHeladera() != null)
                    .filter(d -> d.getVianda().getColaborador().getId() == c.getId())
                    .toList().size();
            reporteViandasPorColaborador.put(c, cantidad3);
        }
        model.put("tiporeporte", "Viandas por colaborador");
        model.put("colaboradores", reporteViandasPorColaborador);
        return model;
    }

    public static Map<String, Object> generarReportePuntajeColaborador(Map<String, Object> model) {
        Map<Colaborador, Double> reportePuntajeColaborador = new HashMap<>();
        List<Colaborador> colaboradores3 = RepoColaboradores.getInstance().obtenerTodos();
        for (Colaborador c : colaboradores3) {
            double cantidad4 = 0;
            cantidad4 = c.calcularPuntajeColaboraciones(LocalDate.now().minusDays(7), LocalDate.now());
            reportePuntajeColaborador.put(c, cantidad4);
        }
        model.put("tiporeporte", "Puntaje del colaborador");
        model.put("colaboradores", reportePuntajeColaborador);
        return model;
    }
}

