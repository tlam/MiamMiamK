package com.tlam.miammiamk.models

import android.os.Parcel
import android.os.Parcelable

class Cuisine(
        val title: String,
        val genre: String,
        val foods: List<Food>
) : Parcelable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            arrayListOf<Food>().apply {
                parcel.readList(this, Food::class.java.classLoader)
            })

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
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
