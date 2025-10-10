package com.example.springexample.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

//@RestController
public class DefaultController {

//    private Map<Integer, String> todo;
//
//    public DefaultController(){
//        todo = new TreeMap<>();
//        todo.put(1, "one");
//        todo.put(2, "two");
//    }
//
//    @GetMapping(path="/todo")
//    public Map<Integer, String> getList (){
//        return todo;
//    }
//
//    @PostMapping(path = "/todo")
//    public ResponseEntity addItem (@RequestParam String item){
//        if (item == null || item.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        int newKey = todo.isEmpty() ? 1 : Collections.max(todo.keySet()) + 1;
//        todo.put(newKey, item);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = "/todo/{id}")
//    public ResponseEntity editItem (@PathVariable int id, @RequestParam String item){
//        //защита не помешала бы
//        todo.put(id, item);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/todo/{id}")
//    public ResponseEntity deleteItem(@PathVariable int id){
//        todo.remove(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/todo/{id}")
//    public String getOneItem (@PathVariable int id){
//        return todo.get(id);
//    }

}
