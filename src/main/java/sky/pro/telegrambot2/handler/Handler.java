package sky.pro.telegrambot2.handler;

import com.pengrad.telegrambot.model.Update;

public interface Handler {
    void handle(Update update);
}