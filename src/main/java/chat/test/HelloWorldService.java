package chat.test;

import com.google.gson.Gson;

import javax.ws.rs.*;

@Path("/")
public class HelloWorldService {
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public String register(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);
        // TODO persist user to database

        return "{success: true, user: " + gson.toJson(user) + "}";
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/login")
    public String login(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);
        // TODO get user from database

        return "{success: true}";
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/view")
    public String view(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);
        // TODO get user from database

        return "{success: true}";
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/edit")
    public String edit(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);
        // TODO edit user on database

        return "{success: true}";
    }
}