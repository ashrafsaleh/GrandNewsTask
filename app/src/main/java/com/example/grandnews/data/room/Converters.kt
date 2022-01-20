package com.example.grandnews.data.room

import androidx.room.TypeConverter
import com.example.grandnews.data.remote.Oembed
import com.example.grandnews.data.remote.SecureMedia
import javax.xml.transform.Source

class Converters {
    @TypeConverter
    fun fromSource(source: SecureMedia?): String {
        return source?.oembed?.thumbnail_url.toString()
    }

    @TypeConverter
    fun toSource(oembed: String): SecureMedia {
        return SecureMedia(Oembed(oembed,oembed),"xx")
    }

    @TypeConverter
    fun fromOembed(source: Oembed?): String {
        return source?.thumbnail_url.toString()
    }

    @TypeConverter
    fun toOembed(oembed: String): Oembed {
        return Oembed(oembed,oembed)
    }

}