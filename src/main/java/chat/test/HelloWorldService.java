package chat.test;

import com.google.gson.Gson;
import hibernate.DaoUser;
import hibernate.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloWorldService {
    
    DaoUser daoUser = DaoUser.getInstance();
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/register")
    public String register(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);
        // TODO persist user to database

        boolean b = DaoUser.getInstance().presist(user);
        if(b){
        return "{success: true}";
        }
        return "{success: false, error: \"mobile already exists\"}";
        
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