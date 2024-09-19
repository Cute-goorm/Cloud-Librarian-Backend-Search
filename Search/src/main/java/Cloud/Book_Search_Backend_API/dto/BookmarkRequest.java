package Cloud.Book_Search_Backend_API.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRequest {
    private String loginId; // 로그인 ID
    private Long userId;    // 사용자 ID
    private Long bookId;    // 책 ID

}
