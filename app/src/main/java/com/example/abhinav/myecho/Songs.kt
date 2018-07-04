package com.example.abhinav.myecho

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable


@SuppressLint("ParcelCreator")
class Songs(var songID: Long, var songTitle: String, var artist: String, var songData: String, var DateAdded: Long) : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    object Statified {
        var nameComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.songTitle.toUpperCase()
            val songtwo = song2.songTitle.toUpperCase()
            songOne.compareTo(songtwo)
        }

        var dateComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.DateAdded.toDouble()
            val songTwo = song2.DateAdded.toDouble()
            songTwo.compareTo(songOne)
        }
    }

}