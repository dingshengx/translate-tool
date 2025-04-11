package com.x.translate_tool.controller;

import com.x.translate_tool.domain.dto.TranslateDTO;
import com.x.translate_tool.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 翻译控制器
 *
 * <p>创建日期: 2025/4/10
 *
 * @author dingsheng
 */
@RestController
@RequestMapping("/api/translate")
public class TranslateController {

  @Autowired private TranslateService translateService;

  @PostMapping()
  public String translate(@RequestBody TranslateDTO translateDTO) {
    return translateService.translate(translateDTO);
  }
}
