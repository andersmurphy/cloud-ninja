/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudNinja;

/**
 *
 * @author anders
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * Title: Sky
 * A dark sky, an array of clouds and a method to paint them.
 * As well as other background features like the moon!
 * @anders
 */

public class Sky extends JPanel
{
   // How many clouds?
   private int numberOfClouds;
   
   // Custome COlours
   private static final Color PINK = new Color(255,200,200);
   private static final Color PURPLE = new Color(170,135,160);
   private static final Color YELLOW = new Color(255,255,220);
      
   // How big's the sky?
   private int width;
   private int height;

   // Arrays to hold location and brightness of stars.
   private int[] cloudsX;
   private int[] cloudsY;
   private int[] cloudsSize;
   private Rectangle[] cloudBox;
   private Boolean[] cloudChange;

   public Sky(int nOfClouds, int w, int h)
   {
      numberOfClouds = nOfClouds;

      // Find dimensions of panel.
      width = w +10; 
      // the +10 so that the picture fits the frame correctly
      // may be something to do with my computer, or a mistake in the code 
      // somewhere
      height = h;
      // Populate arrays with random clouds.
      cloudsX = new int[numberOfClouds];
      cloudsY = new int[numberOfClouds];
      cloudsSize = new int[numberOfClouds];
      cloudBox = new Rectangle[numberOfClouds];
      cloudChange = new Boolean[numberOfClouds];
      for (int i = 0; i < numberOfClouds; i++)
      {
         cloudsX[i] = (int)(Math.random() * width);
         cloudsY[i] = (int)(Math.abs(Math.random() * height));
         cloudsSize[i] = (int)(Math.random() * 10);
         // create cloud boxes
         cloudBox[i] = new Rectangle(cloudsX[i], cloudsY[i]-35,
                 80,20);
         cloudChange[i] = false;
      }
   }

   // Paint the stars!
   public void paint(Graphics g)
   {
      // Background sky is purple.
      g.setColor(PURPLE);
      g.fillRect(0, 0, width, height);
      
      // paint moon
      g.setColor(YELLOW);
      g.fillOval(width*2/3, height/5, 80, 80);
      g.setColor(Color.yellow);
      g.fillOval(width*2/3, height/5, 78, 78);
      g.setColor(YELLOW);
      g.fillOval(width*2/3, height/5, 62, 62);
      g.setColor(PURPLE);
      g.fillOval(width*2/3 -1, height/5 -1, 60, 60);
      
      // Now add the clouds.  
      for (int i = 0; i < numberOfClouds; i++)
      {
         
         // draw cloud
         if(cloudChange[i])
         {
             // draw cloud highlight
            g.setColor(YELLOW);
            g.fillOval(cloudsX[i] , cloudsY[i], 
                  cloudsSize[i] +42, cloudsSize[i] +22);
            g.fillOval(cloudsX[i] + 10, cloudsY[i] -6, 
                 cloudsSize[i] +42, cloudsSize[i] +22);
            g.fillOval(cloudsX[i] + 20, cloudsY[i], 
                 cloudsSize[i] +42, cloudsSize[i] +22);
            g.fillOval(cloudsX[i] + 30, cloudsY[i] -3, 
                 cloudsSize[i] +42, cloudsSize[i] +22);
            g.fillOval(cloudsX[i] + 40, cloudsY[i], 
                 cloudsSize[i] +42, cloudsSize[i] +22);
             // draw cloud
             g.setColor(PINK);
             g.fillOval(cloudsX[i] , cloudsY[i], 
                 cloudsSize[i] +40, cloudsSize[i] +20);
             g.fillOval(cloudsX[i] + 10, cloudsY[i] -6, 
                 cloudsSize[i] +40, cloudsSize[i] +20);
             g.fillOval(cloudsX[i] + 20, cloudsY[i], 
                 cloudsSize[i] +40, cloudsSize[i] +20);
             g.fillOval(cloudsX[i] + 30, cloudsY[i] -3, 
                 cloudsSize[i] +40, cloudsSize[i] +20);
             g.fillOval(cloudsX[i] + 40, cloudsY[i], 
                 cloudsSize[i] +40, cloudsSize[i] +20);
         }
         else
         {
             // draw cloud highlight
             g.setColor(PINK);
             g.fillOval(cloudsX[i] , cloudsY[i], 
                cloudsSize[i] +42, cloudsSize[i] +22);
             g.fillOval(cloudsX[i] + 10, cloudsY[i] -6, 
                cloudsSize[i] +42, cloudsSize[i] +22);
             g.fillOval(cloudsX[i] + 20, cloudsY[i], 
                cloudsSize[i] +42, cloudsSize[i] +22);
             g.fillOval(cloudsX[i] + 30, cloudsY[i] -3, 
                cloudsSize[i] +42, cloudsSize[i] +22);
             g.fillOval(cloudsX[i] + 40, cloudsY[i], 
                cloudsSize[i] +42, cloudsSize[i] +22);
         
            // draw cloud
            g.setColor(YELLOW);
            g.fillOval(cloudsX[i] , cloudsY[i], 
                   cloudsSize[i] +40, cloudsSize[i] +20);
            g.fillOval(cloudsX[i] + 10, cloudsY[i] -6, 
                   cloudsSize[i] +40, cloudsSize[i] +20);
            g.fillOval(cloudsX[i] + 20, cloudsY[i], 
                   cloudsSize[i] +40, cloudsSize[i] +20);
            g.fillOval(cloudsX[i] + 30, cloudsY[i] -3, 
                   cloudsSize[i] +40, cloudsSize[i] +20);
            g.fillOval(cloudsX[i] + 40, cloudsY[i], 
                   cloudsSize[i] +40, cloudsSize[i] +20);
             
         }
      }
   } 
     
   // get cloudBoxes
   public Rectangle getCloudBox(int i)
   {
      return cloudBox[i];
   }  
   // change cloud colour
   public void changeCloudColour(int i)
   {
         cloudChange[i] = true;
   }
}

