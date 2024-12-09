package com.beauty4u.backend.inquiry.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.application.dto.FaqReqDTO;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Faq;
import com.beauty4u.backend.inquiry.command.domain.repository.FaqRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqDomainService {

    private final FaqRepository faqRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveFaq(String loginUserCode, FaqReqDTO faqReqDTO) {

        Faq faq = modelMapper.map(faqReqDTO, Faq.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        faq.modifyUser(user);

        try {
            faqRepository.save(faq);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_FAQ);
        }
    }

    public void updateFaq(Long faqId, FaqReqDTO faqReqDTO) {

        String title = faqReqDTO.getFaqTitle();
        String content = faqReqDTO.getFaqContent();

        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FAQ));

        faq.modifyFaq(title, content);
    }

    public void deleteFaq(Long faqId) {

        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FAQ));

        faqRepository.delete(faq);
    }
}
