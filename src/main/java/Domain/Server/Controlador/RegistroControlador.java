package Domain.Server.Controlador;

import Domain.Colaborador.*;
import Domain.Colaborador.MedioDeContacto.InstantMessageApp;
import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Repositorios.RepoColaboradores;
import Domain.Repositorios.RepoUsuario;
import Domain.Tarjeta.TarjetaColaborador;
import Domain.Ubicacion.Direccion;
import Domain.Ubicacion.Localidad;
import Domain.Ubicacion.Provincia;
import Domain.Usuarios.Usuario;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.*;

public class RegistroControlador {

  public void eleccion(Context ctx){

      TemplateRender.render(ctx, "/eleccionRegistro.html.hbs", Map.of());

    }
  public void eleccionColaborador(Context ctx){

      String eleccion=ctx.formParam("eleccion");
      if(Integer.valueOf(eleccion)==0){
        ctx.redirect("/registro/persona");
      }else{
        ctx.redirect("/registro/negocio");
      }

  }


  public void pantallaPersonaHumana(Context ctx){
    Map<String,Object> model=new HashMap<>();
    model.put("provincias", Provincia.values());
    model.put("localidades", Localidad.values());
    TemplateRender.render(ctx, "/registroPersonaHumana.html.hbs", model);
  }
  public void crearUsuarioPersonaHumana(Context ctx) {
    String email = ctx.formParam("correo");
    String contrasenia = ctx.formParam("contrasenia");
    String repiteContrasenia = ctx.formParam("repetirContrasenia");
    String fechaNacimiento = ctx.formParam("fecha");
    String nombre = ctx.formParam("nombre");
    String apellido = ctx.formParam("apellido");
    String celular = ctx.formParam("celular");
    String provincia = ctx.formParam("provincia");
    String localidad = ctx.formParam("localidad");
    String domicilio = ctx.formParam("domicilio");
    String dni = ctx.formParam("DNI");
    String tipoDocumento = ctx.formParam("tipoDocumento");

    String codigo = ctx.formParam("codigoAdmin");

    Usuario u = new Usuario(nombre,apellido, email, contrasenia);

    if (contrasenia.equals(repiteContrasenia)) {
      List<Mediodecontacto> medios=new ArrayList<>();
      medios.add(new Mediodecontacto(email, InstantMessageApp.MAIL));
      medios.add(new Mediodecontacto(celular, InstantMessageApp.WHATSAPP));

    Colaborador c=new PersonaHumana(nombre,apellido, LocalDate.parse(fechaNacimiento),new Direccion(Provincia.valueOf(provincia),Localidad.valueOf(localidad),domicilio)
            ,medios, TipoDocumento.valueOf(tipoDocumento),Long.parseLong(dni));
    if(codigo.equals("1234")){
      c.setEsAdmin(true);
    }
      Random random = new Random();
      int numeroAleatorio = random.nextInt(1000000) + 1;
      TarjetaColaborador t=new TarjetaColaborador(String.valueOf(numeroAleatorio), LocalDate.now());

      System.out.println("Paso esto==================================");
      if(RepoUsuario.getInstance().buscarPorNombre(email).size()!=0){
        System.out.println("El usuario ya existe");

        }else{

        t.setColaborador(c);
        c.setTarjeta(t);

        RepoColaboradores.getInstance().guardar(c);

        u.asignarColaborador(c);


        RepoUsuario.getInstance().guardar(u);
        }

      ctx.sessionAttribute("usuarioID", String.valueOf(c.getId()));
      ctx.sessionAttribute("esAdmin", u.getAsignado().getEsAdmin());

      ctx.redirect("/colaboracion");
      } else {
      ctx.redirect("/");
        }

    }

  public void pantallaPersonaJuridica(Context ctx){
    Map<String,Object> model=new HashMap<>();
    model.put("provincias", Provincia.values());
    model.put("localidades", Localidad.values());
    model.put("rubros", Rubro.values());
    model.put("tiposrazonsocial", TipoRazonSocial.values());

    TemplateRender.render(ctx, "/registroPersonaJuridica.html.hbs", model);
  }

  public void crearUsuarioPersonaJuridica(Context ctx) {
    String email = ctx.formParam("correo");
    String contrasenia = ctx.formParam("contrasenia");
    String repiteContrasenia = ctx.formParam("repetirContrasenia");
    String razonsicial = ctx.formParam("razonsocial");
    String rubro = ctx.formParam("rubro");
    String tipoRazonsocial = ctx.formParam("tiporazonsocial");
    String provincia = ctx.formParam("provincia");
    String localidad = ctx.formParam("localidad");
    String domicilio = ctx.formParam("domicilio");
    String codigo = ctx.formParam("codigoAdmin");

    Usuario u = new Usuario(null,null, email, contrasenia);

    Random random = new Random();
    int numeroAleatorio = random.nextInt(1000000) + 1;



    if (contrasenia.equals(repiteContrasenia)) {
      List<Mediodecontacto> medios=new ArrayList<>();
      medios.add(new Mediodecontacto(email, InstantMessageApp.MAIL));

      Colaborador c=new PersonaJuridica(razonsicial,medios,new Direccion(Provincia.valueOf(provincia),Localidad.valueOf(localidad),domicilio)
          ,Rubro.valueOf(rubro), TipoRazonSocial.valueOf(tipoRazonsocial));
      if(codigo.equals("1234")){
        c.setEsAdmin(true);
      }
      TarjetaColaborador t=new TarjetaColaborador(String.valueOf(numeroAleatorio), LocalDate.now());

      if(RepoUsuario.getInstance().buscarPorNombre(email).size()!=0){
        System.out.println("El usuario ya existe");
        ctx.redirect("/");
      }else{
        u.asignarColaborador(c);

        c.setTarjeta(t);


        RepoColaboradores.getInstance().guardar(c);

        RepoUsuario.getInstance().guardar(u);

      }

      ctx.sessionAttribute("usuarioID", String.valueOf(u.getAsignado().getId()));
      ctx.sessionAttribute("esAdmin", u.getAsignado().getEsAdmin());

      ctx.redirect("/colaboracion");
    } else {
      ctx.redirect("/");
    }

  }



}
