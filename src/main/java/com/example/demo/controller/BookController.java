package com.example.demo.controller;

import com.example.demo.entity.Books;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BookController
{
     @Autowired
     BookService bookservice;

     @PostMapping("/addbook")
     @Operation(summary = "add Book",description = "add the new book data")
     public ResponseEntity<String> addBook(@RequestBody Books books)
     {
          bookservice.addBook(books);
         return new ResponseEntity<>("Book added sucessfully", HttpStatus.CREATED);

     }
    @GetMapping("/allbooks")
     public ResponseEntity<List<Books>> getAllBooks()
    {
            List<Books> books = bookservice.getAllBooks();
            return  new ResponseEntity<>(books,HttpStatus.OK);
    }



    @GetMapping("/bookid/{bookid}")
    public ResponseEntity<?> getBookById(@PathVariable("bookid") int bookid)
    {
       Optional<Books> b = bookservice.getBookById(bookid);
       if(b.isPresent())
       {
           return new ResponseEntity<>(b.get(),HttpStatus.OK);

       }
       else
       {
          return  new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
       }
   }

   @DeleteMapping("/bookid/{bookid}")
   public ResponseEntity<String> deleteBook(@PathVariable("bookid") int bookid)
   {
       if(bookservice.deleteBook(bookid))
       {
           return new ResponseEntity<>("Book removed sucessfully", HttpStatus.OK);
       }
       else
       {
           return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
       }
   }

   @PutMapping("/updatebook")
   public ResponseEntity<String> update(@RequestBody Books book)
       {
           Books updatebook = bookservice.update(book);

           if(updatebook!=null)
           {
               return new ResponseEntity<>("Book updated successfully",HttpStatus.OK);
           }
           else
           {
               return  new ResponseEntity<>("Book not Found",HttpStatus.NOT_FOUND);
           }



       }



       







}
