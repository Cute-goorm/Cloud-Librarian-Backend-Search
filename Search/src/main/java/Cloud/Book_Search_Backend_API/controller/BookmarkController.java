package Cloud.Book_Search_Backend_API.controller;

import Cloud.Book_Search_Backend_API.dto.BookmarkRequest;
import Cloud.Book_Search_Backend_API.entity.Bookmark;
import Cloud.Book_Search_Backend_API.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    ////////////////////////책 목록조회

    @GetMapping("/books")
    public ResponseEntity<String> getBooks(@RequestParam String query) {
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx"
                + "?ttbkey="
                + "&Query=" + query
                + "&QueryType=Title"
                + "&MaxResults=10"
                + "&start=1"
                + "&SearchTarget=Book"
                + "&output=JS"
                + "&Version=20131101";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);
        return ResponseEntity.ok(response);
    }

    ////////////////////////추가


    @PostMapping("/add")
    public ResponseEntity<String> addBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        if (bookmarkRequest.getLoginId() != null && !bookmarkRequest.getLoginId().isEmpty()) {
            bookmarkService.addBookmark(bookmarkRequest.getLoginId(), bookmarkRequest.getBookId());
        } else {
            bookmarkService.addBookmark(bookmarkRequest.getUserId(), bookmarkRequest.getBookId());
        }
        return ResponseEntity.ok("즐겨찾기에 추가되었습니다.");
    }

    ////////////////////////삭제


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        if (bookmarkRequest.getLoginId() != null && !bookmarkRequest.getLoginId().isEmpty()) {
            bookmarkService.deleteBookmark(bookmarkRequest.getLoginId(), bookmarkRequest.getBookId());
        } else {
            bookmarkService.deleteBookmark(bookmarkRequest.getUserId(), bookmarkRequest.getBookId());
        }
        return ResponseEntity.ok("즐겨찾기에서 삭제되었습니다.");
    }


    ////////////////////////유저즐겨찾기 조회


    @GetMapping("/user/{id}")
    public List<Bookmark> getBookmarksByUser(@PathVariable Long id) {
        return bookmarkService.getBookmarksByUser(id);
    }


    ////////////////////////책즐겨찾기 조회


    @GetMapping("/book/{id}")
    public List<Bookmark> getBookmarksByBook(@PathVariable Long id) {
        return bookmarkService.getBookmarksByBook(id);
    }
}
