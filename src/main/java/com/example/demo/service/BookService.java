package com.example.demo.service;


import com.example.demo.entity.Books;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Books addBook(Books book)
    {

        try {
             return bookRepository.save(book);
        }
        catch (DataIntegrityViolationException ex)
        {
            throw  new BookNotFoundException("Database constraint voilated"+ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    public Books getBookById(int id)
    {

        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with  id:" + id, HttpStatus.NOT_FOUND));


    }

    public boolean deleteBook(int id)
    {

        try
        {
            if(bookRepository.existsById(id))
            {
                bookRepository.deleteById(id);
                return true;

            }
            else
            {
                throw new BookNotFoundException("Book not found with id:"+id,HttpStatus.NOT_FOUND);
            }

        }
        catch(DataIntegrityViolationException ex)
        {
            throw new BookNotFoundException("Database constraint voilated" + ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    public Books update(Books b)
    {
        try {
            if (bookRepository.existsById(b.getBookid())) {
                return bookRepository.save(b);
            } else
            {
                throw   new BookNotFoundException("Book with id"+b.getBookid()+"not found",HttpStatus.NOT_FOUND);
            }
        }
            catch (DataIntegrityViolationException ex) {

            throw new BookNotFoundException("Database constraint voilated" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }





    }




