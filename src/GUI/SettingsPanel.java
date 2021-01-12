package GUI;

import com.Sound.SoundManager;
import com.Sound.SoundType;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.util.HashMap;
import java.util.Hashtable;

public class SettingsPanel extends JPanel {

    protected JSlider musicSlider;
    protected JSlider sfxSlider;
    protected RoundedJButton sfxMuteBtn;
    protected  RoundedJButton musicMuteBtn;
    private SoundManager soundManager;

    public SettingsPanel(SoundManager soundManager) {

        super();
        this.soundManager = soundManager;
        setUpPanel();
        setUpSliders();
        setUpBtns();

    }

    private void setUpSliders() {
        final FloatControl floatControl = soundManager.getFloatControl();
        float[] sliderValues = {floatControl.getMinimum(), floatControl.getMaximum(), floatControl.getValue()};

        musicSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValues[0], (int) sliderValues[1], (int) sliderValues[2]);
        musicSlider.setMajorTickSpacing(10);
        musicSlider.setMinorTickSpacing(1);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
        musicSlider.addChangeListener(this::stateChanged);
        musicSlider.setBorder(BorderFactory.createTitledBorder("Music"));
        this.add(musicSlider);

        sfxSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValues[0], (int) sliderValues[1], (int) sliderValues[2]);
        sfxSlider.setMajorTickSpacing(10);
        sfxSlider.setMinorTickSpacing(1);
        sfxSlider.setPaintTicks(true);
        sfxSlider.setPaintLabels(true);
        sfxSlider.addChangeListener(this::stateChanged);
        sfxSlider.setBorder(BorderFactory.createTitledBorder("SFX"));
        this.add(sfxSlider);



    }

    private void setUpBtns() {

    }

    private void setUpPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        float soundLevel = (float) source.getValue();
        SoundType soundType = e.getSource() == musicSlider? SoundType.Theme : SoundType.Clip;
        soundManager.adjustSound(soundLevel, soundType);


    }

}
