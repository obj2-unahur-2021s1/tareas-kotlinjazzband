package ar.edu.unahur.obj2.tareas


abstract class Trabajador {
    var valorHora: Int = 0
    abstract fun sueldo(tarea:TareaSimple): Int
}
class Empleado: Trabajador() {
    override fun sueldo(tarea: TareaSimple): Int = this.valorHora * tarea.horasNecesarias()
}
class Responsable: Trabajador() {
    override fun sueldo(tarea:TareaSimple): Int = tarea.horasEstimadas * this.valorHora
}