package com.nileshdeokar.healthapp.database

import android.arch.persistence.room.TypeConverter
import java.sql.Blob





class BlobConverter {

    @TypeConverter
    fun fromBlob(data: Blob?): ByteArray? {

        val blobLength = data?.length()?.toInt()
        val blobAsBytes = data?.getBytes(1, blobLength!!)

        data?.free()

        return blobAsBytes
    }

  /*  @TypeConverter
    fun fromByteArray(model: ByteArray?): Blob? {
        return if (model == null) null else Blob(model)
    }*/
}