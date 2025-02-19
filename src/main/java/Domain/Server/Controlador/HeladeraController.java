package Domain.Server.Controlador;

import Domain.Colaborador.Colaborador;
import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoColaboradores;
import Domain.Repositorios.RepoHeladera;
import Domain.Server.Enums.Filtros;
import io.javalin.http.Context;

import java.util.*;

public class HeladeraController {
    public void pantallaPrincipal(Context ctx) {

        String usuarioID = ctx.sessionAttribute("usuarioID");
        Map<String, Object> model = ctx.attribute("sharedData");
        Set<Heladera> filtradas;
        if (usuarioID == null) {
            ctx.redirect("/");
        } else {
            String filtro = ctx.queryParam("filtro");
            String dato = ctx.queryParam("dato");
            if (filtro == null) {
                filtradas = RepoHeladera.getInstance().obtenerTodos();
                model.put("heladeras", filtradas);
                TemplateRender.render(ctx, "heladeras.html.hbs", model);
                return;
            }
            try {

                switch (Filtros.valueOf(filtro)) {
                    case TODAS:
                        filtradas = RepoHeladera.getInstance().obtenerTodos();
                        model.put("heladeras", filtradas);
                        break;
                    case NOMBRE:
                        filtradas = RepoHeladera.getInstance().filtrarPorNombre(dato);
                        model.put("heladeras", filtradas);
                        break;
                    case LOCALIDAD:
                        filtradas = RepoHeladera.getInstance().filtrarPorLocalidad(dato);
                        model.put("heladeras", filtradas);
                        break;
                    case DIRECCION:
                        filtradas = RepoHeladera.getInstance().filtrarPorDireccion(dato);
                        model.put("heladeras", filtradas);
                        break;
                    default:
                        model.put("error", "El filtro seleccionado no es válido.");
                        break;
                }
            } catch (NumberFormatException e) {
                model.put("error", "El filtro debe ser un número válido.");
            } catch (IllegalArgumentException e) {
                model.put("error", "El dato ingresado no es válido.");
            } catch (Exception e) {
                model.put("error", "Ocurrió un error inesperado. Inténtalo nuevamente.");
            }
            TemplateRender.render(ctx, "/heladeras.html.hbs", model);
        }
    }
    public void suscribirseOreportar(Context ctx){

        List<String> heladeras = ctx.formParams("heladeraId");
        Colaborador colaborador= RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));



            for(String id:heladeras){
                Heladera h=RepoHeladera.getInstance().buscarPorId(Long.parseLong(id));
                boolean yaSuscrito = h.getInteresados().stream()
                        .anyMatch(c -> c.getId().equals(colaborador.getId()));
                if (!yaSuscrito) {
                    h.suscribirse(colaborador);
                    RepoHeladera.getInstance().guardar(h);
                    System.out.println("========================Comenzando suscripcion =========================");
                }
            }
            ctx.redirect("/heladeras");
        }

    }


