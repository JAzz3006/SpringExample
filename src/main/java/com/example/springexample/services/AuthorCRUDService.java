package com.example.springexample.services;
import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCRUDService implements CRUDService<AuthorDto>{

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getById(Integer id) {
        log.info("Get by id: " + id);
        return mapToDto(authorRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<AuthorDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(AuthorDto authorDto) {
        authorRepository.save(mapToEntity(authorDto));
    }

    @Override
    public void update(AuthorDto authorDto) {
        authorRepository.save(mapToEntity(authorDto));
    }

    @Override
    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    public static Author mapToEntity(AuthorDto authorDto){
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setRating(authorDto.getRating());
        author.setComments(authorDto.getComments()
                .stream()
                .map(CommentCRUDService::mapToEntity)
                .toList()
        );
        return author;
    }

    public static AuthorDto mapToDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setRating(author.getRating());
        authorDto.setComments(author.getComments()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList()
        );
        return authorDto;

    }
}
