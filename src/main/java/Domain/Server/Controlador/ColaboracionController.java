package Domain.Server.Controlador;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaJuridica;
import Domain.Colaborador.TipoDeColaboracion.*;
import Domain.Colaborador.Transferencia.FrecuenciaDeDonacion;
import Domain.Exception.Errorpopups;
import Domain.Heladera.Heladera;
import Domain.Persona.PersonaVulnerable;
import Domain.Repositorios.*;
import Domain.Server.Enums.Filtros;
import Domain.Server.Enums.Tipocolaboracion;
import Domain.Tarjeta.Tarjeta;
import Domain.Ubicacion.Direccion;
import Domain.Ubicacion.Localidad;
import Domain.Ubicacion.Provincia;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ColaboracionController {
    public void principal(Context ctx) {
        String usuarioID = ctx.sessionAttribute("usuarioID");
        Map<String, Object> model = ctx.attribute("sharedData");
        String dato = ctx.queryParam("dato");
        Set<Heladera> filtradas;
        if (usuarioID == null) {
            ctx.redirect("/");
        } else {

            if (ctx.queryParam("filtro") == null) {
                filtradas = RepoHeladera.getInstance().obtenerTodos();
                model.put("heladeras", filtradas);

                TemplateRender.render(ctx, "/colaboraciones.html.hbs", model);
            } else {

                try {
                    switch (Filtros.valueOf(ctx.queryParam("filtro"))) {
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

                }catch (NumberFormatException e) {
                    model.put("error", "El filtro debe ser un número válido.");
                } catch (IllegalArgumentException e) {
                    model.put("error", "El dato ingresado no es válido.");
                } catch (Exception e) {
                    model.put("error", "Ocurrió un error inesperado. Inténtalo nuevamente.");
                }
                TemplateRender.render(ctx, "/colaboraciones.html.hbs", model);

            }

        }
    }
    public void eleccionColaboracion(Context ctx){
        String eleccion = ctx.formParam("eleccion");
        if(eleccion==null){
            TemplateRender.render(ctx, "/colaboraciones.html.hbs", Map.of());
        }else{
            switch(Tipocolaboracion.valueOf(eleccion)){
                case DINERO: //Donacion de dinero
                    ctx.redirect("/colaboracion/dinero");
                    break;
                case VIANDA: //DonarVianda
                    List<String> heladeraIds = ctx.formParams("heladeraId"); // Obtener lista de IDs de heladeras
                    if(heladeraIds.size()==0){
                        ctx.redirect("/colaboracion");
                    }else {
                        Heladera heladera = RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraIds.get(0))); // Consultar el repositorio

                        if (heladera != null) {

                            ctx.redirect("/colaboracion/vianda?heladeraID=" + heladera.getId());

                        } else {
                            ctx.status(404).result("Heladera no encontrada");
                        }
                    }
                    break;
                case DISTRIBUCION: //Distribuir Vianda
                   
                    List<String> heladeraIds2 = ctx.formParams("heladeraId"); 

                    if(heladeraIds2.size()<2){
                        ctx.redirect("/colaboracion");
                    }else {
                        Heladera heladera1 = RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraIds2.get(0))); // Consultar el repositorio
                        Heladera heladera2 = RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraIds2.get(1)));


                        ctx.redirect( "/colaboracion/distribucion?heladeraId=" + heladera1.getId() + "&heladeraId2=" + heladera2.getId());

                    }
                    break;
                case HELADERA : //Donar Heladera
                    Colaborador colaborador= RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));
                    if(colaborador instanceof  PersonaJuridica){
                        ctx.redirect("/colaboracion/heladera");
                    }else {
                        ctx.redirect("/colaboracion");
                        //mensaje de error
                    }

                    break;

            }
        }

    }
    public void pantalla_donar_dinero(Context ctx){
        Map<String, Object> model = ctx.attribute("sharedData");
        model.put("frecuencias",FrecuenciaDeDonacion.values());
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        TemplateRender.render(ctx, "/donarDinero.html.hbs", model);
    }
    public void donar_dinero(Context ctx){

        String monto = ctx.formParam("monto");
        String tarjeta = ctx.formParam("tarjeta");
        String frecuencia = ctx.formParam("frecuencia");
        if(monto==null || tarjeta==null || frecuencia ==null){
            Map<String, Object> model = new HashMap<>();
            model.put("frecuencias",FrecuenciaDeDonacion.values());
            model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
            TemplateRender.render(ctx, "/donarDinero.html.hbs", model);
        }else{

            TipoDeColaboracion t=new DonarDinero(Integer.valueOf(monto), LocalDate.now(), FrecuenciaDeDonacion.valueOf(frecuencia));

            Colaborador colaborador= RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));

            RepoColaboraciones.getInstance().guardar(t);
            colaborador.realizarColaboracion(t);
            RepoColaboradores.getInstance().guardar(colaborador);

            ctx.redirect("/colaboracion");
        }


    }

    public void pantalla_donar_vianda(Context ctx){
        String h = ctx.queryParam("heladeraID");

        Map<String,Object> model=ctx.attribute("sharedData");
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        Heladera heladera1 = RepoHeladera.getInstance().buscarPorId(Long.parseLong(h));

        String error = ctx.sessionAttribute("error");
        if (error != null) {
            model.put("error", error);
            ctx.sessionAttribute("error", null); // Eliminar el error después de mostrarlo
        }
        model.put("heladera", heladera1);
        model.put("idHeladera1", heladera1.getId());
        model.put("direccionHeladera1", heladera1.getDireccion().getDireccion());


        TemplateRender.render(ctx, "/donarVianda.html.hbs", model);
    }

    public void donar_vianda(Context ctx){
        String fechaCaducidad = ctx.formParam("fecha");
        String peso = ctx.formParam("peso");
        String calorias = ctx.formParam("calorias");
        String heladeraid = ctx.formParam("heladera");


        Colaborador colaborador=RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));
        Heladera heladera= RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraid));

        if (heladera.getEstadoHeladera().getHeladeraAveriada()) {
            Errorpopups.erroresyredireccion(ctx,"⚠️ La heladera está averiada. No se puede distribuir en este momento."
                    ,"/colaboracion/vianda?heladeraID=" + heladera.getId());
            return;
        }

        TipoDeColaboracion t=new DonarVianda();

        Map<String,String> datos=new HashMap<>();


        datos.put("fechaCaducidad", fechaCaducidad);
        datos.put("peso", peso);
        datos.put("calorias", calorias);

        RepoColaboraciones.getInstance().guardar(t);
        System.out.println("===========================Rompio Aca porque no se creo correctamente=============================");

        TipoDeColaboracion abtenido=RepoColaboraciones.getInstance().buscarPorId(t.getId());

        System.out.println( "SE obtubo "+abtenido.getId()+" "+abtenido.getFecha());

        colaborador.getTarjeta().agregarSolicitud(abtenido,heladera,datos,1);


        ctx.redirect("/colaboracion");

    }
    public void pantalla_distribuir_viandas(Context ctx){

        String h = ctx.queryParam("heladeraId");
        String h2 = ctx.queryParam("heladeraId2");

        Map<String,Object> model=ctx.attribute("sharedData");
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        Heladera heladera1 = RepoHeladera.getInstance().buscarPorId(Long.parseLong(h)); // Consultar el repositorio
        Heladera heladera2 = RepoHeladera.getInstance().buscarPorId(Long.parseLong(h2));


        String error = ctx.sessionAttribute("error");
        if (error != null) {
            model.put("error", error);
            ctx.sessionAttribute("error", null); // Eliminar el error después de mostrarlo
        }
        model.put("heladera1", heladera1);
        model.put("heladera2", heladera2);


        TemplateRender.render(ctx, "/distribuirVianda.html.hbs", model);
    }

    public void distribuir_viandas(Context ctx){
        String motivo = ctx.formParam("motivo");
        String cantidad = ctx.formParam("cantidad");
        String heladeraid = ctx.formParam("origen");
        String heladeraid2 = ctx.formParam("destino");

        if(heladeraid.equals(heladeraid2)){
            ctx.redirect("/colaboracion");
            return;
        }


        Colaborador colaborador=RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));
        Heladera heladera= RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraid));
        Heladera heladera2= RepoHeladera.getInstance().buscarPorId(Long.parseLong(heladeraid2));

        if (heladera2.getEstadoHeladera().getHeladeraAveriada()) {
            Errorpopups.erroresyredireccion(ctx,"⚠️ La heladera está averiada. No se puede distribuir en este momento."
                    ,"/colaboracion/distribucion?heladeraID=" + heladera.getId());
            return;
        }
        if(heladera.getEstado().getHeladeraAveriada() && heladera.getViandasEnHeladera().size()!=0){
            TipoDeColaboracion t=new DistribuirVianda();


            Map<String,String> datos=new HashMap<>();
            datos.put("motivo", motivo);
            datos.put("cantidad", cantidad);
            datos.put("origen", heladeraid);
            datos.put("destino", heladeraid2);

            RepoColaboraciones.getInstance().guardar(t);

            colaborador.getColaboraciones().add(t);

            RepoColaboradores.getInstance().guardar(colaborador);

            colaborador.getTarjeta().agregarSolicitud(t,heladera,datos,Integer.valueOf(cantidad));
        }


        ctx.redirect("/colaboracion");

    }
    public void pantalla_donar_heladera(Context ctx){

        Map<String,Object> model=ctx.attribute("sharedData");
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        model.put("provincias", Provincia.values());
        model.put("localidades", Localidad.values());
        TemplateRender.render(ctx, "/donarHeladera.html.hbs", model);
    }

    public void donar_heladera(Context ctx){

        String nombre = ctx.formParam("nombre");
        String capacidad = ctx.formParam("capacidad");
        String latitud = ctx.formParam("latitud");
        String longitud = ctx.formParam("longitud");
        String provincia = ctx.formParam("provincia");
        String localidad = ctx.formParam("localidad");
        String direccion = ctx.formParam("direccion");
        String tminimo = ctx.formParam("tminimo");
        String tmaximo = ctx.formParam("tmaximo");


        Colaborador colaborador=RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));

        Heladera heladera=new Heladera(nombre,Integer.valueOf(capacidad),latitud,longitud,
            new Direccion(Provincia.valueOf(provincia), Localidad.valueOf(localidad), direccion)
            ,LocalDate.now(), BigDecimal.valueOf(Integer.valueOf(tmaximo)),BigDecimal.valueOf(Integer.valueOf(tminimo)));


        TipoDeColaboracion t=new HacerseCargoDeHeladera(heladera);


        PersonaJuridica j=(PersonaJuridica) colaborador;

        j.agregarHeladera(heladera);

        RepoColaboraciones.getInstance().guardar(t);

        colaborador.realizarColaboracion(t);

        RepoColaboradores.getInstance().guardar(colaborador);

        ctx.redirect("/colaboracion");
    }

    public void pantalla_persona_vulnerable(Context ctx){
        Map<String,Object> model=ctx.attribute("sharedData");
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        model.put("provincias", Provincia.values());
        model.put("localidades", Localidad.values());
        model.put("tarjetas", RepoTarjetas.getInstance().obtenerTodos().stream().filter(t->!t.getEnUso()).collect(Collectors.toList()));
        TemplateRender.render(ctx, "/RegistrarPersonaVulnerable.html.hbs", model);
    }

    public void registro_persona_vulnerable(Context ctx){
        String nombre = ctx.formParam("nombre");
        String fechaNacimiento = ctx.formParam("fecha");
        String provincia = ctx.formParam("provincia");
        String localidad = ctx.formParam("localidad");
        String domicilio = ctx.formParam("direccion");
        String dni = ctx.formParam("dni");
        String cantidadHijos = ctx.formParam("cantidadHijos");
        String tarjeta = ctx.formParam("tarjeta");


        Colaborador co = RepoColaboradores.getInstance().buscarPorId(Long.parseLong(ctx.sessionAttribute("usuarioID")));

        Tarjeta tj= RepoTarjetas.getInstance().buscarPorNumeroTarjeta(tarjeta);

        PersonaVulnerable p=new PersonaVulnerable(nombre, LocalDate.parse(fechaNacimiento),LocalDate.now(),
                Optional.of(new Direccion(Provincia.valueOf(provincia), Localidad.valueOf(localidad),domicilio)),
                Optional.of(dni),Integer.valueOf(cantidadHijos));

        p.asignarTarjeta(tj);
        tj.setEsnUso(true);
        tj.setFechaRegistro(LocalDate.now());

        RepositorioPersonasVulnerables.getInstance().guardar(p);

        RegistrarUnaPersonaVulnerable r=new RegistrarUnaPersonaVulnerable(p,tj);

        RepoColaboraciones.getInstance().guardar(r);

        co.realizarColaboracion(r);

        RepoColaboradores.getInstance().guardar(co);

        RepoTarjetas.getInstance().guardar(tj);




        ctx.redirect("/colaboracion");
    }
    public void solicitarTarjetas(Context ctx){
        Tarjeta nuevaTarjeta = new Tarjeta();

        RepoTarjetas.getInstance().guardar(nuevaTarjeta);
        ctx.redirect("/colaboracion/persona-vulnerable");
    }
}
