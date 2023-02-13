package com.example.deber_01_jama

import android.os.Parcel
import android.os.Parcelable

class Aplicacion (
    var id: Int?,
    var nombreAplicacion: String?,
    var descripcion: String?,
    var valoraciones: String?,
    var numeroDescargas: Int?,
    var cod: String?,
) : Parcelable {
    override fun toString(): String {
        return "${id} - ${nombreAplicacion} - ${descripcion}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(nombreAplicacion)
        parcel.writeString(descripcion)
        parcel.writeString(valoraciones)
        parcel.writeInt(numeroDescargas!!)
        parcel.writeString(cod)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Aplicacion> {
        override fun createFromParcel(parcel: Parcel): Aplicacion {
            return Aplicacion(parcel)
        }

        override fun newArray(size: Int): Array<Aplicacion?> {
            return arrayOfNulls(size)
        }
    }
}