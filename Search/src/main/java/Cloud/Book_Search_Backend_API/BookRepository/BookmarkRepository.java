package Cloud.Book_Search_Backend_API.BookRepository;

import Cloud.Book_Search_Backend_API.dto.Member;
import Cloud.Book_Search_Backend_API.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByMember(Member member);
    List<Bookmark> findByBookId(Long bookId);
    Optional<Bookmark> findByUserIdAndBookId(Long userId, Long bookId);
}
