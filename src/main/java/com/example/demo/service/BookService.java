package com.example.demo.service;


import com.example.demo.entity.Books;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    public List<Books> getAllBooks()
{
    List<Books> booksList = new ArrayList<>();
    bookRepository.findAll().forEach(booksList::add);
    return booksList;

}
    public  void addBook(Books book)
    {
        bookRepository.save(book);
    }
    public Optional<Books> getBookById(int id)
    {

        return bookRepository.findById(id);

    }

    public boolean deleteBook(int id)
    {
        if(bookRepository.existsById(id))
        {
            bookRepository.deleteById(id);
            return true;

        }
        else
        {
            return false;
        }

    }
    public Books update(Books b)
    {
         if(bookRepository.existsById(b.getBookid()))
         {
                return bookRepository.save(b);
         }
         else
         {
             return null;
         }
    }



}
