
public class SoundManager
{
    private static SoundPlayer[] back;
    private static SoundPlayer[] effect;
    public SoundManager()
    {
        back = new SoundPlayer[2];
        back[0] = new SoundPlayer("midi", "Audio/02.mid");
        back[1] = new SoundPlayer("midi", "Audio/battle.mid");
        
        effect = new SoundPlayer[3];
        effect[0] = new SoundPlayer("au", "Audio/Fire.au");
        effect[1] = new SoundPlayer("au", "Audio/Sword.au");
        effect[2] = new SoundPlayer("au", "Audio/Hit.au");
        
        back[0].loop();
    }
    public void play(int n)
    {
        effect[n].play();
    }
    public void changeBackground(boolean type)
    {
        if(type)
        {
            back[0].stop();
            back[1].loop();
        }
        else
        {
            back[1].stop();
            back[0].loop();
        }
    }
    public boolean isPlaying()
    {
        for(int i = 0; i < effect.length; i++)
        {
            if(effect[i].isPlaying())
                return true;
        }
        return false;
    }
    public void end()
    {
    	back[0].close();
    	back[1].close();
    }
}