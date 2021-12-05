/*
 * Picture.java
 *
 * This class includes a constructor that can be used to set up a Picture object
 * to allow drawing on an area of known size
 * The method draw receives a graphics context
 *
 */
package cloudNinja;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Picture
{
    //TODO add instance variables
    int width, height;
    private Point picturePos;
    // counts number of steps
    private int stepCounter = 0;
    // integer value for each animation
    private int pictureCounter = 1;
    // counter for standing animation

    // movement flags
    private boolean walkingLeft = true; // set to true so that pciture initialy
    // moving (to satisfy requirement)
    private boolean walkingRight;
    private boolean jumping = false;
    private boolean falling;
    // used to control the duration of the jump
    private int jumpCounter;
    // prevents jumping whilst already jumping
    private boolean jumpLock;
    private Sky mySky;
    private int numberOfClouds;

    //TODO complete constructor
    public Picture(int width, int height)
    {
        this.width = width;
        this.height = height;
        System.out.println("width " + width + " height " + height);
        //TODO complete
        // sets starting psition of picture
        picturePos = new Point(this.width/2,0);
        numberOfClouds = 40;
        mySky = new Sky(numberOfClouds,width,height);
    }

    //TODO add updatePictureState method
    //parameters can be removed/changed if required
    //This method does not do any drawing - it changes the state of the
    //picture object(s). The draw method does the drawing
    public void updatePictureState()
    {
        //TODO complete
        // check for clouds
        for(int i = 0; i < numberOfClouds; i++)
        {
            if(mySky.getCloudBox(i).contains(picturePos))
            {
                falling = false;
                // picture is now able to jump again
                jumpLock = false;
                // change cloud colour
                mySky.changeCloudColour(i);
                break;
            }
            else
            {
                falling = true;
            }
        }
        // check for walkingRight flag
        if(walkingRight)
        {
            // moves picture right
            move(5,0);
        }
        // check for walkingLeft flag
        if(walkingLeft)
        {
            // moves picture left
            move(-5,0);

        }
        //check for falling flag
        if(falling)
        {
            // moves player down
            move(0,5);
        }
        // check for jumping flag
        if(jumping)
        {
            jumpLock = true;
            falling = false;
            if(jumpCounter < 10)
            {
                move(0,-10);
                jumpCounter++;
            }
            else if(jumpCounter < 13)
            {
                move(0,-7);
                jumpCounter++;
            }
            else
            {
                jumping = false;
            }
        }

        // change the player image after 5 steps
        if(stepCounter%5 == 0)
        {
            // change value of the pictureCounter
            pictureCounter++;
            // reset if value is greater then 2 (two pictures)
            if(pictureCounter > 2)
            {
                pictureCounter = 1;
            }
            // reset step counter
            stepCounter = 0;
        }
        // leave right side appear left side
        if(picturePos.x > width )
        {
            picturePos.x = -10;
        }
        // leave left side appear right side
        if(picturePos.x < -10)
        {
            picturePos.x = width;
        }
        // leave bottom appear top
        if(picturePos.y > height )
        {
            picturePos.y = -10;
        }
        // leave top appear bottom
        if(picturePos.y < -20 )
        {
            picturePos.y = height;
        }


    }

    //TODO complete draw method
    /* draw method that draws ninja, and animates him*/
    public void draw(Graphics g)
    {
        mySky.paint(g);
        //TODO complete
        /* animation moving right A*/
        if(walkingRight && !falling)
        {
            // checks to see which animation should be used
            if(pictureCounter == 1)
            {
                // moving right animation 1
                // Head
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x +1,picturePos.y +1, 19, 19);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +4,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +5, picturePos.y +14, 15, 2);
                g.fillRect(picturePos.x +6, picturePos.y +16, 13, 2);
                g.fillRect(picturePos.x +7, picturePos.y +18, 10, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 6, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +10 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +15 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +4, picturePos.y + 30, 6, 4);
                g.fillOval(picturePos.x +10, picturePos.y + 28, 6, 4);
                // Hand
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +16 , picturePos.y +26, 4, 4);
            }

            if(pictureCounter == 2)
            {
                // moving right animation 2
                // Head
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x +1,picturePos.y +1, 19, 19);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +4,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +5, picturePos.y +14, 15, 2);
                g.fillRect(picturePos.x +6, picturePos.y +16, 13, 2);
                g.fillRect(picturePos.x +7, picturePos.y +18, 10, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 6, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +10 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +15 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +8, picturePos.y + 30, 6, 4);
                // Hand
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x , picturePos.y +24, 4, 4);
            }
        }

        /* animation moving left*/
        if(walkingLeft && !falling)
        {
            // checks to see which animation should be used
            if(pictureCounter == 1)
            {
                // moving left animation 1
                // Head
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x ,picturePos.y +1, 19, 19);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x ,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +1, picturePos.y +14, 15, 2);
                g.fillRect(picturePos.x +2, picturePos.y +16, 13, 2);
                g.fillRect(picturePos.x +4, picturePos.y +18, 10, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 1, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +5 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +10 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +4, picturePos.y + 30, 6, 4);
                g.fillOval(picturePos.x +10, picturePos.y + 28, 6, 4);
                // Hand
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +16 , picturePos.y +26, 4, 4);
            }

            if(pictureCounter == 2)
            {
                // moving left animation 2
                // Head
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x ,picturePos.y +1, 19, 19);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x ,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +1, picturePos.y +14, 15, 2);
                g.fillRect(picturePos.x +2, picturePos.y +16, 13, 2);
                g.fillRect(picturePos.x +4, picturePos.y +18, 10, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 1, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +5 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +10 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +8, picturePos.y + 30, 6, 4);
                // Hand
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x , picturePos.y +24, 4, 4);
            }
        }
        // standing animation
        if(!walkingRight && !walkingLeft && !falling)
        {
            g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x +1,picturePos.y +2, 18, 18);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +2,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +2, picturePos.y +14, 16, 2);
                g.fillRect(picturePos.x +3, picturePos.y +16, 14, 2);
                g.fillRect(picturePos.x +4, picturePos.y +18, 11, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 3, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +7 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +13 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +4, picturePos.y + 30, 6, 4);
                g.fillOval(picturePos.x +10, picturePos.y + 30, 6, 4);
        }
        // falling animation
        if(falling)
        {
            g.setColor(Color.LIGHT_GRAY);
                g.fillOval(picturePos.x,picturePos.y,20, 20);
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x +1,picturePos.y +2, 18, 18);
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x +2,picturePos.y +4, 16, 16);
                // mouth cover
                g.setColor(Color.GRAY);
                g.fillRect(picturePos.x +2, picturePos.y +14, 16, 2);
                g.fillRect(picturePos.x +3, picturePos.y +16, 14, 2);
                g.fillRect(picturePos.x +4, picturePos.y +18, 11, 2);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(picturePos.x + 3, picturePos.y +14,14,1);
                // Eyes
                g.setColor(Color.white);
                g.fillOval(picturePos.x +7 ,picturePos.y +10, 2, 2);
                g.fillOval(picturePos.x +13 ,picturePos.y +10, 2, 2);
                // Body
                g.setColor(Color.GRAY);
                g.fillOval(picturePos.x + 5, picturePos.y +18, 10, 10);
                // Feet
                g.fillOval(picturePos.x +8, picturePos.y + 30, 6, 4);
                // Hands
                g.setColor(Color.DARK_GRAY);
                g.fillOval(picturePos.x -4 , picturePos.y +15, 4, 4);
                g.fillOval(picturePos.x +20, picturePos.y +15, 4, 4);
        }
    }
    //TODO add further methods here if required - up to you!

    // sets walking right flag
    public void setWalkingRight(boolean b)
    {
        walkingRight = b;
    }

    // sets walking left flag
    public void setWalkingLeft(boolean b)
    {
        walkingLeft = b;
    }

    // sets jumping flag
    public void setJumping(boolean b)
    {
        // check for jumpLock
        if(!jumping && !jumpLock && b)
        {
            // resets jump counter i.e the timer for the jump
            jumpCounter = 0;
        }
        // can't jump if falling
        if(falling)
        {
            jumping = false;
        }
        else
        {
            jumping = b;
        }
    }

    // sets falling flag
    public void setFalling(boolean b)
    {
        if(!b)
        {
            // rest jump flag to enable jumping
            if(jumpLock)
            {
                jumpLock = false;
                jumping = false;
            }
            // set value of falling
            falling = b;

        }
    }

    // moves picture helper class
    private void move(int x, int y)
    {
        picturePos.translate(x, y);
        stepCounter++;
    }



}
