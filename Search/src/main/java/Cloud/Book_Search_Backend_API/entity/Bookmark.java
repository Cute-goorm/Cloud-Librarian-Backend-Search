package Cloud.Book_Search_Backend_API.entity;

import Cloud.Book_Search_Backend_API.dto.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long bookId; // 책 ID를 저장
    private Long userId; // 사용자의 ID를 저장


    public Bookmark(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
