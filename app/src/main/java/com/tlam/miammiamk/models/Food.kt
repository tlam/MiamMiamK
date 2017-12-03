package com.tlam.miammiamk.models

import android.os.Parcel
import android.os.Parcelable

class Food(val title: String, val description: String) : Parcelable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Food> {
            override fun createFromParcel(parcel: Parcel) = Food(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Food>(size)
        }
    }
}
