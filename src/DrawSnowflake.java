import java.awt.*;

public class DrawSnowflake {

    public static void paintFigure(Graphics2D g2d, int size, int levelCount) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size, size); // Draws a solid rectangle of specified width/height, located at (x,y)
        g2d.setColor(color);                  // рисует фон
        //  g2d.drawOval(size/2 - 50,size/2 - 50,100,100); // овал рисуется как прямоугольник, с левого верхнего угла
        paintFigureLevel(g2d, size / 2, size / 2, size, levelCount);

        //    g2d.dispose ();


    }

    private static void paintFigureLevel(Graphics2D g2d, double x, double y, double size, int level) {
        if (level > 0) {
            // Абсолютно некрасивая реализация, хотел ей заняться в последнюю очередь, когда все будет работать
            paintFigureLevel(g2d, x + size * 0.3, y, size * 0.4, level - 1);
            paintFigureLevel(g2d, x - size * 0.3, y, size * 0.4, level - 1);
            paintFigureLevel(g2d, x, y + size * 0.3, size * 0.4, level - 1);
            paintFigureLevel(g2d, x, y - size * 0.3, size * 0.4, level - 1);
            double s = size / 12.5; // s = sizeOfSector
            g2d.drawOval((int) x - (int) (2.5 * s) / 2, (int) y - (int) (2.5 * s) / 2, (int) (2.5 * s), (int) (2.5 * s));


            g2d.drawLine((int) (x), (int) (y - 3.25 * s), (int) (x), (int) (y - 1.25 * s)); // верх
            g2d.drawLine((int) (x), (int) (y + 3.25 * s), (int) (x), (int) (y + 1.25 * s)); // вниз
            g2d.drawLine((int) (x - 3.25 * s), (int) (y), (int) (x - 1.25 * s), (int) (y)); // влево
            g2d.drawLine((int) (x + 3.25 * s), (int) (y), (int) (x + 1.25 * s), (int) (y)); // вправо


        } else { // тут рисуется одна снежинка
            return;
        }
    }
}
