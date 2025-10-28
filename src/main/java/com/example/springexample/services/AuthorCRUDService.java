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
        return null;
    }

    @Override
    public Collection<AuthorDto> getAll() {
        return List.of();
    }

    @Override
    public void create(AuthorDto item) {

    }

    @Override
    public void update(AuthorDto item) {

    }

    @Override
    public void delete(Integer id) {

    }

    public static Author mapToEntity(AuthorDto authorDto){

    }

    public static AuthorDto mapToDto(Author author){

    }
}
