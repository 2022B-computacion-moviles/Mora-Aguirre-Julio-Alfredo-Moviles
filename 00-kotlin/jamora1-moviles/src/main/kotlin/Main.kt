import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    //Variables inmutables: (no se pueden reasingar)

    val inmutable: String = "Julio";

    var mutable:String = "Aldredo";

    //Variables mutables: (se pueden reasignar)

    mutable = "Mora";

    println(mutable+inmutable);

    //Variables a usar
    //Duck Typing

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

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"


    val sumaUno = Suma(1, 2)
    var sumaDos = Suma(1, null)
    var sumaTres = Suma(null, 2)
    var sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.historialSumas)


    //-------------------------------------------------

    //Arreglos

    //Tipos de arreglos

    //Arreglo estático
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    //println(arregloEstatico)

    //Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    //println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    //println(arregloDinamico)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos

    val respuestaForEach: Unit = arregloDinamico.forEach { valorActual: Int ->
        //println("Valor actual: ${valorActual}")
    }
    arregloEstatico.forEachIndexed { indice: Int, valorActual: Int ->
        //println("Valor actual: ${valorActual} Indice: ${indice}")
    }
    //println(respuestaForEach)



    val respuestaMap: List<Double> = arregloDinamico.map { valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)
//        .map { valorActual: Int ->
//            return@map valorActual + 15
//        }


    val respuestaFilter: List<Int> = arregloDinamico.filter { valorActual: Int ->
        val mayoresACinco: Boolean = valorActual > 5 //Expresion condicion
        return@filter mayoresACinco
    }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND

    val respuestaAny: Boolean = arregloDinamico.any { valorActual: Int ->
        return@any (valorActual > 5)
    }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico.all { valorActual: Int ->
        return@all (valorActual > 5)
    }
    println(respuestaAll) // false

    //REDUCE -> Valor acumulado


    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce) //78


    //------------------------------------------------
}

//Funciones:
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
        if (uno == null) 0 else uno,
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

    fun sumar (): Int {

        val total = numeroUno + numeroDos

        agregarHistoria(total)

        return total
    }

    companion object{ //todo lo que se tenga aqui, pueden ser metodos disponibles dentro de esta clase
    val pi = 3.14// suma suma.pi ---> 3.14
        val historialSumas = arrayListOf<Int>()//suma.historialSumas
        fun agregarHistoria(valorNuevaSuma: Int){//Suma.agregarHistorial
            historialSumas.add(valorNuevaSuma)

        }
    }
}