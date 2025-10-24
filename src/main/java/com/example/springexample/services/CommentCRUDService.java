package com.example.springexample.services;
import com.example.springexample.dto.CommentDto;
import com.example.springexample.entity.Comment;
import com.example.springexample.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentCRUDService implements CRUDService<CommentDto>{
//    private final TreeMap<Integer, CommentDto> storage = new TreeMap<>();
    private final CommentRepository repository;

    @Override
    public CommentDto getById(Integer id) {
        log.info("Get by ID " + id);
//        if (!storage.containsKey(id)) {
//            System.out.println("No such ID " + id);
//            return null;
//        }
        Comment comment = repository.findById(id).orElseThrow();
        return mapToDto(comment);
    }

    @Override
    public Collection<CommentDto> getAll() {
        log.info("Get all");
        return repository.findAll().stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CommentDto item) {
        log.info("Create");
        repository.save(mapToEntity(item));
    }

    @Override
    public void update(CommentDto item) {
        log.info("Update");
//        if (!storage.containsKey(id)) {
//            System.out.println("No such id to update: " + id);
//            return;
//        }
        repository.save(mapToEntity(item));
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
//        if (!storage.containsKey(id)){
//            System.out.println("No such id to delete: " + id);
//            return;
//        }
        repository.deleteById(id);
    }

    public static CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setAuthor(comment.getAuthor());
        return commentDto;
    }

    public static Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setAuthor(commentDto.getAuthor());
        return comment;
    }
}
