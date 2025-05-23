package com.pard.hw3.book.service;

import com.pard.hw3.book.dto.RequestBookDto;
import com.pard.hw3.book.dto.ResponseBookDto;
import com.pard.hw3.book.entity.Book;
import com.pard.hw3.book.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;


    public void save(RequestBookDto requestBookDto){
        Book book = Book.builder()
                .bookId(requestBookDto.getBookId())
                .bookName(requestBookDto.getBookName())
                .author(requestBookDto.getAuthor())
                .publisher(requestBookDto.getPublisher())
                .year(requestBookDto.getYear())
                .build();
        bookRepo.save(book);
    }

    public List<ResponseBookDto> readAll(){
        List<Book> books = bookRepo.findAll();
        List<ResponseBookDto> responseBookDtos = books.stream().map(book ->
                                ResponseBookDto.builder()
                                .Id(book.getId())
                                .bookId(book.getBookId())
                                .bookName(book.getBookName())
                                .author(book.getAuthor())
                                .publisher(book.getPublisher())
                                .year(book.getYear())
                                .build()).toList();
        return responseBookDtos;
    }

    public ResponseBookDto readById(Long Id){
        Optional<Book> optionalBook = bookRepo.findById(Id);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseBookDto.builder()
                    .Id(book.getId())
                    .bookId(book.getBookId())
                    .year(book.getYear())
                    .bookName(book.getBookName())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .build();
        }
        else{
            throw new IllegalArgumentException("해당 순서의 도서를 찾을 수 없습니다. ID: " + Id);
        }
    }
    public ResponseBookDto readByBookName(String bookName){

        if (bookName == null || bookName.trim().isEmpty()) {//trim은 스페이스 있는거 글자 중간 제외하고 다 삭제
            throw new IllegalArgumentException("도서 이름은 비어 있을 수 없습니다.");
        }

        Optional<Book> optionalBook = bookRepo.findByBookName(bookName.trim());
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseBookDto.builder()
                    .Id(book.getId())
                    .bookId(book.getBookId())
                    .year(book.getYear())
                    .bookName(book.getBookName())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .build();
        }
        else{
            throw new IllegalArgumentException("해당 이름의 도서를 찾을 수 없습니다. BookName: " + bookName);
        }
    }
    @Transactional
    public void updateById(Long Id,RequestBookDto requestBookDto){
        Optional<Book> optionalBook = bookRepo.findById(Id);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.update(requestBookDto);
            bookRepo.save(book);// 이게 없으면 db에 저장 안됨!!!
        }
        else{
            throw new IllegalArgumentException("해당 순서의 도서를 찾을 수 없습니다. ID: " + Id);
        }
    }
    @Transactional
    public void updateByName(String bookName,RequestBookDto requestBookDto){
        Optional<Book> optionalBook = bookRepo.findByBookName(bookName);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.update(requestBookDto);
            bookRepo.save(book);// 이게 없으면 db에 저장 안됨!!!
        }
        else{
            throw new IllegalArgumentException("해당 이름의 도서를 찾을 수 없습니다. BookName: " + bookName);
        }
    }
    @Transactional
    public void deleteById(Long Id){
        Optional<Book> optionalBook = bookRepo.findById(Id);
        if(optionalBook.isPresent()) {
            bookRepo.deleteById(Id);

        }
        else{
            throw new IllegalArgumentException("해당 순서의 도서를 찾을 수 없습니다. ID: " + Id);
        }
    }
    @Transactional
    public void deleteByBookName(String bookName){
        Optional<Book> optionalBook = bookRepo.findByBookName(bookName);
        if(optionalBook.isPresent()) {
            bookRepo.deleteByBookName(bookName);
        }
        else{
            throw new IllegalArgumentException("해당 이름의 도서를 찾을 수 없습니다. BookName: " + bookName);
        }
    }
    public List<ResponseBookDto> searchHarryPotterBooks() {
        List<Book> books = bookRepo.findByBookNameContainingOrderByYearAsc("해리포터");

        return books.stream().map(book -> ResponseBookDto.builder()
                .Id(book.getId())
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .year(book.getYear())
                .build()
        ).toList();
    }
    public List<ResponseBookDto> searchNoHarryPotterBooks() {
        List<Book> books = bookRepo.findByBookNameNotContainingOrderByIdAsc("해리포터");

        return books.stream().map(book -> ResponseBookDto.builder()
                .Id(book.getId())
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .year(book.getYear())
                .build()
        ).toList();
    }
    public List<ResponseBookDto> searchYearLessthan2015() {
        List<Book> books = bookRepo.YearLessThanEqualOrderByIdAsc(2015);

        return books.stream().map(book -> ResponseBookDto.builder()
                .Id(book.getId())
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .year(book.getYear())
                .build()
        ).toList();
    }
}
