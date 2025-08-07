package com.azure.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private AzureOpenAIService azureOpenAIService;

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody ChatRequest chatRequest) {
        String assistantReply = azureOpenAIService.getChatResponse(chatRequest.getMessages());

        Map<String, String> response = new HashMap<>();
        response.put("content", assistantReply);

        return ResponseEntity.ok(response);
    }
}
