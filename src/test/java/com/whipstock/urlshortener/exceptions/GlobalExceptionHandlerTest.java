package com.whipstock.urlshortener.exceptions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whipstock.urlshortener.model.EncodeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void encode_validUrl_success() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders
            .post("/encode")
            .content(new ObjectMapper().writeValueAsString((new EncodeRequest().setUrl("https://example.com"))))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void encode_invalidUrl_error() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders
            .post("/encode")
            .content(new ObjectMapper().writeValueAsString((new EncodeRequest().setUrl("https://exampl"))))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }

}
