package cloudNinja;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Game
{
    // instance variables provided
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 500;
    private DrawingPanel drawingPanel;
    private JMenuBar bar;
    private JMenu fileMenu;
    private JMenuItem aboutMenu;
    private JMenuItem exitMenu;

    public void init()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("CloudNinja");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // menu
        bar = new JMenuBar();
        frame.setJMenuBar(bar);
        fileMenu = new JMenu("File");
        aboutMenu = new JMenuItem("Instructions");
        exitMenu = new JMenuItem("Exit");
        // add listener to exit menu item
        exitMenu.addActionListener(new MenuSelection());
        // add listener to instructions menu item
        aboutMenu.addActionListener(new MenuSelection());
        bar.add(fileMenu);
        fileMenu.add(aboutMenu);
        fileMenu.add(exitMenu);

        //set visible here, now size is available
        frame.setVisible(true);

        drawingPanel = new DrawingPanel(getAvailableWidth(frame), getAvailableHeight(frame));
        frame.add(drawingPanel);

        //TODO add registering of any event handlers here

        // adds listner
        frame.addKeyListener(new KeyList());
        // makes keys work!!! important
        frame.setFocusable(true);
        update(frame);
    }

    //These methods should only be called after the frame is visible.
    //They tell you about the available width and height in the frame
    public int getAvailableWidth(JFrame frame)
    {
        return frame.getWidth() - frame.getInsets().left - frame.getInsets().right;
    }

    public int getAvailableHeight(JFrame frame)
    {
        return frame.getHeight() - frame.getInsets().top - frame.getInsets().bottom;
    }

    //TODO complete update method
    //this method drives the application
    public void update(JFrame frame)
    {
        //the idea here is to loop
        //and change the picture
        //this involves updating the content of the drawingPanel
        // by calling the panel's updatePictureState method
        while(true)
        {
            drawingPanel.updatePictureState();
            frame.repaint();
            try
            {
                Thread.sleep(50);
            }
            catch(Exception e)
            {
                System.exit(0);
            }
        }
    }

    // inner class on which to draw everything - you can add
    // to this class as much as you like
    private class DrawingPanel extends JPanel // provided
    {

        private Picture myPicture;
        // add listener


        // add further instance variables if required
        public DrawingPanel(int width, int height) // given
        {
            myPicture = new Picture(width, height);
            setSize(width, height);

        }

        //this method is invoked automatically when repaint occurs in
        //the outer container
        public void paintComponent(Graphics g) // given
        {
            super.paintComponent(g);
            myPicture.draw(g); //This does the redrawing based on current state

        }

        // TODO complete updatePictureState method
        //this is about updating the state of elements in the picture
        //not about redrawing
        public void updatePictureState()
        {
            myPicture.updatePictureState();
        }
    }

    /* key listener to move ninja */
    private class KeyList extends KeyAdapter
    {
        // listens for a key to be pressed
        public void keyPressed(KeyEvent k)
        {
            if(k.getKeyCode() == KeyEvent.VK_LEFT)
            {
                // sets the walkingLeft flag to true
                drawingPanel.myPicture.setWalkingLeft(
                            true);
                drawingPanel.myPicture.setWalkingRight(
                            false);
            }
            if(k.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                // sets the walkingRight flag to true
                drawingPanel.myPicture.setWalkingRight(
                            true);
                drawingPanel.myPicture.setWalkingLeft(
                            false);
            }
            if(k.getKeyCode() == KeyEvent.VK_SPACE)
            {
                // sets the jumping flag to true
                drawingPanel.myPicture.setJumping(
                            true);
            }
        }
            // listens for a key to be released
            public void keyReleased(KeyEvent k)
            {
                // sets the walkingLeft flag to false
                if(k.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    drawingPanel.myPicture.setWalkingLeft(
                            false);
                }
                // sets the walkingRight flag to false
                if(k.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    drawingPanel.myPicture.setWalkingRight(
                            false);
                }
                // sets the jumping flag to false
                if(k.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    drawingPanel.myPicture.setJumping(
                            false);
             }
        }

    }

    public class MenuSelection implements ActionListener
    {
        public void actionPerformed(ActionEvent a)
        {
            Object buttonPressed = a.getSource();
            // action performed if exit is pressed
            if(buttonPressed.equals(exitMenu))
            {
                System.exit(0);
            }
            // action performed if instructions is pressed
            if(buttonPressed.equals(aboutMenu))
            {
                // creates an instruction message
                JOptionPane.showMessageDialog(null, "Use the arrow keys to "
                        + "move around, and space to jump!\n"
                        + "Land on the clouds to make them change colour."
                        + "\n How many clouds can you change?",
                        "Instructions", JOptionPane.PLAIN_MESSAGE);


            }
        }
    }

}
