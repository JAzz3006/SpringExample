package com.example.springexample.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(Integer id){
        super(String.format("Comment with is %d not found", id));
    }

}
