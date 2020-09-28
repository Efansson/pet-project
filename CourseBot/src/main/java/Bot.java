import ORM.Hibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = LogManager.getLogger(Hibernate.class);
    private long chat_id;
    protected String lastMessage = "";


    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived ( Update update ) {
        chat_id = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage()
                .setChatId(chat_id)
                .setText(getMessage(update.getMessage().getText()));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Бот не отправляет сообщения, проверь, что там " + e.toString());
            e.printStackTrace();
        }
    }

    public String getMessage ( String msg ) {
        Renewal renewal = new Renewal();

        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if (msg.equals("Меню")) {
            keyboard.clear();
            keyboardFirstRow.add("Евро");
            keyboardFirstRow.add("Доллар");
            keyboardSecondRow.add("Оба курса");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }

        if (msg.equals("Оба курса")) {
            keyboard.clear();
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return renewal.getAll();
        }

        if (msg.equals("Евро")) {
            lastMessage = msg;
            keyboard.clear();
            keyboardFirstRow.add("Покупка");
            keyboardFirstRow.add("Продажа");
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }

        if (msg.equals("Доллар")) {
            lastMessage = msg;
            keyboard.clear();
            keyboardFirstRow.add("Покупка");
            keyboardFirstRow.add("Продажа");
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выберите пункт меню";
        }

        if (lastMessage.equals("Доллар") && msg.equals("Продажа")) {
            keyboard.clear();
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return renewal.getSaleU();
        }
        if (lastMessage.equals("Доллар") && msg.equals("Покупка")) {
            keyboard.clear();
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return renewal.getBuyingU();
        }

        if (lastMessage.equals("Евро") && msg.equals("Продажа")) {
            keyboard.clear();
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return renewal.getSaleE();
        }
        if (lastMessage.equals("Евро") && msg.equals("Покупка")) {
            keyboard.clear();
            keyboardFirstRow.add("Меню");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return renewal.getBuyingE();
        }
        if (msg.equals("/start")) {
            return renewal.getInfo();
        }

        return renewal.getAll();
    }


    @Override
    public String getBotUsername () {
        return "Здесь имя бота";
    }

    @Override
    public String getBotToken () {
        return "Тут токен бота";
    }

}

class Renewal {
    Hibernate hibernate = new Hibernate();

    private String buyingU = hibernate.getBuyingUSD().getValue();
    private String saleU = hibernate.getSaleUSD().getValue();
    private String buyingE = hibernate.getBuyingEUR().getValue();
    private String saleE = hibernate.getSaleEUR().getValue();
    private String all = "Курс покупки доллара: " + buyingU + " продажи: " + saleU + "\n" + "Курс покупки евро: " + buyingE + " продажи: " + saleE;

    public String getBuyingU () {
        return buyingU;
    }

    public String getSaleU () {
        return saleU;
    }

    public String getBuyingE () {
        return buyingE;
    }

    public String getSaleE () {
        return saleE;
    }

    public String getAll () {
        return all;
    }

    public String getInfo () {
        return info;
    }

    private String info = "Разработчик не несёт ответственность за достоверность информации, полученной от данного бота. " +
            "\nДанные, полученные в этом боте, могут отличаться от действительных. " +
            "\nАктуальные курсы можете уточнить в поддержке Райффайзенбанка, а так же на сайте: raiffeisen.ru " +
            "\nСоглашаясь с этими условиями, напишите в ответ: <Меню>.";

}
