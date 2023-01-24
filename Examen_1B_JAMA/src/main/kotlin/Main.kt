fun main(args: Array<String>) {
    var oper: OperacionesCrud = OperacionesCrud()
    oper.SetearDatos()

    println("EXAMEN PRIMER BIMESTRE JULIO MORA AGUIRRE")
    var seguirExterno = 0
    while(seguirExterno === 0){
            println("Selecciona la Entidad de Interes:\n"+
                    "1. CELULAR\n" +
                    "2. APLICACION\n"+
                    "3. Salir")

                                var opcionPrincipal = readLine()!!.toInt()

                                if (opcionPrincipal == 1){

                                            var seguirInterno1 = 0

                                            while(seguirInterno1 === 0){

                                            println("Seleccione una opcion de interes:\n"+
                                                "1. Crear Celular\n" +
                                                "2. Leer Celular\n" +
                                                "3. Actualizar Celular\n" +
                                                "4. Eliminar Celular\n" +
                                                "5. Buscar Celular\n" +
                                                "6. Salir menu principal"
                                            )
                                                var opcionSecundaria1 = readLine()!!.toInt()

                                                if (opcionSecundaria1 == 1){
                                                    oper.crearCelular()
                                                }
                                                if (opcionSecundaria1 == 2){
                                                    oper.mostrarTodasAplicaciones()
                                                }
                                                if (opcionSecundaria1 == 3){
                                                    println("Escriba el id de un celular a actualizar:")
                                                    var idCelular: Int = readLine()!!.toInt()
                                                    if(idCelular >= 0 && idCelular < oper.rel.relacion.size){
                                                        oper.actualizacionCompletaCelular(idCelular)
                                                    }else {
                                                        println("Ingrese un id a actualizar existente")
                                                    }
                                                }
                                                if (opcionSecundaria1 == 4){

                                                    println("Escriba el id de un Celular a borrar:")
                                                    var idCelular: Int = readLine()!!.toInt()
                                                    if(idCelular >= 0 && idCelular < oper.rel.relacion.size){
                                                        oper.borrarAplicacionPorId(idCelular)
                                                    }else {
                                                        println("Ingrese un id a borrar existente")
                                                    }
                                                }

                                                if (opcionSecundaria1 == 5){
                                                    println("Escriba el id del Celular que desea buscar:")
                                                    var idCelular: Int = readLine()!!.toInt()
                                                    if(idCelular >= 0 && idCelular < oper.rel.relacion.size){
                                                        oper.obtenerCelularPorId(idCelular)
                                                    }else {
                                                        println("Ingrese un id existente")
                                                    }

                                                }
                                                if (opcionSecundaria1 == 6){
                                                    break;
                                                }else{

                                                /**println("Opcion no Valida")**/

                                                }

                                                println("Para volver al menu de opciones ingrese 0")
                                                seguirInterno1 = readLine()!!.toInt()
                                    }

                                }
                                if (opcionPrincipal == 2){

                                    var seguirInterno2 = 0

                                    while(seguirInterno2 === 0){

                                        println("Seleccione una opcion de interes:\n"+
                                                "1. Crear Aplicacion\n" +
                                                "2. Leer Aplicacion\n" +
                                                "3. Actualizar Aplicacion\n" +
                                                "4. Eliminar Aplicacion\n" +
                                                "5. Buscar Aplicacion\n" +
                                                "6. Salir menu principal"

                                        )
                                        var opcionSecundaria2 = readLine()!!.toInt()

                                        if (opcionSecundaria2 == 1){
                                            println("Escriba el id de un Celular para crear nuevas aplicaciones:")
                                            var idCelular: Int = readLine()!!.toInt()
                                            if(idCelular >= 0 && idCelular < oper.rel.relacion.size){
                                                oper.pedirCrearAplicacionesDeCelular(idCelular)
                                            }else {
                                                println("Ingrese un id de celular a agregar aplicaciones que exista")
                                            }
                                        }
                                        if (opcionSecundaria2 == 2){

                                            println("Escriba el id de un Celular a buscar:")
                                            var idCelular: Int = readLine()!!.toInt()
                                            println("Escriba el id de la aplicacion a buscar dentro del celular r con id "+idCelular+":")
                                            var idAplicacion: Int = readLine()!!.toInt()
                                            if((idCelular >= 0 && idCelular < oper.rel.relacion.size) && (idAplicacion >= 0 && idAplicacion < oper.rel.relacion.get(idCelular).aplicaciones.size)){
                                                oper.obtenerAplicacionDeCelular(idCelular, idAplicacion)
                                            }else {
                                                println("Ingrese un id de Celular e id de Aplicacion  existentes")
                                            }
                                        }


                                        if (opcionSecundaria2 == 3){
                                            println("Escriba el id de un Celular donde se va a actualizar la Aplicacion:")
                                            var idCelular: Int = readLine()!!.toInt()
                                            println("Escriba el id de la aplicacion a actualizar dentro del celular con id "+idCelular+":")
                                            var idAplicacion: Int = readLine()!!.toInt()
                                            if((idCelular >= 0 && idCelular < oper.rel.relacion.size) && (idAplicacion >= 0 && idAplicacion < oper.rel.relacion.get(idCelular).aplicaciones.size)){
                                                oper.actualizaraplicacionDeCelular(idCelular, idAplicacion)
                                            }else {
                                                println("Ingrese un id de celular y aplicacion existente para actualizar")
                                            }
                                        }
                                        if (opcionSecundaria2 == 4){
                                            println("Escriba el id de un Celular donde desea borrar la aplicacion:")
                                            var idCelular: Int = readLine()!!.toInt()
                                            println("Escriba el id de la aplicacion a borrar dentro del celular con id "+idCelular+":")
                                            var idAplicacion: Int = readLine()!!.toInt()
                                            if((idCelular >= 0 && idCelular < oper.rel.relacion.size) && (idAplicacion >= 0 && idAplicacion < oper.rel.relacion.get(idCelular).aplicaciones.size)){
                                                oper.borrarAplicacionDeUnCelular(idCelular,idAplicacion)
                                            }else {
                                                println("Ingrese un id de Celular y Aplicacion existentes para borrarlos")
                                            }
                                        }
                                        if (opcionSecundaria2 == 5){
                                            println("Escriba el id de un Celular a buscar:")
                                            var idCelular: Int = readLine()!!.toInt()
                                            println("Escriba el id de la aplicacion a buscar dentro de un Celular con id "+idCelular+":")
                                            var idAplicacion: Int = readLine()!!.toInt()
                                            if((idCelular >= 0 && idCelular < oper.rel.relacion.size) && (idAplicacion >= 0 && idAplicacion < oper.rel.relacion.get(idCelular).aplicaciones.size)){
                                                oper.obtenerAplicacionDeCelular(idCelular, idAplicacion)
                                            }else {
                                                println("Ingrese un id de celular y aplicaciones existentes")
                                            }
                                        }

                                        if (opcionSecundaria2 == 6){
                                            break;

                                        }else{
                                            /**println("Opcion no Valida")**/
                                        }

                                        println("Para volver al menu de opciones ingrese 0")
                                        seguirInterno2 = readLine()!!.toInt()
                                    }
                                }
                                if (opcionPrincipal == 3){
                                    break;
                                }else{
                                    /**println("Opcion no Valida")**/
                                }
        println("Para volver al menu de opciones principales digite 0")
        seguirExterno = readLine()!!.toInt()
    }

    }
