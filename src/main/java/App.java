import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/changedisplay", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ChangeMachine myChangeMachine = new ChangeMachine();
      Float flUserAmount = Float.valueOf(request.queryParams("userAmount"));
      model.put("userAmount", request.queryParams("userAmount"));
      model.put("flUserAmount", flUserAmount);
      model.put("template", "templates/changedisplay.vtl");
      model.put("result", myChangeMachine.makeChange(flUserAmount));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
