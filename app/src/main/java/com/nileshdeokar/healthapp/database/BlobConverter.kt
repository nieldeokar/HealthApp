package com.nileshdeokar.healthapp.database

import android.arch.persistence.room.TypeConverter
import java.sql.Blob
import com.sun.org.apache.xml.internal.utils.StringBufferPool.free





class BlobConverter {

    @TypeConverter
    fun fromBlob(data: Blob?): ByteArray? {

        val blobLength = data.length() as Int
        val blobAsBytes = data.getBytes(1, blobLength)

//release the blob and free up memory. (since JDBC 4.0)
        blob.free()
    }

    @TypeConverter
    fun fromByteArray(model: ByteArray?): Blob? {
        return if (model == null) null else Blob(model)
    }
}