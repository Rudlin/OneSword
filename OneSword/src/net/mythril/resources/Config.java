package net.mythril.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config 
{
	int WIDTH, HEIGHT;
	
	Properties p = new Properties();
	InputStream configfile;

	boolean sync;

	public Config()
	{
		try {
			configfile = new FileInputStream("rsc/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		try {
			p.load(configfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WIDTH = Integer.parseInt(p.getProperty("screen_width"));
		HEIGHT = Integer.parseInt(p.getProperty("screen_height"));
		sync = Boolean.parseBoolean(p.getProperty("vsync"));
	}

	public int getWidth() {
		return WIDTH;
	}

	public void setWidth(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public void setHeight(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	
	public boolean isSyncEnabled() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}
}
