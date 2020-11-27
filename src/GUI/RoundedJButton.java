package GUI;

import javax.swing.*;
import java.awt.*;

public class RoundedJButton extends JButton {
    protected int strokeSize = 1;
    protected Color shadowColor = Color.BLACK;
    protected boolean shady = true;
    protected boolean highQuality = true;
    protected Dimension arcs = new Dimension(20, 20);
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;

    public RoundedJButton(String text) {
        super(text);
        setOpaque(false);
    }

    public RoundedJButton(String text, int strokeSize, Color shadowColor, boolean shady, boolean highQuality, Dimension arcs, int shadowGap, int shadowOffset, int shadowAlpha) {
        this(text);
        this.strokeSize = strokeSize;
        this.shadowColor = shadowColor;
        this.shady = shady;
        this.highQuality = highQuality;
        this.arcs = arcs;
        this.shadowGap = shadowGap;
        this.shadowOffset = shadowOffset;
        this.shadowAlpha = shadowAlpha;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(shadowOffset, shadowOffset, width - strokeSize - shadowOffset, height - strokeSize - shadowOffset, arcs.width, arcs.height);
        } else {
            shadowGap = 1;
        }

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
        this.setForeground(Color.MAGENTA);
    }
}
