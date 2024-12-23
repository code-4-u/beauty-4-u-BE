package com.beauty4u.backend.inquiry.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReplyReqDTO;
import com.beauty4u.backend.inquiry.command.application.service.InquiryReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryReplyCommandController {

    private final InquiryReplyService inquiryReplyService;

    @Operation(summary = "QnA 답변(A) 등록", description = "회원(관리자)이 답변을 등록한다.")
    @PostMapping("/{inquiryId}/reply")
    public ResponseEntity<ApiResponse<Void>> saveQnaReply(
            @PathVariable Long inquiryId,
            @RequestBody @Valid QnaReplyReqDTO qnaReplyReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        inquiryReplyService.saveQnaReply(loginUserCode, inquiryId, qnaReplyReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_REPLY_SAVE_SUCCESS);
    }

    @Operation(summary = "QnA 답변(A) 수정", description = "회원(관리자)이 답변을 수정한다.")
    @PutMapping("/{inquiryReplyId}/reply")
    public ResponseEntity<ApiResponse<Void>> updateQnaReply(
            @PathVariable Long inquiryReplyId,
            @RequestBody @Valid QnaReplyReqDTO qnaReplyReqDTO) {

        inquiryReplyService.updateQnaReply(inquiryReplyId, qnaReplyReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_REPLY_UPDATE_SUCCESS);
    }

    @Operation(summary = "QnA 답변(A) 삭제", description = "회원(관리자)이 답변을 삭제한다.")
    @DeleteMapping("/{inquiryReplyId}/reply")
    public ResponseEntity<ApiResponse<Void>> deleteQnaReply(
            @PathVariable Long inquiryReplyId) {

        inquiryReplyService.deleteQnaReply(inquiryReplyId);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_REPLY_DELETE_SUCCESS);
    }
}
