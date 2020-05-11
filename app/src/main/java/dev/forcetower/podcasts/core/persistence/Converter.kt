package dev.forcetower.podcasts.core.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.forcetower.podcasts.model.persistence.Thumbnails
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

object Converter {
    @TypeConverter
    @JvmStatic
    fun localDateTimeToLong(zoned: LocalDateTime?): Long? {
        return zoned?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    @JvmStatic
    fun longToLocalDateTime(value: Long?): LocalDateTime? {
        return if (value == null) null
        else LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())
    }

    @TypeConverter
    @JvmStatic
    fun zonedDateTimeToLong(zoned: ZonedDateTime?): Long? {
        return zoned?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    @JvmStatic
    fun longToZonedDateTime(value: Long?): ZonedDateTime? {
        return if (value == null) null
        else ZonedDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())
    }

    @TypeConverter
    @JvmStatic
    fun stringToThumbnail(value: String?): Thumbnails? {
        return if (value == null) null
        else Gson().fromJson(value, Thumbnails::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun thumbnailToString(value: Thumbnails?): String? {
        return if (value == null) null
        else Gson().toJson(value)
    }
}