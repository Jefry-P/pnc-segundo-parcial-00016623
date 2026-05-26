package com.uca.pncsegundoparcialbiblioteca.repository;

import com.uca.pncsegundoparcialbiblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String title);
    boolean existsByIsbn(String isbn);
    List<Book> findAllByGenre(String genre);
}
