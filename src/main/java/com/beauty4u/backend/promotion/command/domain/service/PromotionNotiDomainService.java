package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.util.MailUtil;
import com.beauty4u.backend.promotion.command.application.dto.PromotionEmailResult;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionNoti;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionNotiRepository;
import com.beauty4u.backend.promotion.query.dto.FindPromotionNotiTargetResDTO;
import com.beauty4u.backend.promotion.query.service.PromotionNotiQueryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PromotionNotiDomainService {

    private final PromotionNotiRepository promotionNotiRepository;
    private final PromotionNotiQueryService promotionNotiQueryService;
    private final ModelMapper modelMapper;
    private final MailUtil mailUtil;

    /* FakeSMTP 서버 사용 */
    public PromotionEmailResult savePromotionNotiFakeSMTP(Long promotionId, Long analysisId) {
        List<FindPromotionNotiTargetResDTO> targets = promotionNotiQueryService.findPromotionNotiTarget(promotionId, analysisId);

        LocalDateTime sendDateTime = LocalDateTime.now();
        List<CompletableFuture<Void>> emailFutures = new ArrayList<>();
        Map<String, String> emailMap = new HashMap<>();  // 실패 추적용

        for(FindPromotionNotiTargetResDTO target : targets) {
            String content = target.getCustomerName()
                            + " 고객님~! \n"
                            + "[" + target.getPromotionTitle() + "]\n"
                            + " 행사 맞이하여 추천상품 \n"
                            + "[" + target.getGoodsName() + "] \n"
                            + target.getDiscountRate() + "% 할인된 가격! \n"
                            + " 특가로 확인해보세요~! \n"
                            + "언제나 이용해주셔서 감사합니다! \n"
                            + "beauty4u 드림.";

            CompletableFuture<Void> emailFuture = mailUtil
                    .sendPromotionFakeSmtpAsync(target.getCustomerEmail(), "beauty4u - [" + target.getPromotionTitle() + "] 프로모션 안내", content)
                    .exceptionally(throwable -> {
                        emailMap.put(target.getCustomerEmail(), throwable.getMessage());
                        return null;
                    });

            emailFutures.add(emailFuture);

            SavePromotionNotiDTO save = new SavePromotionNotiDTO();
            save.setCustomerCode(target.getCustomerCode());
            save.setPromotionNotiContent(content);
            save.setPromotionNotiSentDate(sendDateTime);

            PromotionNoti saveNoti = modelMapper.map(save, PromotionNoti.class);

            // DB 저장
            promotionNotiRepository.save(saveNoti);
        }

        try {
            CompletableFuture.allOf(emailFutures.toArray(new CompletableFuture[0]))
                    .get(60, TimeUnit.SECONDS);
        } catch (Exception e) {
           System.out.println("Some promotion emails failed to send" + e);
        }

        return PromotionEmailResult.builder()
                .totalCount(targets.size())
                .failCount(emailMap.size())
                .successCount(targets.size() - emailMap.size())
                .failEmails(new ArrayList<>(emailMap.keySet()))
                .build();
    }

    /* 실제 Google SMTP 서버 사용 */
    public PromotionEmailResult sendPromotionNotiMail(Long promotionId, Long analysisId) {
        List<FindPromotionNotiTargetResDTO> targets = promotionNotiQueryService.findPromotionNotiTarget(promotionId, analysisId);

        String promotionTitle = targets.get(0).getPromotionTitle();
        String promotionGoods = targets.get(0).getGoodsName();

        LocalDateTime sendDateTime = LocalDateTime.now();
        List<CompletableFuture<Void>> emailFutures = new ArrayList<>();
        Map<String, String> emailMap = new HashMap<>();  // 실패 추적용

        String content01 = "황희순 고객님~! \n"
                         + "[" + promotionTitle + "]\n"
                         + "행사 맞이하여 추천상품\n"
                         + "[" + promotionGoods + "]\n"
                         + "25% 할인된 가격!\n"
                         + "특가로 확인해보세요~!\n"
                         + "언제나 이용해주셔서 감사합니다!\n"
                         + "beauty4u 드림. \n"
                         + "본 메일은 프로모션 이메일 테스트 용입니다.";

        CompletableFuture<Void> emailFuture1 = mailUtil.sendPromotionGmailAsync("gmltns885@gmail.com", "beauty4u - [2024 크리스마스 기념 윈터 빅세일] 프로모션 안내", content01)
                         .exceptionally(throwable -> {
                            emailMap.put("gmltns885@gmail.com", throwable.getMessage());
                            return null;
                         });

        emailFutures.add(emailFuture1);

        SavePromotionNotiDTO save1 = new SavePromotionNotiDTO();
        save1.setCustomerCode("CU0000001");
        save1.setPromotionNotiContent(content01);
        save1.setPromotionNotiSentDate(sendDateTime);

        PromotionNoti saveNoti1 = modelMapper.map(save1, PromotionNoti.class);

        // DB 저장
        promotionNotiRepository.save(saveNoti1);

        String content02 = "신민철 고객님~! \n"
                + "[" + promotionTitle + "]\n"
                + "행사 맞이하여 추천상품\n"
                + "[" + promotionGoods + "]\n"
                + "25% 할인된 가격!\n"
                + "특가로 확인해보세요~!\n"
                + "언제나 이용해주셔서 감사합니다!\n"
                + "beauty4u 드림. \n"
                + "본 메일은 프로모션 이메일 테스트 용입니다.";

        CompletableFuture<Void> emailFuture2 = mailUtil.sendPromotionGmailAsync("dbeoalscjf98@naver.com", "beauty4u - [2024 크리스마스 기념 윈터 빅세일] 프로모션 안내", content02)
                         .exceptionally(throwable -> {
                            emailMap.put("dbeoalscjf98@naver.com", throwable.getMessage());
                            return null;
                        });

        emailFutures.add(emailFuture2);

        SavePromotionNotiDTO save2 = new SavePromotionNotiDTO();
        save2.setCustomerCode("CU0000003");
        save2.setPromotionNotiContent(content02);
        save2.setPromotionNotiSentDate(sendDateTime);

        PromotionNoti saveNoti2 = modelMapper.map(save2, PromotionNoti.class);

        // DB 저장
        promotionNotiRepository.save(saveNoti2);

        String content03 = "안세령 고객님~! \n"
                + "[" + promotionTitle + "]\n"
                + "행사 맞이하여 추천상품\n"
                + "[" + promotionGoods + "]\n"
                + "25% 할인된 가격!\n"
                + "특가로 확인해보세요~!\n"
                + "언제나 이용해주셔서 감사합니다!\n"
                + "beauty4u 드림. \n"
                + "본 메일은 프로모션 이메일 테스트 용입니다.";

        CompletableFuture<Void> emailFuture3 = mailUtil.sendPromotionGmailAsync("sr1094@naver.com", "beauty4u - [2024 크리스마스 기념 윈터 빅세일] 프로모션 안내", content03)
                         .exceptionally(throwable -> {
                            emailMap.put("sr1094@naver.com", throwable.getMessage());
                            return null;
                        });

        emailFutures.add(emailFuture3);

        SavePromotionNotiDTO save3 = new SavePromotionNotiDTO();
        save3.setCustomerCode("CU0000004");
        save3.setPromotionNotiContent(content02);
        save3.setPromotionNotiSentDate(sendDateTime);

        PromotionNoti saveNoti3 = modelMapper.map(save3, PromotionNoti.class);

        // DB 저장
        promotionNotiRepository.save(saveNoti3);


        return PromotionEmailResult.builder()
                .totalCount(3)
                .failCount(emailMap.size())
                .successCount(3 - emailMap.size())
                .failEmails(new ArrayList<>(emailMap.keySet()))
                .build();
    }

    public void updatePromotionNoti(Long id, UpdatePromotionNotiDTO updatePromotionNotiDTO) {
        PromotionNoti promotionNoti = promotionNotiRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PROMOTION_NOT_FOUND));
        modelMapper.map(updatePromotionNotiDTO, promotionNoti);
    }

    public void deletePromotionNoti(Long id) {
        promotionNotiRepository.deleteById(id);
    }
}
