package ar.edu.unahur.obj2.tareas


abstract class Tarea() {
    var horasEstimadas = 0
    var costoInfraestructura = 0

    val empleados = mutableListOf<Empleado>()
    fun agregarEmpleado(empleado:Empleado) = empleados.add(empleado)
    fun nominaDeEmpleados() = empleados
    fun horasNecesarias() = horasEstimadas / empleados.size

    abstract fun costoTotalTarea(): Int
}

class TareaSimple() :Tarea() {

    fun costoPorSueldosEmpleados():Int = empleados.sumBy { it.sueldo(this) }
    override fun costoTotalTarea(): Int = costoPorSueldosEmpleados() + costoInfraestructura
}

class TareaIntegracion : Tarea() {
    override fun costoTotalTarea(): Int {
        TODO("costoSubtareas + bono(3% del costoSubtareas)")
    }
}

abstract class Trabajador(tarea:Tarea) {
    var valorHora = 0
    fun cantidadHoras(tarea:Tarea) {}
    abstract fun sueldo(tarea:Tarea): Int
}

class Empleado(tarea: Tarea): Trabajador(tarea) {
    override fun sueldo(tarea: Tarea): Int = this.valorHora * tarea.horasNecesarias()
}
class Encargado(tarea: Tarea): Trabajador(tarea){
    override fun sueldo(tarea:Tarea): Int = tarea.horasEstimadas * this.valorHora
}

interface calculo {
    fun costoTotalTarea()
    fun horasTrabajadas()
}

/*
***costoTareaSimple =  Sumatoria de (horasDeTrabajo * valorHoraEmp) + (valorHoraEncarg * horasEstimadas) + costoInfraestructura
* horasTrabajoPorEmpleado = Horas estimadas / Cantidad de empleados

***Costo de Tarea de Integración = costoSubtareas + bono(3% del costoSubtareas)
* Horas de trabajo de cada empleado = planificación (1 x cada 8 de trabajo) + horasSubtareas

*/
