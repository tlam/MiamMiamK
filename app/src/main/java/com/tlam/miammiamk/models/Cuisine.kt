package com.tlam.miammiamk.models

import android.os.Parcel
import android.os.Parcelable

import com.tlam.miammiamk.database.DbModel

class Cuisine(
        val id: Long,
        val name: String,
        val genre: String,
        var foods: MutableList<Food>
) : Parcelable, DbModel() {

    private constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            arrayListOf<Food>().apply {
                parcel.readList(this, Food::class.java.classLoader)
            })

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(name)
        dest.writeString(genre)
        dest.writeList(foods)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Cuisine> {
            override fun createFromParcel(parcel: Parcel) = Cuisine(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Cuisine>(size)
        }
    }
}
