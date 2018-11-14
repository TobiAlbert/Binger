package ng.max.binger.data

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun dateToLong(date: Date) = date.time

        @TypeConverter
        @JvmStatic
        fun longToDate(long: Long) = Date(long)

    }
}