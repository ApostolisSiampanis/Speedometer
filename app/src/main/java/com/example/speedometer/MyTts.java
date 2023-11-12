package com.example.speedometer;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MyTts {
    private TextToSpeech tts;
    TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if (i == TextToSpeech.SUCCESS){
                tts.setLanguage(Locale.ENGLISH);
            }
        }
    };
    public MyTts(Context context){
        tts = new TextToSpeech(context, initListener);
    }
    public void speak(String message){
        tts.speak(message,TextToSpeech.QUEUE_ADD,null,null);
    }

    public void stopSpeaking() {
        if (tts != null) {
            tts.stop();
        }
    }
}
