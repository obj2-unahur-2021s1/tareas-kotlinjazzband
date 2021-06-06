package ar.edu.unahur.obj2.tareas

import java.lang.Math.abs

abstract class Tarea {
    var horasEstimadas = 0
    val responsableDeTarea = mutableListOf<Responsable>()
    val empleados = mutableListOf<Empleado>()

    var costoInfraestructura = 0
    fun agregarEmpleado(empleado:Empleado) = empleados.add(empleado)
    fun agregarResponsable(responsable: Responsable) = responsableDeTarea.add(responsable)
    fun nominaDeEmpleados() = empleados
    fun nominaEmpleadosYResponsable(): List<Trabajador> = nominaDeEmpleados() + responsableDeTarea
    open fun horasNecesarias() = horasEstimadas / empleados.size
    abstract fun costoTotalTarea(): Int

}

class TareaSimple :Tarea() {
    fun costoPorSueldosEmpleados():Int = empleados.sumBy { it.sueldo(this) }
    fun costoPorSueldoResponsable():Int = responsableDeTarea.sumBy { it.sueldo(this) }
    override fun costoTotalTarea(): Int = costoPorSueldosEmpleados() + costoPorSueldoResponsable() + costoInfraestructura
}

class TareaIntegracion : Tarea() {
    val subtareas = mutableListOf<Tarea>()
    val horasNecesariasDeSubtareas = subtareas.sumBy { it.horasEstimadas }
    val costoSubtareas = subtareas.sumBy { it.costoTotalTarea() }

    override fun horasNecesarias(): Int = horasNecesariasDeSubtareas + abs(horasNecesariasDeSubtareas / 8)
    override fun costoTotalTarea(): Int = (costoSubtareas + costoSubtareas* 0.3).toInt()
}

abstract class Trabajador {
    var valorHora = 0
    //fun cantidadHoras(tarea:Tarea) {}
    abstract fun sueldo(tarea:Tarea): Int
}

class Empleado: Trabajador() {
    override fun sueldo(tarea: Tarea): Int = this.valorHora * tarea.horasNecesarias()
}
class Responsable: Trabajador(){
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
