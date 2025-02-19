package Domain.Server.Controlador;

import Domain.Exception.Errorpopups;
import Domain.Repositorios.RepoUsuario;
import Domain.Usuarios.Usuario;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;


public class UsuarioControlador {



  public void login(Context ctx){
    String error = ctx.sessionAttribute("error");
    Map<String, Object> model=new HashMap<>();
    if(ctx.formParam("usuarioID")!=null){
      ctx.redirect("/colaboracion");
    }else{
      if (error != null) {
        model.put("error", error);
        ctx.sessionAttribute("error", null);
      }
      TemplateRender.render(ctx, "login.html.hbs", model);
    }

  }


  public void inicioSesion(Context ctx) {
    String username = ctx.formParam("correo");
    String password = ctx.formParam("contrasenia");
    String error = ctx.sessionAttribute("error");
    Map<String, Object> model=new HashMap<>();

    Usuario o = RepoUsuario.getInstance().buscarPorNombre(username).get(0);

    if (o != null && o.getContrasenia().compareTo(password) == 0) {

      ctx.sessionAttribute("usuarioID", Long.toString(o.getAsignado().getId()));
      ctx.sessionAttribute("esAdmin", o.getAsignado().getEsAdmin());
      ctx.redirect("/colaboracion");
    } else {
      Errorpopups.erroresyredireccion(ctx,"⚠️ Credenciales incorrectas. Por favor, intenta de nuevo."
              ,"/");
    }
  }


  public void cerrarSesion(Context ctx) {
    ctx.sessionAttribute("usuarioID", null);
    ctx.redirect("/");
  }
}
