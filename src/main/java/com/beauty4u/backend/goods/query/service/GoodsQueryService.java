package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.goods.query.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private GoodsDomainService goodsDomainService;

    public List<GoodsRepository> findAllGoods(){
        return goodsRepository.findAll();
    }
}
