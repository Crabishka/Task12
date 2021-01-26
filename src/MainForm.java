import util.SwingUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;

public class MainForm extends JFrame {

    private JPanel MainPanel;
    private JLabel labelImg;
    private JButton LoadFromFileButton;
    private JButton SaveIntoFileButton;
    private JButton ExecuteButton;
    private JSpinner LevelSpinner;
    private JSpinner SizeSpinner;
    private JLabel LevelLabel;
    private JLabel WidthLabel;
    private JLabel JustTextLabel;
    private JLabel AttentionLabel;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;


    public MainForm() {

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        JustTextLabel.setText("<html>При глубине рекурсии 11 <br> " +
                "время работы >2 cекунд <br> " +
                "при 12 около 30 секунд </html>");
        AttentionLabel.setText("<html> Прежде чем сохранить картинку,<br> дайте файлу название </html>");
        this.setTitle("Снежинка");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        MainPanel.setMinimumSize(new Dimension(800, 1000));
        //    doDrawing(600);

        ExecuteButton.addActionListener(actionEvent -> {
            try {
                doDrawing((int) SizeSpinner.getValue(),
                        (int) LevelSpinner.getValue());
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }


        });


        LoadFromFileButton.addActionListener(actionEvent -> {

            try {
                if (fileChooserOpen.showOpenDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                    Scanner scanner = new Scanner( new File (fileChooserOpen.getSelectedFile().getPath()));
                    //  int[] drawingData = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    //  я пока не совсем понял, как работают лямбда-выражения и квадраточие, поэтому пусть будет по-старому
                    String[] datas = scanner.nextLine().trim().split(" ");
                    int size = Integer.parseInt(datas[0]);
                    int level = Integer.parseInt(datas[1]);
                    doDrawing(size, level);
                    LevelSpinner.setValue(level);
                    SizeSpinner.setValue(size);

                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });


        SaveIntoFileButton.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                    if (labelImg.getIcon() != null) {
                        //    ImageIO.write( (RenderedImage)  labelImg.getIcon(),"png",new File (fileChooserSave.getSelectedFile().getPath()));
                        ImageIO.write(doDrawing((int) SizeSpinner.getValue(),
                                (int) LevelSpinner.getValue()), "png", new File(fileChooserSave.getSelectedFile().getPath() + ".png"));
                    }
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });


    }

    public BufferedImage doDrawing(int size, int levelCount) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        DrawSnowflake.paintFigure(g2d, size, levelCount);
        labelImg.setIcon(new ImageIcon(image));
        return image;

    }


}
