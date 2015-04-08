package ie.dit;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Main extends PApplet
{
	Minim minim;
	AudioInput in;
	float min;
	float max;
	int sampleRate = 44100;
	
	public void setup()
	{
		size(2048, 500, OPENGL);
		smooth();
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO, width, sampleRate, 16);
		
		min = Float.MAX_VALUE;
		max = Float.MIN_VALUE;
	}
	
	public void draw()
	{
		background(0);
		stroke(255);
		float average = 0;
		
		for (int i = 0 ; i < in.bufferSize(); i ++)
		{
			float sample = in.left.get(i);
			
			if (sample < min)
			{
				min = sample;
			}
			
			if (sample > max)
			{
				max = sample;
			}
			sample *= 100.0;
			line(i, height / 2, i,  (height / 2) + sample);
			average += Math.abs(in.left.get(i));
			//point(i, (height / 2) + sample);
		}

		
		average /= in.bufferSize();
		
		fill(255);
		text("Amp: " + average, 10, 10);
		
		float smallRadius = 50;
		float bigRadius = (smallRadius * 2) + (average * 500);
		
		stroke(0, 255, 0);
		fill(0, 255, 0);
		ellipse(width / 2, height / 2, bigRadius, bigRadius);
		stroke(0);
		fill(0);
		ellipse(width / 2, height / 2, smallRadius, smallRadius);		
	}
	
	
	public static void main(String[] args)
	{
		PApplet.main(new String[] {"--present", "ie.dit.Main"});
	}


}
