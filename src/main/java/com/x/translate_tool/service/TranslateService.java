package com.x.translate_tool.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.x.translate_tool.domain.dto.TranslateDTO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

/**
 * 描述: 翻译服务
 *
 * <p>创建日期: 2025/4/10
 *
 * @author dingsheng
 */
@Slf4j
@Service
public class TranslateService {

  private static final String API_URL = "http://localhost:11434/api/generate";
  private static final OkHttpClient client = new OkHttpClient();

  public String translate(TranslateDTO translateDTO) {
    String text = translateDTO.getText();
    String targetLanguage = translateDTO.getTargetLanguage();
    String prompt = buildPrompt(text, targetLanguage);

    JSONObject json = new JSONObject();
    json.set("model", "gemma-translate");
    json.set("prompt", prompt);
    json.set("stream", Boolean.FALSE);

    RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));

    Request request = new Request.Builder().url(API_URL).post(body).build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        return "Request failed: " + response.code();
      }

      String responseBody = response.body().string();
      JSONObject resultJson = JSONUtil.parseObj(responseBody);
      return resultJson.getStr("response");
    } catch (Exception e) {
      //      log.error("Error during translation: {}", e.getMessage());
      return "Error during translation";
    }
  }

  private String buildPrompt(String input, String direction) {
    if (direction.equals("English")) {
      return "Translate to English: " + input;
    } else if (direction.equals("Chinese")) {
      return "Translate to Chinese: " + input;
    }
    return String.valueOf("Invalid direction");
  }
}
