package com.farfly;

import java.awt.Image;
import java.awt.Graphics;

public class Button {
	public int x;
    public int y;
    public int width;
    public int height;
    public boolean pressed;
    private Image image;
    
    public Button(final int x, final int y, final int width, final int height, final Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    public static boolean checkCollision(final int mouseX, final int mouseY, final Button btn) {
        return mouseX >= btn.x && mouseX <= btn.x + btn.width && mouseY >= btn.y && mouseY <= btn.y + btn.height;
    }
    
    public void render(final Graphics g) {
        if (this.pressed) {
            g.drawImage(this.image, this.x + 1, this.y + 1, this.width - 2, this.height - 2, null);
        }
        else {
            g.drawImage(this.image, this.x, this.y, null);
        }
    }
}