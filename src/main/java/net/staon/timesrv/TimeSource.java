package net.staon.timesrv;

import net.staon.timesrv.data.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "time_src")
@Path("/time")
public class TimeSource {
    @Autowired
    TimeRepository times;

    public static final String REALTIME_GROUP = "realtime";

    @GET
    @Path("/{group}")
    @Produces("application/json")
    public Response getTime(@PathParam("group") String group) {
        Time time;
        if(Objects.equals(group, REALTIME_GROUP)) {
            /* -- real time */
            long unixTime = System.currentTimeMillis();
            time = new Time(unixTime / 1000L, (unixTime % 1000L) * 1000L);
        }
        else {
            /* -- fake time */
            var timeDb = times.findById(group);
            if(timeDb.isPresent()) {
                time = new Time(timeDb.get());
            }
            else {
                return Response.status(404, "Unknown group").build();
            }
        }
        return Response.status(200).entity(time).build();
    }

    @POST
    @Path("/{group}")
    @Consumes("application/json")
    public Response setTime(@PathParam("group") String group, Time time) {
        if(Objects.equals(group, REALTIME_GROUP)) {
            return Response.status(400, "The realtime group cannot be set").build();
        }
        times.save(new net.staon.timesrv.data.Time(group, time));
        return Response.status(200).build();
    }
}
