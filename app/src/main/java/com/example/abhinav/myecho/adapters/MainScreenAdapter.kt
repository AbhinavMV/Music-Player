package com.example.abhinav.myecho.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.abhinav.myecho.R
import com.example.abhinav.myecho.Songs
import com.example.abhinav.myecho.fragments.SongPlayingFragment


class MainScreenAdapter(_songDetails: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {

    var songDetails: ArrayList<Songs>? = null
    var mcontext: Context? = null

    init {
        this.songDetails = _songDetails
        this.mcontext = _context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.drawer_custom_mainscreen, parent, false)

        val returnthis = MyViewHolder(View)
        return returnthis
    }

    override fun getItemCount(): Int {
        if (songDetails == null) {
            return 0
        } else return (songDetails as ArrayList<Songs>).size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = songDetails?.get(position)
        holder.track_Title?.text = songObject?.songTitle
        holder.track_Artist?.text = songObject?.artist
        holder.content_Holder?.setOnClickListener({
            val songPlayingFragment = SongPlayingFragment()
            var args = Bundle()
            args.putString("songArtist", songObject?.artist)
            args.putString("path", songObject?.songData)
            args.putString("songTitle", songObject?.songTitle)
            args.putInt("SongId", songObject?.songID?.toInt() as Int)
            args.putInt("songPosition", position)
            args.putParcelableArrayList("songData", songDetails)
            songPlayingFragment.arguments = args
            if (SongPlayingFragment.Statified.mediaplayer?.isPlaying() != null) {
                SongPlayingFragment.Statified.mediaplayer?.reset()
            }
            (mcontext as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, songPlayingFragment)
                    .addToBackStack("SongPlayingFragment")
                    .commit()
        })

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var track_Title: TextView? = null
        var track_Artist: TextView? = null
        var content_Holder: RelativeLayout? = null

        init {
            track_Title = view?.findViewById(R.id.trackTitle)
            track_Artist = view?.findViewById(R.id.trackArtist)
            content_Holder = view?.findViewById(R.id.contentRow)
        }
    }

}

