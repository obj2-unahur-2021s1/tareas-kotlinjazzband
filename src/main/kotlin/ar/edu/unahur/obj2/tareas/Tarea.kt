package ar.edu.unahur.obj2.tareas

import java.lang.Math.abs

interface Tarea {
    fun nomina(): List<Trabajador>
    fun horasNecesarias(): Int
    fun costoTotalTarea(): Int
}

class TareaSimple :Tarea {
    val responsableDeTarea= mutableListOf<Trabajador>()
    var costoInfraestructura = 0
    var horasEstimadas = 0
    val empleados = mutableListOf<Trabajador>()

    fun agregarResponsable(responsable: Trabajador) = responsableDeTarea.add(responsable)
    fun agregarEmpleado(empleado:Trabajador) = empleados.add(empleado)
    override fun horasNecesarias() = horasEstimadas / empleados.size
    override fun nomina() : List<Trabajador> = empleados + responsableDeTarea
    fun costoPorSueldosEmpleados():Int = empleados.sumBy { it.sueldo(this) }
    fun costoPorSueldoResponsable():Int = responsableDeTarea.sumBy { it.sueldo(this) }
    override fun costoTotalTarea(): Int = costoPorSueldosEmpleados() + costoPorSueldoResponsable() + costoInfraestructura
}

class TareaIntegracion : Tarea {
    val responsableDeTarea= mutableListOf<Trabajador>()
    val subtareas = mutableListOf<TareaSimple>()

    fun agregarResponsable(responsable: Trabajador) = responsableDeTarea.add(responsable)
    override fun nomina() : List<Trabajador> = subtareas.map{ it.nomina() }.flatten() + responsableDeTarea
    fun agregarSubtarea(tarea: TareaSimple) = subtareas.add(tarea)
    fun costoSubtareas() = subtareas.sumBy { it.costoTotalTarea() }
    fun horasNecesariasDeSubtareas() = subtareas.sumBy { it.horasEstimadas }
    override fun horasNecesarias(): Int = this.horasNecesariasDeSubtareas() + abs(this.horasNecesariasDeSubtareas() / 8)
    override fun costoTotalTarea(): Int = (this.costoSubtareas() + this.costoSubtareas()* 0.03).toInt()
}

