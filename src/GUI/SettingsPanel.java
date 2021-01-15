package GUI;

import com.Sound.SoundManager;
import com.Sound.SoundType;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class SettingsPanel extends JPanel implements ActionListener {

    protected JPanel sliderPanel;
    protected JPanel buttonsPanel;
    protected JSlider musicSlider;
    protected JSlider sfxSlider;
    protected JButton sfxMuteBtn;
    protected  JButton musicMuteBtn;

    private Hashtable<Integer, JLabel> JSliderLabels;

    public SettingsPanel() {

        super();
        setUpPanel();
        setUpSliderLabels();
        setUpSliders();
        setUpBtns();
        setUpBtnsPanel();

    }

    private void setUpSliders() {
        float[] sliderValuesTHEME = SoundManager.getFloatControlValuesTHEME();
        float[] sliderValuesSFX = SoundManager.getFloatControlValuesSFX();

//        musicSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValuesTHEME[0], (int) sliderValuesTHEME[1], (int) sliderValuesTHEME[2]);
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, (int) (sliderValuesTHEME[2] * 100));
        musicSlider.setLabelTable(JSliderLabels);
        musicSlider.setPaintLabels(true);
        musicSlider.addChangeListener(this::stateChanged);
        musicSlider.setBorder(BorderFactory.createTitledBorder("Music"));
        sliderPanel.add(musicSlider);

        sfxSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, (int) (sliderValuesSFX[2] * 100));
        sfxSlider.setLabelTable(JSliderLabels);
        sfxSlider.setPaintLabels(true);
        sfxSlider.addChangeListener(this::stateChanged);
        sfxSlider.setBorder(BorderFactory.createTitledBorder("SFX"));
        sliderPanel.add(sfxSlider);

    }

    private void setUpSliderLabels() {
        Hashtable<Integer, JLabel> jsliderLabels = new Hashtable<>();
        jsliderLabels.put(0, new JLabel("0"));
        jsliderLabels.put(50, new JLabel("25"));
        jsliderLabels.put(100, new JLabel("50"));
        jsliderLabels.put(150, new JLabel("75"));
        jsliderLabels.put(200, new JLabel("100"));

        JSliderLabels = jsliderLabels;
    }

    private void setUpBtns() {
        musicMuteBtn = new JButton(!SoundManager.isMutedMusic()? "Mute Music" : "Unmute Music");
        musicMuteBtn.addActionListener(this);
        sfxMuteBtn = new JButton(!SoundManager.isMutedSFX()? "Mute SFX" : "Unmute SFX");
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

        float soundLevel = (float) source.getValue() / 100;
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
            if (musicMuteBtn.getText().equals("Mute Music"))
                musicMuteBtn.setText("Unmute Music");
            else
                musicMuteBtn.setText("Mute Music");
        } else if (e.getSource() == sfxMuteBtn) {
            SoundManager.toggleSFXMute();
            if (sfxMuteBtn.getText().equals("Mute SFX"))
                sfxMuteBtn.setText("Unmute SFX");
            else
                sfxMuteBtn.setText("Mute SFX");
        }
    }
}
