package com.persil.droidrecorder;
import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

public class Sound {

	private double currentTime;
	private double totalTime;
	private MediaPlayer mediaPlayer = null;
	private Uri currentUri;
	private boolean state;
	
public Sound(){
	if (mediaPlayer == null) {
	mediaPlayer = new MediaPlayer();
	}
	currentTime = 0;
	totalTime = 0;
	state = false;
}


public double getCurrentTime() {
	if (mediaPlayer!=null) {
	currentTime = mediaPlayer.getCurrentPosition();
	return currentTime;
	}
	else {
		return 0;
	}
}


public double getTotalTime() {
	if (mediaPlayer!=null) {
	totalTime = mediaPlayer.getDuration();
	return totalTime;
	}
	else {
		return 0;
	}
}

@SuppressWarnings("static-access")
public void PrepareSound(Context context, String path) {
	SoundReset();
	if (mediaPlayer == null) {
	mediaPlayer = new MediaPlayer();
	}
	currentUri.parse(path);
	mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
     try {
    	 mediaPlayer.setDataSource(context, currentUri);
     } catch (IllegalArgumentException e) {
         Toast.makeText(context, "Fail Uri...", Toast.LENGTH_LONG).show();
     } catch (SecurityException e) {
         Toast.makeText(context, "Fail Uri...", Toast.LENGTH_LONG).show();
     } catch (IllegalStateException e) {
         Toast.makeText(context, "Fail Uri...", Toast.LENGTH_LONG).show();
     } catch (IOException e) {
         e.printStackTrace();
     }
     try {
    	 mediaPlayer.prepare();
     } catch (IllegalStateException e) {
         Toast.makeText(context, "Fail Uri...", Toast.LENGTH_LONG).show();
     } catch (IOException e) {
         Toast.makeText(context, "Fail Uri...", Toast.LENGTH_LONG).show();
     }
}

public void StartAndPause() {
	if (mediaPlayer!=null && mediaPlayer.isPlaying() && state == true) {
		mediaPlayer.pause();
		state = false;
	}
	else if (state == false){
		mediaPlayer.start();
		state = true;
	}

}

public void Stop() {
	  if(mediaPlayer!=null && mediaPlayer.isPlaying()){
		  state = false;
		  mediaPlayer.stop();
		  mediaPlayer.reset();
		  mediaPlayer.release();
		  }
}

public void SoundReset() {
	 if(mediaPlayer!=null && mediaPlayer.isPlaying()){
	  mediaPlayer.stop();
	 }
	 if (mediaPlayer!=null) {
	 mediaPlayer.reset();
	  mediaPlayer.release();
	 }

}




}
