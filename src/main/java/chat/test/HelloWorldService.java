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

        boolean b = DaoUser.getInstance().presist(user);
        if(b) {
            return "{\"success\": true}";
        } else {
            return "{\"success\": false, \"error\": \"mobile already exists\"}";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/login")
    public String login(String req) {
        Gson gson = new Gson();
        User loggingInUser = gson.fromJson(req, User.class);
        User foundUser = daoUser.getByMobile(loggingInUser.getMobile());
        if (foundUser == null || ! foundUser.getPassword().equals(loggingInUser.getPassword())) {
            return "{\"success\": false, \"error\": \"Invalid mobile number or password\"}";
        } else {
            return "{\"success\": true, \"user\": " + gson.toJson(foundUser) + "}";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/view")
    public String view(String req) {
        Gson gson = new Gson();
        User loggingInUser = gson.fromJson(req, User.class);
        User foundUser = daoUser.getByMobile(loggingInUser.getMobile());
        if (foundUser == null) {
            return "{\"success\": false, \"error\": \"Invalid mobile number\"}";
        } else {
            return "{\"success\": true, \"user\": " + gson.toJson(foundUser) + "}";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/edit")
    public String edit(String req) {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);

        boolean b = DaoUser.getInstance().update(user);
        if(b) {
            return "{\"success\": true, \"user\": " + gson.toJson(user) + "}";
        } else {
            return "{\"success\": false, \"error\": \"invalid user data\"}";
        }
    }
}