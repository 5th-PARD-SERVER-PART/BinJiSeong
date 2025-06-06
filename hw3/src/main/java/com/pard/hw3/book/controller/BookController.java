package com.pard.hw3.book.controller;

import com.pard.hw3.book.dto.RequestBookDto;
import com.pard.hw3.book.dto.ResponseBookDto;
import com.pard.hw3.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("")//저장
    public String save(@RequestBody RequestBookDto requestBookDto){
        bookService.save(requestBookDto);
        return "저장완료";
    }
    @GetMapping("")//모든 리스트 보기
    public List<ResponseBookDto> readAll(){
        return bookService.readAll();
    }
    @GetMapping("/id/{Id}")//들어온 순서대로 id가 자동생성 되고 이 id로 찾기
    public ResponseBookDto readById(@PathVariable Long Id){
        return bookService.readById(Id);
    }
    @GetMapping("/name/{bookName}")//책 이름으로 찾기
    public ResponseBookDto readByBookName(@PathVariable String bookName){
        return bookService.readByBookName(bookName);
    }
    @PatchMapping("/id/{Id}")
    public String updateById(@PathVariable Long Id, @RequestBody RequestBookDto requestBookDto){
        bookService.updateById(Id,requestBookDto);
        return "입력한 "+Id+"번 내용 수정완료!";
    }
    @PatchMapping("/name/{bookName}")
    public String updateByName(@PathVariable String bookName, @RequestBody RequestBookDto requestBookDto){
        bookService.updateByName(bookName,requestBookDto);
        return "입력한 "+bookName+" 내용 수정완료!";
    }
    @DeleteMapping("id/{Id}")
    public String deleteById(@PathVariable Long Id){
        bookService.deleteById(Id);
        return "입력한 "+Id+"번 내용 삭제완료!";
    }
    @DeleteMapping("name/{bookName}")
    public String deleteByBookName(@PathVariable String bookName){
        bookService.deleteByBookName(bookName);
        return "입력한 "+bookName+" 내용 삭제완료!";
    }
    @GetMapping("/search/harrypotter")
    public List<ResponseBookDto> searchHarryPotterBooks() {
        return bookService.searchHarryPotterBooks();
    }
    @GetMapping("/search/noharrypotter")
    public List<ResponseBookDto> searchNoHarryPotterBooks() {
        return bookService.searchNoHarryPotterBooks();
    }
    @GetMapping("/search/less2015")
    public List<ResponseBookDto> searchYearLessthan2015() {
        return bookService.searchYearLessthan2015();
    }

}
