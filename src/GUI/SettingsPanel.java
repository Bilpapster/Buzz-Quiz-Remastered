package GUI;

import com.Sound.SoundManager;
import com.Sound.SoundType;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {

    protected JPanel sliderPanel;
    protected JPanel buttonsPanel;
    protected JSlider musicSlider;
    protected JSlider sfxSlider;
    protected JButton sfxMuteBtn;
    protected  JButton musicMuteBtn;

    public SettingsPanel() {

        super();
        setUpPanel();
        setUpSliders();
        setUpBtns();
        setUpBtnsPanel();

    }

    private void setUpSliders() {
        float[] sliderValuesTHEME = SoundManager.getFloatControlValuesTHEME();
        float[] sliderValuesSFX = SoundManager.getFloatControlValuesSFX();

        musicSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValuesTHEME[0], (int) sliderValuesTHEME[1], (int) sliderValuesTHEME[2]);
        musicSlider.setMajorTickSpacing(10);
        musicSlider.setMinorTickSpacing(1);
        musicSlider.setPaintTicks(true);
        musicSlider.setPaintLabels(true);
        musicSlider.addChangeListener(this::stateChanged);
        musicSlider.setBorder(BorderFactory.createTitledBorder("Music"));
        sliderPanel.add(musicSlider);

        sfxSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValuesSFX[0], (int) sliderValuesSFX[1], (int) sliderValuesSFX[2]);
        sfxSlider.setMajorTickSpacing(10);
        sfxSlider.setMinorTickSpacing(1);
        sfxSlider.setPaintTicks(true);
        sfxSlider.setPaintLabels(true);
        sfxSlider.addChangeListener(this::stateChanged);
        sfxSlider.setBorder(BorderFactory.createTitledBorder("SFX"));
        sliderPanel.add(sfxSlider);



    }

    private void setUpBtns() {
        musicMuteBtn = new JButton(!SoundManager.isIsMutedMusic()? "Mute Music" : "Unmute Music");
        musicMuteBtn.addActionListener(this);
        sfxMuteBtn = new JButton(!SoundManager.isIsMutedSFX()? "Mute SFX" : "Unmute SFX");
        sfxMuteBtn.addActionListener(this);
    }

    private void setUpPanel() {
        this.setLayout(new BorderLayout());
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setVisible(true);
        this.add(sliderPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        float soundLevel = (float) source.getValue();
        SoundType soundType = e.getSource() == musicSlider? SoundType.THEME : SoundType.CLIP;
        SoundManager.adjustSound(soundLevel, soundType);


    }

    private void setUpBtnsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(musicMuteBtn);
        buttonsPanel.add(sfxMuteBtn);
        buttonsPanel.setVisible(true);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == musicMuteBtn) {
            SoundManager.toggleMusicMute();
            if (musicMuteBtn.getText() == "Mute Music")
                musicMuteBtn.setText("Unmute Music");
            else
                musicMuteBtn.setText("Mute Music");
        } else if (e.getSource() == sfxMuteBtn) {
            SoundManager.toggleSFXMute();
            if (sfxMuteBtn.getText() == "Mute SFX")
                sfxMuteBtn.setText("Unmute SFX");
            else
                sfxMuteBtn.setText("Mute SFX");
        }
    }
}
