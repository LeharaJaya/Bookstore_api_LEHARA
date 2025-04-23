package exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class BookNotFoundMapper implements ExceptionMapper<BookNotFoundException> {
    @Override
    public Response toResponse(BookNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Book Not Found");
        error.put("message", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
