package com.x.translate_tool.domain.dto;

import lombok.Data;

/**
 * 描述:
 *
 * <p>创建日期: 2025/4/11
 *
 * @author dingsheng
 */
public class TranslateDTO {

  private String text;

  private String targetLanguage;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTargetLanguage() {
    return targetLanguage;
  }

  public void setTargetLanguage(String targetLanguage) {
    this.targetLanguage = targetLanguage;
  }
}
