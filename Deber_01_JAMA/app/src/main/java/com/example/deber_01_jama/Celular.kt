package com.example.deber_01_jama

import android.os.Parcel
import android.os.Parcelable

class Celular(
    var codCelular: String?,
    var nombreCelular: String?,
    var numeroCamaras: Int?,
    var fechaFabricacion: String?,
    var dobleLinea: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {

    }


    override fun toString(): String {
        return "${codCelular} - ${nombreCelular}"
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //Escribir String
        parcel.writeString(codCelular)
        parcel.writeString(nombreCelular)
        parcel.writeInt(numeroCamaras!!)
        parcel.writeString(fechaFabricacion)
        parcel.writeString(dobleLinea)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Celular> {
        override fun createFromParcel(parcel: Parcel): Celular {
            return Celular(parcel)
        }

        override fun newArray(size: Int): Array<Celular?> {
            return arrayOfNulls(size)
        }


    }
}