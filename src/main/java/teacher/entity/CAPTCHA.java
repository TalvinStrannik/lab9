package teacher.entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CAPTCHA {
	private int HEIGHT=60;
	private int WIDTH=180;
	private int HEIGHTs=40;
	private int WIDTHs=40;
	private String string;
	private BufferedImage image;
	
	public String getString() {
		return string;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	private int randomInt(int n) {
		return Math.abs(new Random().nextInt()) % n;
	}
	
	private String randomStr(){
		StringBuffer sb=new StringBuffer("@");
		int n=randomInt(26)+65+10;
		if(n<=90)
			sb.setCharAt(0, (char) n);
		else
			sb.setCharAt(0, (char) (n-43));
		return sb.toString();
	}
	
	private Color randomColor(){
		return new Color(new Float(Math.random()), new Float(Math.random()),new Float(Math.random()));
	}
	
	private void setAlpha(BufferedImage obj_img,byte alpha) {       
	    alpha %= 0xff; 
	    for (int cx=0;cx<obj_img.getWidth();cx++) {          
	        for (int cy=0;cy<obj_img.getHeight();cy++) {
	            int color = obj_img.getRGB(cx, cy);
	            int mc = (alpha << 24) | 0x00ffffff;
	            int newcolor = color & mc;
	            obj_img.setRGB(cx, cy, newcolor);            
	        }
	    }
	}
	
	private BufferedImage getImg(String str){
		BufferedImage bi = new BufferedImage(WIDTHs,HEIGHTs,BufferedImage.TYPE_INT_ARGB);
		Graphics2D canvas = bi.createGraphics();
		canvas.setColor(randomColor());
		canvas.setFont(new Font("Arial", 10, 50));
		canvas.drawString(str, 0, 40);
		setAlpha(bi,(byte)255);
		return bi;
	}
	
	public CAPTCHA(){
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics2D canvas = image.createGraphics();
		//Rectangle2D.Float r = new Rectangle2D.Float(0, 0, WIDTH, HEIGHT);
		//canvas.draw(r);
		//canvas.fill(r);
		AffineTransform at=new AffineTransform();
		StringBuffer stringb = new StringBuffer("01234");
		for(int i=0;i<5;i++){
			at.translate(10+30*i, 10);
			at.rotate((Math.random()-0.5));
			//Integer n=randomInt(10);
			//stringb.setCharAt(i, n.toString().charAt(0));
			String s=randomStr();
			stringb.setCharAt(i, s.charAt(0));
			canvas.drawImage(getImg(s), at,null);
			at.setToIdentity();
		}
		for(int i=0;i<9;i++){	
			canvas.setStroke(new BasicStroke(new Float(randomInt(4)+2), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, null, 0.0f));
			canvas.setColor(randomColor());
			canvas.drawLine(randomInt(WIDTH),randomInt(HEIGHTs)+10,randomInt(WIDTH),randomInt(HEIGHTs)+10);
		}
		string=stringb.toString();
	}
}
