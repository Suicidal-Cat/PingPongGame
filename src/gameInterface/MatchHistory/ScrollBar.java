package gameInterface.MatchHistory;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new DesignScroll());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);//border
    }
}
