package com.my.ex.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.my.ex.SortResponse;
import com.my.ex.dto.BoardDto;
import com.my.ex.dto.BoardPagingDto;
import com.my.ex.dto.LikeDto;
import com.my.ex.dto.map.KakaoMapRequestDto;
import com.my.ex.service.BoardService;
import com.my.ex.service.LikeService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private KakaoMapRequestDto kakao;
	
	// 게시글 등록 페이지
	@RequestMapping("/createPage")
	public String createPage() {
		return "/board/createPage";
	}
	
	// 게시글 등록
	@RequestMapping(value = "/createBoard", method = RequestMethod.POST )
	public String createBoard(BoardDto dto, RedirectAttributes rttr) {
		boolean create = service.createBoard(dto);
		String result = "false";
		
		if(create) result = "true";
		rttr.addFlashAttribute("createResult", result);
		return "redirect:paging";
	}
	
	// 게시글 상세보기
	@RequestMapping("/detailBoard")
	public String detailBoard(HttpServletRequest request, Model model, HttpSession session) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String userId = (String)session.getAttribute("userId"); 
		updateHitCount(bId);
		BoardDto dto = service.detailBoard(bId);
		boolean isLiked = likeService.isLiked(bId, userId);
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		List<BoardDto> replyList = service.replyList(bGroup);
		model.addAttribute("dto", dto);
		model.addAttribute("replyList", replyList);
		model.addAttribute("isLiked", isLiked);
		return "/board/detailPage";
	}
	
	// 게시글 수정 페이지
	@RequestMapping("/updatePage")
	public String updatePage(@RequestParam("bId") int bId, Model model) {
		BoardDto dto = service.detailBoard(bId);
		model.addAttribute("dto", dto);
		return "/board/updatePage";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(HttpServletRequest request, RedirectAttributes rttr) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		BoardDto dto = new BoardDto();
		dto.setbId(bId);
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		boolean update = service.updateBoard(dto);
		String result = "false";
		if(update) result = "true";
		rttr.addFlashAttribute("updateResult", result);
		return "redirect:detailBoard?bId=" + bId + "&bGroup=" + bGroup;
	}
	
	// 게시글 삭제
	@RequestMapping("/deleteBoard")
	public String deleteBoard(@RequestParam("bId")int bId, RedirectAttributes rttr) {
		boolean deleteResult = service.deleteBoard(bId);
		String result = "false";
		
		if(deleteResult) result = "true";
		rttr.addFlashAttribute("deleteResult", result);
		return "redirect:paging";
	}
	
	// 조회수
	@RequestMapping("/updateHitCount")
	public void updateHitCount(@RequestParam("bId")int bId) {
		service.updateHitCount(bId);
	}
	
	// 게시글 좋아요
	@RequestMapping(value =  "/addLike", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> addLike(@RequestParam("bId")int bId, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		service.incrementLikesCount(bId);
		LikeDto dto = new LikeDto();
		dto.setBId(bId);
		dto.setUserId(userId);
		likeService.addLike(dto);
		int totalLikes = service.getTotalLikes(bId);
		return new ResponseEntity<>(totalLikes, HttpStatus.OK);
	}
	
	// 게시글 좋아요 취소
	@RequestMapping(value =  "/removeLike", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> removeLike(@RequestParam("bId")int bId, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		service.decrementLikesCount(bId);
		LikeDto dto = new LikeDto();
		dto.setBId(bId);
		dto.setUserId(userId);
		likeService.removeLike(dto);
		int totalLikes = service.getTotalLikes(bId);
		return new ResponseEntity<>(totalLikes, HttpStatus.OK);
	}
	
	// 댓글
	@RequestMapping(value = "/replyInsert", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<BoardDto>> replyInsert(HttpSession session, BoardDto dto){
		String userId = (String)session.getAttribute("userId");
		dto.setbName(userId);
		service.replyInsert(dto);
		List<BoardDto> replyList = service.replyList(dto.getbGroup());
		service.updateCommentCount(dto.getbGroup());
		return new ResponseEntity<>(replyList, HttpStatus.OK);
	}
	
	// 답글
	@RequestMapping(value = "/replyChildInsert", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<BoardDto>> replyChildInsert(HttpSession session, BoardDto dto) {
		String userId = (String)session.getAttribute("userId");
		dto.setbName(userId);
		service.replyChildInsert(dto);
		List<BoardDto> replyList = service.replyList(dto.getbGroup());
		service.updateCommentCount(dto.getbGroup());
//		List<BoardDto> replyChildList = service.replyChildList(dto.getbGroup());
		return new ResponseEntity<>(replyList, HttpStatus.OK);
	}
	
	// 정렬 유지한채로 페이지 이동 시 -> jsp(html)로 이동
	@RequestMapping("/paging")
	public String paging(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
									  @RequestParam(value = "searchGubun", required = false, defaultValue = "") String searchGubun,
									  @RequestParam(value = "searchText", required = false, defaultValue = "") String searchText,
									  @RequestParam(value = "sortType", required = false, defaultValue = "latest") String sortType) {
		List<BoardDto> pagingList = service.pagingList(page, searchGubun, searchText, sortType);
		BoardPagingDto pageDto = service.paingParam(page);
		for(BoardDto dto : pagingList) {
			dto.setSearchGubun(searchGubun);
			dto.setSearchText(searchText);
			dto.setSortType(sortType);
			
			// HTML 이스케이프 처리
			String escapedContent = HtmlUtils.htmlEscape(dto.getbContent());
			dto.setbContent(escapedContent);
		}
		model.addAttribute("boardList", pagingList);
		model.addAttribute("paging", pageDto);
		return "/board/pagingList";
	}
	
	// 현재 페이지에서 정렬 시 -> js(ajax)로 이동
	@ResponseBody
	@RequestMapping("/paging/ajax")
	public ResponseEntity<SortResponse> pagingAjax(Model model,
										@RequestParam(value = "page", required = false, defaultValue = "1") int page,
										@RequestParam(value = "searchGubun", required = false, defaultValue = "") String searchGubun,
										@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText,
										@RequestParam(value = "sortType", required = false, defaultValue = "latest") String sortType) {
		List<BoardDto> pagingList = service.pagingList(page, searchGubun, searchText, sortType);
		BoardPagingDto pageDto = service.paingParam(page);
		for(BoardDto dto : pagingList) {
			dto.setSearchGubun(searchGubun);
			dto.setSearchText(searchText);
			dto.setSortType(sortType);
			
			// HTML 이스케이프 처리
			String escapedContent = HtmlUtils.htmlEscape(dto.getbContent());
			dto.setbContent(escapedContent);
		}
		SortResponse response = new SortResponse(pagingList, pageDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// 이미지업로드(1) - 업로드한 이미지를 로컬에 저장
	@ResponseBody
	@RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
	public void imgUpload(MultipartHttpServletRequest multiRequest, 
						  HttpServletRequest request,
						  HttpServletResponse response) {
		try {
			final String real_save_path = "C:\\server_program\\imgUploadTest\\";

            // 폴더가 없을 경우 생성
            File saveFolder = new File(real_save_path);
            if (!saveFolder.exists() || saveFolder.isFile()) {
                saveFolder.mkdirs();
            }

            final Map<String, MultipartFile> files = multiRequest.getFileMap();
            MultipartFile fileload = files.get("upload");
            if (fileload == null || fileload.isEmpty()) {
                System.out.println("파일이 없습니다.");
                return;
            }
            
            // filename 취득
            String fileName = fileload.getOriginalFilename();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            Random ran = new Random(System.currentTimeMillis());
            fileName = System.currentTimeMillis() + "_" + (int)(ran.nextDouble() * 10000) + "." + ext;

            // 폴더 경로 설정
            String newfilename = real_save_path + fileName;
            fileload.transferTo(new File(newfilename)); // 파일 저장 실행

            // getImageForContents() 메서드 실행
            JSONObject outData = new JSONObject();
            outData.put("uploaded", true);
            outData.put("url", request.getScheme() + "://" + request.getServerName() + "/board/getImageForContents?fileNm=" + fileName);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(outData.toString());
        } catch (Exception e) {
        	System.out.println("*error: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	// 이미지업로드(2) - 로컬에 저장된 이미지를 사용자에게 제공(텍스트창)
	@RequestMapping(value = "/getImageForContents")
    public void getImageForContents(@RequestParam("fileNm") String fileNm, HttpServletResponse response) throws Exception {
        String fileStr = "C:\\server_program\\imgUploadTest\\"; 

        FileInputStream fis = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;

        try {
            File file = new File(fileStr, fileNm);
            if (!file.exists()) { 
            	System.out.println("fileStr: " + fileStr);
            	System.out.println("fileNm: " + fileNm);
            	System.out.println("file not exists");
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 파일이 없으면 404 오류 응답
                return;
            }

            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);
            bStream = new ByteArrayOutputStream();

            int imgByte;
            while ((imgByte = in.read()) != -1) {
                bStream.write(imgByte);
            }

            String type = "";
            String ext = fileNm.substring(fileNm.lastIndexOf(".") + 1).toLowerCase();
            type = "jpg".equals(ext) ? "image/jpeg" : "image/" + ext;
            response.setHeader("Content-Type", type);
            response.setContentLength(bStream.size());
            bStream.writeTo(response.getOutputStream());
            response.getOutputStream().flush();
        } finally {
            // 자원 해제
            if (bStream != null) bStream.close();
            if (in != null) in.close();
            if (fis != null) fis.close();
        }
    }
	
	// 조회순 정렬
	@ResponseBody
	@RequestMapping("/sort_hit")
	public ResponseEntity<SortResponse> sort_hit(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
												 @RequestParam(value = "searchGubun", required = false, defaultValue = "") String searchGubun,
												 @RequestParam(value= "searchText", required = false, defaultValue = "") String searchText) {
		List<BoardDto> sort_hitPagingList = service.sort_hitPagingList(page, searchGubun, searchText);
		BoardPagingDto pageDto = service.paingParam(page);
		for(BoardDto dto : sort_hitPagingList) {
			// HTML 이스케이프 처리
			String escapedContent = HtmlUtils.htmlEscape(dto.getbContent());
			dto.setbContent(escapedContent);
		}
		SortResponse response = new SortResponse();
		response.setSort_hitPagingList(sort_hitPagingList);
		response.setPageDto(pageDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	// 카카오 key
	@ResponseBody
	@RequestMapping(value =  "/getKakaoKey", method = RequestMethod.POST)
	public ResponseEntity<String> getKakaoKey() {
		return new ResponseEntity<>(kakao.getJsKey(), HttpStatus.OK);
	}
	
}
