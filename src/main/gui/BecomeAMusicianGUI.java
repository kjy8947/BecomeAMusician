package gui;

import sound.NoteSound;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.*;

public class BecomeAMusicianGUI extends JFrame implements ActionListener {
    private JLabel labelOne;
    private JLabel labelTwo;
    private JLabel labelThree;
    private JTextField field;

    private NoteSound noteSound = new NoteSound();
    private Buttons buttons = new Buttons();
//    private ArrayList<String> buttons = new ArrayList<>();
//    private ArrayList<JButton> buttonList = new ArrayList<>();

    public BecomeAMusicianGUI() {
        super("Become a Musician");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 360));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        nameTheCharacter();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(640, 360));
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
//        setLayout(new FlowLayout());
//        JButton btn = new JButton("Enter");
//        btn.setActionCommand("myButton");
//        btn.addActionListener(this);
//        labelOne = new JLabel("(1-12 alphabetical letters only without spaces)");
//        //labelTwo = new JLabel("your character's name is:");
//        field = new JTextField(12);
//        add(field);
//        add(btn);
//        add(labelOne);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);

        //setResizable(false);

//        for (JButton jb : buttons.getButtonList()) {
//            String noteName = jb.getText();
//            jb.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    try {
//                        noteSound.playNote(noteName);
//                    } catch (MidiUnavailableException midiUnavailableException) {
//                        midiUnavailableException.printStackTrace();
//                    }
//                }
//            });
//
//            add(jb);
//            setVisible(true);
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            playNotes();
        }
    }

    public void nameTheCharacter() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(640, 360));
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
//        setLayout(new FlowLayout());

        JButton btn = new JButton("Enter");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        labelOne = new JLabel("Name your character: ");
        //labelTwo = new JLabel("your character's name is:");
        field = new JTextField(12);

//        btn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                playNotes();
//            }
//        });

        add(labelOne);
        add(field);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn.setVisible(false);
                labelOne.setVisible(false);
                field.setVisible(false);
                playNotes();
            }
        });
    }

    public void playNotes() {
        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            jb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        noteSound.playNote(noteName);
                    } catch (MidiUnavailableException midiUnavailableException) {
                        midiUnavailableException.printStackTrace();
                    }
                }
            });

            add(jb);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        new BecomeAMusicianGUI();
    }
}