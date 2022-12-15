package si.fri.prpo.skupina7.api.v1.mappers;

import si.fri.prpo.skupina7.exceptions.InvalidCartOperationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidCartOperationExceptionMapper implements ExceptionMapper<InvalidCartOperationException> {
    @Override
    public Response toResponse(InvalidCartOperationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}").build();
    }
}
}
