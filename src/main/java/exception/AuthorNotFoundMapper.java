package exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class AuthorNotFoundMapper implements ExceptionMapper<AuthorNotFoundException> {
    @Override
    public Response toResponse(AuthorNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Author Not Found");
        error.put("message", ex.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
