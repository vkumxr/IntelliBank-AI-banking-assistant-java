package com.azure.assistant;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private OpenAIClient openAIClient;

    private static final String DEPLOYMENT_NAME = "gpt-4"; // replace with your actual deployment name

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return "You said: " + request.getMessage();
    }

}
