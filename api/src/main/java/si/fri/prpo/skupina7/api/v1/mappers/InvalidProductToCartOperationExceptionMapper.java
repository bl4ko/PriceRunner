package si.fri.prpo.skupina7.api.v1.mappers;

import si.fri.prpo.skupina7.exceptions.InvalidProductToCartOperationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidProductToCartOperationExceptionMapper implements ExceptionMapper<InvalidProductToCartOperationException> {
    @Override
    public Response toResponse(InvalidProductToCartOperationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}").build();
    }
}
}
