package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Author;
import model.Book;
import service.AuthorService;
import service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorService authorService = new AuthorService();
    private final BookService bookService = new BookService();

    @GET
    public List<Author> getAll() {
        return authorService.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    public Author get(@PathParam("id") int id) {
        return authorService.getAuthor(id);
    }

    @POST
    public Response create(Author author, @Context UriInfo uriInfo) {
        Author created = authorService.addAuthor(author);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(created.getId()));
        return Response.created(builder.build()).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Author update(@PathParam("id") int id, Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") int authorId) {
        return bookService.getAllBooks().stream()
                .filter(book -> book.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }
}
