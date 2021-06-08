package ar.edu.unahur.obj2.tareas

abstract class Trabajador(open val valorHora: Int) {
    abstract fun sueldo(tarea:TareaSimple): Int
}
class Empleado(override val valorHora: Int): Trabajador(valorHora) {
    override fun sueldo(tarea: TareaSimple): Int = this.valorHora * tarea.horasNecesarias()
}
class Responsable(override val valorHora: Int): Trabajador(valorHora) {
    override fun sueldo(tarea:TareaSimple): Int = tarea.horasEstimadas * this.valorHora
}