package hr.ferit.brunozoric.taskie.database

import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import hr.ferit.brunozoric.taskie.model.Priority

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromPriority(value:Priority):Int{
            return value.ordinal
        }

        @TypeConverter
        @JvmStatic
        fun toPriority(value: Int):Priority{
            return Priority.values()[value]
        }

    }

}