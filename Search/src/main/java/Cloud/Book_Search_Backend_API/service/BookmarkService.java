package Cloud.Book_Search_Backend_API.service;

import Cloud.Book_Search_Backend_API.BookRepository.BookmarkRepository;
import Cloud.Book_Search_Backend_API.entity.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberService memberService;

    @Transactional
    public void addBookmark(String loginId, Long bookId) {
        Member member = (Member) memberService.getLoginMemberByLoginId(loginId);
        if (bookmarkRepository.findByUserIdAndBookId((long) member.getModifiers(), bookId).isPresent()) {
            throw new IllegalArgumentException("이미 즐겨찾기된 책입니다.");
        }
        Bookmark bookmark = Bookmark.builder()
                .member((Cloud.Book_Search_Backend_API.dto.Member) member)
                .userId((long) member.getModifiers())
                .bookId(bookId)
                .build();
        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteBookmark(String loginId, Long bookId) {
        Member member = (Member) memberService.getLoginMemberByLoginId(loginId); // MemberService 메서드
        Bookmark bookmark = bookmarkRepository.findByUserIdAndBookId((long) member.getModifiers(), bookId)
                .orElseThrow(() -> new IllegalArgumentException("즐겨찾기에 존재하지 않는 책입니다."));
        bookmarkRepository.delete(bookmark);
    }

    public List<Bookmark> getBookmarksByUser(Long memberId) {
        Member member = (Member) memberService.getLoginMemberById(memberId); // MemberService 메서드
        return bookmarkRepository.findByMember((Cloud.Book_Search_Backend_API.dto.Member) member);
    }

    public List<Bookmark> getBookmarksByBook(Long bookId) {
        return bookmarkRepository.findByBookId(bookId);
    }

    // 중복 메서드 제거
    public void addBookmark(Long userId, Long bookId) {
        if (bookmarkRepository.findByUserIdAndBookId(userId, bookId).isPresent()) {
            throw new IllegalArgumentException("이미 즐겨찾기된 책입니다.");
        }
        Bookmark bookmark = new Bookmark(userId, bookId);
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long userId, Long bookId) {
        Bookmark bookmark = bookmarkRepository.findByUserIdAndBookId(userId, bookId)
                .orElseThrow(() -> new IllegalArgumentException("즐겨찾기에 존재하지 않는 책입니다."));
        bookmarkRepository.delete(bookmark);
    }
}