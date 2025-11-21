package com.example.springexample.services;
import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repositories.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthorCRUDServiceTest {

    private final AuthorRepository repository = Mockito.mock(AuthorRepository.class);
    private final AuthorCRUDService service = new AuthorCRUDService(repository);


    @Test
    @DisplayName("Test getById")
    public void getByIdTest(){
        int authorId = 1;
        Author author = new Author();
        author.setId(authorId);
        author.setComments(List.of());
        when(repository.findById(authorId)).thenReturn(Optional.of(author));
        AuthorDto authorDto = service.getById(authorId);
        assertEquals(authorId, authorDto.getId());
        verify(repository, times(1)).findById(authorId);
    }

    @Test
    @DisplayName("Test getALL")
    public void getAllTest(){
        int authorId = 1;
        Long rating = 1234L;
        String firstName = "Alex";
        String lastName = "Pushkin";
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setId(authorId);
        author.setRating(rating);
        author.setLastName(lastName);
        author.setFirstName(firstName);
        authors.add(author);
        when(repository.findAll()).thenReturn(authors);
        Collection<AuthorDto> authorDtos = service.getAll();
        AuthorDto first = authorDtos.isEmpty() ? null : authorDtos.iterator().next();
        assertEquals(authors.size(), authorDtos.size());
        assertEquals(authorId, first.getId());
        assertEquals(rating, first.getRating());
        assertEquals(firstName, first.getFirstName());
        assertEquals(lastName, first.getLastName());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test create")
    public void createTest(){
        int authorId = 1;
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorId);
        service.create(authorDto);
        ArgumentCaptor<Author> captor = ArgumentCaptor.forClass(Author.class);
        verify(repository, times(1)).save(captor.capture());
        Author saved = captor.getValue();
        assertEquals(authorId, saved.getId());
    }

    @Test
    @DisplayName("Test update")
    public void updateTest(){
        AuthorDto authorDto = new AuthorDto();
        int id = 1;
        authorDto.setId(id);
        service.update(authorDto);
        ArgumentCaptor<Author> captor = ArgumentCaptor.forClass(Author.class);
        verify(repository, times(1)).save(captor.capture());
        Author saved = captor.getValue();
        assertEquals(id, saved.getId());
    }

    @Test
    @DisplayName("Test delete")
    public void deleteByIdTest(){
        int id = 1;
        service.delete(id);
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }
}
