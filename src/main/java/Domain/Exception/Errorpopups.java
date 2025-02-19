package Domain.Exception;

import io.javalin.http.Context;

public class Errorpopups {

    public static void erroresyredireccion(Context ctx, String errorMsg, String redirectUrl) {
        ctx.sessionAttribute("error", errorMsg);
        ctx.redirect(redirectUrl);
    }
}
