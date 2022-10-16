package com.example.hropenclass.repository;

import com.example.hropenclass.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
