package com.example.chrislemelin.clicker.Managers;

import android.media.MediaCodec;
import android.media.MediaPlayer;
import android.content.Context;
import android.provider.MediaStore;
import android.util.Log;
import android.content.SharedPreferences;

import com.example.chrislemelin.clicker.R;


/**
 * Created by chrislemelin on 10/4/16.
 */

public class SoundManager
{
    private MediaPlayer musicPlayer;
    private MediaPlayer effectsPlayer;
    private Context context;
    private boolean music;
    private boolean effects;

    private SharedPreferences pref;

    public SoundManager(Context c, SharedPreferences pref)
    {
        this.pref = pref;
        context = c;
        this.music = pref.getBoolean("music_on",true);
        this.effects = pref.getBoolean("effects_on",true);

        if(music)
        {
            if(musicPlayer== null)
            {
                musicPlayer = MediaPlayer.create(c, R.raw.loopingmusic);
                musicPlayer.setVolume(1f, 1f);
                musicPlayer.setLooping(true);
            }
        }
        else
        {
            musicPlayer = MediaPlayer.create(c,R.raw.loopingmusic);
            musicPlayer.setVolume(0f,0f);
        }
    }

    public void startMusic()
    {
        musicPlayer.start();

    }

    public void startEffect(int effect)
    {
        if(effects)
        {
            try
            {
                if(effectsPlayer!=null)
                {
                    effectsPlayer.stop();
                }
                effectsPlayer = MediaPlayer.create(context, effect);
                effectsPlayer.start();
            } catch (Exception e) {
                Log.d("effect not found", "given effect " + effect);
            }
        }
    }

    public void setEffects(Boolean effects)
    {
        this.effects = effects;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(context.getString(R.string.pref_effects),this.effects);
        editor.commit();
    }

    public void setMusic(Boolean music)
    {
        this.music = music;
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(context.getString(R.string.pref_music),this.music);
        editor.commit();
        if(music)
        {
            musicPlayer.setVolume(1f,1f);
        }
        else
        {
            musicPlayer.setVolume(0f,0f);
        }
    }

    public void pauseMusic()
    {
        musicPlayer.pause();
    }

}
