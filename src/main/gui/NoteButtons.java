package gui;

import javax.swing.*;
import java.util.ArrayList;

public class NoteButtons {
    private ArrayList<JButton> buttonList = new ArrayList<>();

    JButton buttonC = new JButton("C");
    JButton buttonCsharp = new JButton("C#");
    JButton buttonD = new JButton("D");
    JButton buttonDsharp = new JButton("D#");
    JButton buttonE = new JButton("E");
    JButton buttonF = new JButton("F");
    JButton buttonFsharp = new JButton("F#");
    JButton buttonG = new JButton("G");
    JButton buttonGsharp = new JButton("G#");
    JButton buttonA = new JButton("A");
    JButton buttonAsharp = new JButton("A#");
    JButton buttonB = new JButton("B");

    public NoteButtons() {
        buttonList.add(buttonC);
        buttonList.add(buttonCsharp);
        buttonList.add(buttonD);
        buttonList.add(buttonDsharp);
        buttonList.add(buttonE);
        buttonList.add(buttonF);
        buttonList.add(buttonFsharp);
        buttonList.add(buttonG);
        buttonList.add(buttonGsharp);
        buttonList.add(buttonA);
        buttonList.add(buttonAsharp);
        buttonList.add(buttonB);
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }
}
