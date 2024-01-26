package org.example.message;

import java.io.IOException;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PublishingMessage {

    public static final String TOKEN = "";

    /**
     * Post a message to a channel your app is in using ID and message text
     */
    static void publishMessage(String id, String text) {
        // you can get this instance via ctx.client() in a Bolt app
        var client = Slack.getInstance().methods();
        try {
            // Call the chat.postMessage method using the built-in WebClient
            var result = client.chatPostMessage(
                messageRequestBuilder -> messageRequestBuilder
                    // The token you used to initialize your app
                    .token(TOKEN)
                    .channel(id)
                    .text(text)
                // You could also use a blocks[] array to send richer content
            );
            // Print result, which includes information about the message (like TS)
            log.info("result {}", result);
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        publishMessage("", "Hello, world");
    }
}
