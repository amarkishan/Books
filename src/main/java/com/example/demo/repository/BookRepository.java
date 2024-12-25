package com.example.demo.repository;

import com.example.demo.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Books,Integer>
{


}
