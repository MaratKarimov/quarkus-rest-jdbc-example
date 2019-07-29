package ru.maratk.resources;

import ru.maratk.dao.AccumulatorDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class AccumulatorResource {

    @Inject
    AccumulatorDao accumulatorDao;

    @Path("/plus")
    @GET
    public Response plus(@QueryParam("value") int value){
        try {
            accumulatorDao.plus(value);
            return Response
                    .ok(accumulatorDao.findCurrent())
                    .header("Content-type", MediaType.APPLICATION_JSON)
                    .build();
        } catch(final SQLException e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .header("Content-type", MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/minus")
    @GET
    public Response minus(@QueryParam("value") int value){
        try {
            accumulatorDao.minus(value);
            return Response
                    .ok(accumulatorDao.findCurrent())
                    .header("Content-type", MediaType.APPLICATION_JSON)
                    .build();
        } catch(final SQLException e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .header("Content-type", MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}