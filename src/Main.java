
import java.util.Locale;

public class Main {


    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(() -> new MainForm().setVisible(true));


    }


}
/**
 * Рекурсия - check
 * Вынести в отдельный класс - check
 * Кнопочки на форме - check
 * Textbox'ы для настройки поля - check
 * Консольная реализация
 * Сделать так, чтобы линии не пересекали круги
 *
 **/