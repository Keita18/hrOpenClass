package com.example.hropenclass.controllers;

import com.example.hropenclass.model.Message;
import com.example.hropenclass.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    final MessageService messageService;

    public MessageController(@Autowired MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody final Message message) {
        return ResponseEntity.ok().body(messageService.saveMessage(message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> get(@PathVariable final long id) {
        return ResponseEntity.ok().body(messageService.getMessage(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable final long id, @RequestBody Message message) {
        Optional<Message> m = messageService.getMessage(id);
        if (m.isPresent()) {
            Message currentMessage = m.get();
            if(message.getUserName() != null)
                currentMessage.setUserName(message.getUserName());
            if (message.getText() != null)
                currentMessage.setText(message.getText());
            messageService.saveMessage(currentMessage);
            return ResponseEntity.ok().body(currentMessage);
        } else return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Message> delete(@RequestBody Message message) {
        messageService.deleteMessage(message);
        return ResponseEntity.ok().build();
    }
}
