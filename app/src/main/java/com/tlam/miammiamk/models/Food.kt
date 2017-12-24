package com.tlam.miammiamk.models

import android.os.Parcel
import android.os.Parcelable

import com.tlam.miammiamk.database.DbModel

data class Food(
        val id: Long,
        val name: String,
        val description: String,
        val source: String,
        var cuisineId: Long
) : Parcelable, DbModel() {

    private constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(source)
        dest.writeLong(cuisineId)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Food> {
            override fun createFromParcel(parcel: Parcel) = Food(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Food>(size)
        }
    }
}
