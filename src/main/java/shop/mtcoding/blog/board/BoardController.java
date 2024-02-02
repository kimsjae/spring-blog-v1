package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.user.User;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import shop.mtcoding.blog.user.UserRequest;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    // http://localhost:8080?page=0
    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        System.out.println("페이지 : " + page);
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        boolean first = currentPage == 0 ? true : false;

        int totalCount = boardRepository.countBoardId();
        int paging = 3;

        int remainCount = totalCount % paging;
        int totalPage;
        if (remainCount == 0) {
            totalPage = totalCount/paging;
        } else {
            totalPage = totalCount/paging+1;
        }




        boolean last = currentPage+1 == totalPage ? true : false;
        request.setAttribute("first", first);
        request.setAttribute("last", last);



        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id : " + id);

        BoardResponse.DetailDTO responseDTO = boardRepository.findId(id);
        request.setAttribute("board", responseDTO);

        // 권한 체크 (여기서 실패하면 403을 줌)
        // 1. 해당 페이지의 주인 여부
        boolean owner = false;

        // 2. 작성자 userId 확인하기
        int boardUserId = responseDTO.getUserId();

        // 3. 로그인 여부 체크 - 로그인이 안 됐으면 무조건 false기 때문
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null && boardUserId == sessionUser.getId()) { // 로그인 했고 작성자ID와 게시글ID가 같으면
            owner = true;
        }

        request.setAttribute("owner", owner);


        return "board/detail";
    }
}
