package Domain.Server.Controlador;

import Domain.Importador.ImportadorColaboracionCsv;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CargaColaboracionController {
    public void pantalla_carga_datos(Context ctx){
        Map<String, Object> model = new HashMap<>();
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));
        TemplateRender.render(ctx, "/cargarColaboraciones.html.hbs", model);
    }

    public void cargar_archivo(Context ctx) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("esAdmin",ctx.sessionAttribute("esAdmin"));

        UploadedFile file = ctx.uploadedFile("archivo_csv"); // "archivo_csv" es el nombre del input del formulario

        ImportadorColaboracionCsv importador = new ImportadorColaboracionCsv();

        importador.importar(file.content());

        TemplateRender.render(ctx, "/cargarColaboraciones.html.hbs", model);


        }
    }

