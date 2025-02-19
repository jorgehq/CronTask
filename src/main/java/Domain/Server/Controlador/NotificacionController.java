package Domain.Server.Controlador;

import Domain.Colaborador.Colaborador;
import Domain.Heladera.Heladera;
import Domain.Notificaciones.Notificacion;
import Domain.Notificaciones.NotificacionFaltanViandas;
import Domain.Notificaciones.NotificacionIncidente;
import Domain.Repositorios.RepoColaboradores;
import Domain.Repositorios.RepoHeladera;
import io.javalin.http.Context;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NotificacionController {

    public void pantallaNotificaciones(Context ctx) {
        String usuarioID = ctx.sessionAttribute("usuarioID");
        Map<String, Object> model = ctx.attribute("sharedData");
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));

        String error = ctx.sessionAttribute("error");
        if (error != null) {
            model.put("error", error);
            ctx.sessionAttribute("error", null);
        }
        List<Notificacion> filtradas ;
        if (usuarioID == null) {
            ctx.redirect("/");
        } else {
            Colaborador colaborador=RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));

            System.out.println("================================Cantidad de notificaciones "+colaborador.getNotificaciones().size()+"=============================");
                filtradas = new ArrayList<>(colaborador.getNotificaciones());
                if(filtradas==null){
                    TemplateRender.render(ctx, "/Notificaciones.html.hbs", model);
                }else{
                    filtradas.sort((n1, n2) -> n2.getFecha().compareTo(n1.getFecha()));
                    List<Integer> cantidadActual = new ArrayList<>();
                    List<Integer> cantidadFaltante = new ArrayList<>();
                    List<Integer> cantidadDonada = new ArrayList<>();
                    List<String> tiposNotificacion=new ArrayList<>();
                    List<String> enlaces=new ArrayList<>();

                    for (Notificacion n : filtradas) {
                        System.out.println("Notificación: " + n.getId() + ", Heladera: " + n.getHeladera());
                    }

                    for (Notificacion n : filtradas) {

                        Heladera h= RepoHeladera.getInstance().buscarPorId(n.getHeladera().getId());

                        if (n instanceof NotificacionFaltanViandas) {
                            cantidadActual.add(h.cantidadViandas());
                            cantidadFaltante.add(n.getHeladera().getCapacidadDeViandas() - h.cantidadViandas());
                            cantidadDonada.add(n.getHeladera().cantidadSolicitudesVianda());
                            tiposNotificacion.add("FaltanViandas");
                            enlaces.add("/colaboracion/vianda?heladeraID="+n.getHeladera().getId());

                        } else if (n instanceof NotificacionIncidente) {
                            int faltantes=h.cantidadViandas();
                            cantidadActual.add(h.cantidadViandas());
                            cantidadFaltante.add(faltantes);
                            cantidadDonada.add(n.getHeladera().cantidadSolicitudesDistribucion());
                            tiposNotificacion.add("Incidente");
                            enlaces.add("/colaboracion/distribucion?heladeraId="+n.getHeladera().getId()+"&heladeraId2="+((NotificacionIncidente) n).getSugerencia().getHeladera().getId());
                        } else {
                            tiposNotificacion.add("Otro");
                        }

                    }


                    model.put("Indices", IntStream.range(0, filtradas.size()).boxed().collect(Collectors.toList()));
                    model.put("Notificaciones", filtradas);
                    model.put("TiposNotificacion", tiposNotificacion);
                    model.put("CantidadViandas", cantidadActual);
                    model.put("Faltantes", cantidadFaltante);
                    model.put("EnCamino", cantidadDonada);
                    model.put("Enlaces",enlaces);

                    ctx.header("Cache-Control", "no-cache, no-store, must-revalidate");
                    ctx.header("Pragma", "no-cache");
                    ctx.header("Expires", "0");
                    TemplateRender.render(ctx, "/Notificaciones.html.hbs", model);
                }




        }
    }

    public void eliminarNotificacionColaborador(Context ctx){


        String noti = ctx.queryParam("idNotificacion");
        Long colaboradorId = Long.parseLong(ctx.sessionAttribute("usuarioID"));
        Long notificacionId = Long.parseLong(noti);

        RepoColaboradores.getInstance().eliminarNotificacionColaborador(colaboradorId, notificacionId);
        Colaborador colaborador=RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));

        System.out.println("========================================Colaborador notificaciones tamaño nuevo"+colaborador.getNotificaciones().size());

        ctx.redirect("/notificaciones");
    }
}
