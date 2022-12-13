import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val inmutable: String = "Julio";

    var mutable:String = "Aldredo";

    mutable = "Mora";

    println(mutable+inmutable);

    //duck typing

    val ejemploVariable = "Ejemplo"

    ejemploVariable.trim()

    val edadEjemplo: Int = 12;
    //variables primitivas

    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadocivil: Char = 'S'
    var mayordeedad = true

    //clases

    val fechaNacimiento: Date = Date()

    //el switch no existe
    val estadoCivilWhen = "S"
    when(estadoCivilWhen){
        ("S")-> {
            println("Soltero")
        }

        "C"-> println("Casado")
        else -> println("Desconocido")

    }

    //val coqueteo = if (estadocivil)
}


fun imprimirNombre(nombre: String): Unit {

    println("Nombre: ${nombre}")

}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null// el incognito dice que puede un double que algun momento puede tomar el valor de null

): Double {

    if (bonoEspecial != null){
        return sueldo * tasa * bonoEspecial
    }
    return sueldo * tasa
}

// clases, clase abstracta


abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno =  uno;
        this.numeroDos = dos;
        println("Iniciando")
    }


}

//algo màs suaveton

abstract  class  Numeros (

    protected val numeroUno: Int,
    protected val numeroDos: Int
){
    init {
        numeroDos
        numeroDos
        println("Iniciando")
    }
}

class Suma(

    uno: Int,
    dos: Int,
) : Numeros (
    uno,
    dos,
){
    init {
        this.numeroUno
        this.numeroDos
    }


    constructor ( // segundo constructor
        uno: Int?,
        dos: Int
    ): this(
        if (uno == null) 0 else uno,git
        dos
    ){}

    constructor (
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if (dos == null) 0 else dos,

    ){}


    constructor (
        uno: Int?,
        dos: Int ?
    ): this(
        if (uno == null) 0 else uno,
        if (uno == null) 0 else dos,
    ){}


}