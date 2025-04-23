package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Book;
import service.BookService;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private final BookService service = new BookService();

    @GET
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") int id) {
        return service.getBook(id);
    }

    @POST
    public Response addBook(Book book, @Context UriInfo uriInfo) {
        Book created = service.addBook(book);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book book) {
        return service.updateBook(id, book);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        service.deleteBook(id);
        return Response.noContent().build();
    }
}
