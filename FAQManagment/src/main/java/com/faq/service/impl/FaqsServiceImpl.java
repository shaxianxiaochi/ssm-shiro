package com.faq.service.impl;

import com.faq.mapper.FaqsMapper;
import com.faq.pojo.Faqs;
import com.faq.service.FaqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("faqService")
public class FaqsServiceImpl implements FaqsService{

    @Autowired(required = false)
    private FaqsMapper faqsMapper;

    @Override
    public List<Faqs> getBy(Map<String, Object> map) {
        return faqsMapper.getBy(map);
    }

    @Override
    public Faqs getByTitle(Integer id) {
        return faqsMapper.getByTitle(id);
    }

    @Override
    public int insert(Faqs faqs) {
        return faqsMapper.insert(faqs);
    }


}
