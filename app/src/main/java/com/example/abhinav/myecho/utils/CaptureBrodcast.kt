package com.example.abhinav.myecho.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.example.abhinav.myecho.R
import com.example.abhinav.myecho.activities.MainActivity
import com.example.abhinav.myecho.fragments.SongPlayingFragment

class CaptureBrodcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var isPaused: Boolean = false
        var state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        try {
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                try {
                    MainActivity.Statified.notificationManager?.cancel(8014)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                        SongPlayingFragment.Statified.mediaplayer?.pause()
                        SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        isPaused = true

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                try {
                    MainActivity.Statified.notificationManager?.cancel(8014)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean === false) {
                        if (isPaused) {
                            isPaused = false
                            SongPlayingFragment.Statified.mediaplayer?.start()
                            SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.pause_icon)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                try {
                    MainActivity.Statified.notificationManager?.cancel(8014)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    if (SongPlayingFragment.Statified.mediaplayer?.isPlaying as Boolean) {
                        SongPlayingFragment.Statified.mediaplayer?.pause()
                        SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        isPaused = true

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
