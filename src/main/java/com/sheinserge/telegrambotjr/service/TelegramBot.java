package com.sheinserge.telegrambotjr.service;

import com.sheinserge.telegrambotjr.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    @Autowired
    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
//        var msg = update.getMessage();
//        System.out.println(msg.getText());
            if(update.hasMessage()&&update.getMessage().hasText()){
                String msg = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                switch (msg){
                    case "/start":

                        startCommand(chatId, update.getMessage().getChat().getFirstName());
                        break;

                    default:sendMsg(chatId, "Извини брат, такая херня не по понятиям");

                }
            }
    }
    private void startCommand(long chatId, String name){
        String answ = "Hi" + name;
        sendMsg(chatId, answ);

    }
    private void sendMsg(long chatId, String text){
        SendMessage send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        send.setText(text);

        try {
            execute(send);
        }catch (TelegramApiException e){
            throw new RuntimeException(e);
        }

    }

}
