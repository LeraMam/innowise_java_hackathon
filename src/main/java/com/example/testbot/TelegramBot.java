package com.example.testbot;

import com.example.testbot.configuration.BotConfig;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Autowired
    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    public String getBotToken(){
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            Session s = HibernateConnect.getSessionFactory().openSession();
            CurrencyState currencyState = new CurrencyState();
            currencyState.setCurrency("some");
            currencyState.setPrice(29.7);
            CurrencyService service = new CurrencyService();
            service.saveUser(currencyState);

            InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Кнопка");
            inlineKeyboardButton.setCallbackData("Вы нажали на кнопку");
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            buttonList.add(inlineKeyboardButton);
            List<List<InlineKeyboardButton>> list = new ArrayList<>();
            list.add(buttonList);
            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            message.setText(message_text);
            message.setReplyMarkup(inlineKeyboardMarkup);
            inlineKeyboardMarkup.setKeyboard(list);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
