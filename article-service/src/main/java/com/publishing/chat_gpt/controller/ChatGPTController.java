package com.publishing.chat_gpt.controller;

import com.publishing.chat_gpt.dto.CharContentRequestDto;
import com.publishing.chat_gpt.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @MessageMapping("/get-chat-categories")
    @SendTo("/topic/chat-topic")
    public List<String> categories(CharContentRequestDto dto) throws Exception{
        return chatGPTService.chatWithGpt3(dto.content());
    }
}