package com.faq.mapper;

import com.faq.pojo.Faqs;

import java.util.List;
import java.util.Map;

public interface FaqsMapper {

    List<Faqs> getBy(Map<String,Object> map);

    Faqs getByTitle(Integer id);

    int insert(Faqs faqs);

}
