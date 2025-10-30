package com.example.springexample.controllers;
import com.example.springexample.dto.AuthorDto;
import com.example.springexample.services.AuthorCRUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/author")
@Slf4j
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorCRUDService authorService;

    @GetMapping("/{id}")
    public AuthorDto getById (@PathVariable Integer id){
        return authorService.getById(id);
    }

    @GetMapping
    public Collection<AuthorDto> getAll(){
        return authorService.getAll();
    }

    @PostMapping
    public void create (@RequestBody AuthorDto authorDto){
        authorService.create(authorDto);
    }

    @PutMapping("/{id}")
    public void update (@RequestBody AuthorDto authorDto, @PathVariable Integer id){
        authorDto.setId(id);
        authorService.update(authorDto);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id) {
        authorService.delete(id);
    }
}
