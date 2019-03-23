import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.net.*;
import java.io.*;
 
public class SoundPlayer2 implements LineListener
{
    private Sequencer sequencer;
    private Clip clip;
    private String type;
	private boolean playing;
    public SoundPlayer2(String m, String file)
    {
        type = m;
        clip = null;
        sequencer = null;
        playing = false;
        load(file);
    }
    public void load(String filepath)
    {
        if(type.equalsIgnoreCase("midi"))
            loadMidi(filepath);
        if(type.equalsIgnoreCase("au"))
            loadAu(filepath);
    }
    
    public void loadMidi(String filepath)
    {
        try
        {
        	InputStream in = getClass().getResourceAsStream(filepath);
            Sequence sequence = MidiSystem.getSequence(in);
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
        }
        catch(IOException ioe)
        {
            System.out.println("Error Reading: "+new File(filepath).getName() + " (" + ioe + ")");
        }
        catch(InvalidMidiDataException imde)
        {
            System.out.println("\n|         Not a MIDI File         |\n");
        }
        catch(MidiUnavailableException mue)
        {
            System.out.println("\n| MIDI Device is currently in use |\n");
        }
    }
    
    public void loadAu(String filepath)
    {
        try
        {
        	InputStream in = getClass().getResourceAsStream(filepath);
        	AudioInputStream stream = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
        	AudioFormat format = stream.getFormat();
        	if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
        	{
        			format = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    format.getSampleRate(),
                    format.getSampleSizeInBits()*2,
                    format.getChannels(),
                    format.getFrameSize()*2,
                    format.getFrameRate(),
                    true);
        			stream = AudioSystem.getAudioInputStream(format, stream);
        	}
    
        	DataLine.Info info = new DataLine.Info(
            Clip.class, stream.getFormat(), ((int)stream.getFrameLength()*format.getFrameSize()));
        	clip = (Clip) AudioSystem.getLine(info);
        	clip.open(stream);
        	clip.addLineListener(this);
        	stream.close();
    
        }
        catch (MalformedURLException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
        catch (LineUnavailableException e){e.printStackTrace();}
        catch (UnsupportedAudioFileException e){e.printStackTrace();}
    }
    
    public void close()
    {
        if(type.equalsIgnoreCase("midi"))
            closeMidi();
    }
    
    public void closeMidi()
    {
        try
        {
            sequencer.stop();
            sequencer.close();
        }
        catch(NullPointerException npe){}
        catch(IllegalStateException ise){}
        sequencer = null;
    }
    
    public void play()
    {
        if(type.equalsIgnoreCase("midi"))
            playMidi();
        if(type.equalsIgnoreCase("au"))
            playAu();
    }
    
    public void playMidi()
    {
        try
        {
            sequencer.start();
        }
        catch(NullPointerException npe){}
        catch(IllegalStateException ise){}
    }
    
    public void playAu()
    {
        clip.setMicrosecondPosition(0);
        clip.start();
    }
    
    public void stop()
    {
        if(type.equalsIgnoreCase("midi"))
            stopMidi();
    }
 
    public void stopMidi()
    {
        try
        {
            sequencer.stop();
            sequencer.setMicrosecondPosition(0);

        }
        catch(NullPointerException npe){}
        catch(IllegalStateException ise){}
    }
    
    public void pause()
    {
        if(type.equalsIgnoreCase("midi"))
            pauseMidi();
    }

    public void pauseMidi()
    {
        try
        {
            sequencer.stop();
        }
        catch(NullPointerException npe){}
        catch(IllegalStateException ise){}
    }
    
    public void loop()
    {
        if(type.equalsIgnoreCase("midi"))
            loopMidi();
    }

    public void loopMidi()
    {
        try
        {
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
        }
        catch(NullPointerException npe){}
    }
    
    public boolean isPlaying()
    {
    	return playing;
    }

	public void update(LineEvent event)
	{
		if(event.getType().equals(LineEvent.Type.STOP))
		{
			playing = false;
		}
		if(event.getType().equals(LineEvent.Type.START))
		{
			playing = true;
		}
	}
}