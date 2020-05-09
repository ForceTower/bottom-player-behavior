package dev.forcetower.podcasts.core.service.converters

import com.google.gson.JsonDeserializer
import com.google.gson.JsonParseException
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object TimeConverters {
    @JvmStatic
    val ZDT_DESERIALIZER: JsonDeserializer<ZonedDateTime> = JsonDeserializer { json, _, _ ->
        val jsonPrimitive = json.asJsonPrimitive
        try {
            // if provided as String - '2011-12-03 10:15:30'
            if (jsonPrimitive.isString) {
                val patterns = arrayOf(
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    "yyyy-MM-dd'T'HH:mm:ssX",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd'T'HH:mmX",
                    "yyyy-MM-dd'T'HH:mmZ"
                )
                for (pattern in patterns) {
                    try {
                        val parser = DateTimeFormatter.ofPattern(pattern)
                        return@JsonDeserializer ZonedDateTime.parse(jsonPrimitive.asString, parser)
                    } catch (t: Throwable) { }
                }
            }

            // if provided as Long
            if (jsonPrimitive.isNumber) {
                return@JsonDeserializer ZonedDateTime.ofInstant(Instant.ofEpochMilli(jsonPrimitive.asLong), ZoneId.systemDefault())
            }
        } catch (e: RuntimeException) {
            throw JsonParseException("Unable to parse ZonedDateTime", e)
        }

        throw JsonParseException("Unable to parse ZonedDateTime")
    }
}