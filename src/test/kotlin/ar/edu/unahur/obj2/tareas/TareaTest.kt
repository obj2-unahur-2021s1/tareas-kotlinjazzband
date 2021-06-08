package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({

  val tarea1 = TareaSimple(300000,210)
  val pedro = Empleado(1000)
  val juan = Empleado(2000)
  val alberto = Empleado(1500)
  val capporale = Responsable(1750)

  tarea1.agregarEmpleado(juan)
  tarea1.agregarEmpleado(pedro)
  tarea1.agregarEmpleado(alberto)
  tarea1.agregarResponsable(capporale)

  describe("Testeando la Tarea Simple"){
    it("sueldo de pedro"){
      pedro.sueldo(tarea1).shouldBe(70000)
    }
    it("Sueldo de Juan"){
      juan.sueldo(tarea1).shouldBe(140000)
    }
    it("Sueldo de Alberto"){
      alberto.sueldo(tarea1).shouldBe(105000)
    }
    it("Sueldo de capporale"){
      capporale.sueldo(tarea1).shouldBe(367500)
    }
    it("nomina de Empleados"){
      tarea1.empleados.shouldContainExactly(juan,pedro, alberto)
    }
  }

  describe("Requerimientos del enunciado para Tarea Simple tarea1") {
    it("Nomina de empleados y responsable Tarea Simple"){
      tarea1.nomina().shouldContainExactly(juan,pedro,alberto,capporale)
    }
    it("horas necesarias para cumplir el tarea1 con los 3 empleados"){
      tarea1.horasNecesarias().shouldBe(70)
    }
    it("costo de la tarea1 "){
      tarea1.costoTotalTarea().shouldBe(982500)
    }
  }

  val tarea2 = TareaSimple(250000,300)
  val hector = Empleado(1200)
  val ramon = Empleado(1000)
  val horacio = Empleado(2500)
  val fernandez = Responsable(2750)

  tarea2.agregarEmpleado(hector)
  tarea2.agregarEmpleado(ramon)
  tarea2.agregarEmpleado(horacio)
  tarea2.agregarResponsable(fernandez)

  describe("Requerimientos Test Tarea de Integracion tarea1 y tarea2") {
    it("tarea2 nomina de empleados y responsable"){
      tarea2.nomina().shouldContainExactly(hector,ramon,horacio,fernandez)
    }
    it("program2 horas necesarias para cumplir el tarea con los 3 empleados"){
      tarea2.horasNecesarias().shouldBe(100)
    }
    it("program2 costo de la tarea program2"){
      tarea2.costoTotalTarea().shouldBe(1545000)
    }

    val haciendoTareas = TareaIntegracion()
    val armando = Responsable(3500)

    haciendoTareas.agregarResponsable(armando)
    haciendoTareas.agregarSubtarea(tarea1)
    haciendoTareas.agregarSubtarea(tarea2)

    it("haciendoTareas horas de la tarea de integracion"){
      haciendoTareas.horasNecesariasDeSubtareas().shouldBe(510)
    }
    it("haciendoTareas costo tarea1 + costo tarea2 + 0.03 (3%) de total del costo de la tarea"){
      haciendoTareas.costoTotalTarea().shouldBe(2603325)
    }
    it("haciendoTareas = nomina en numeros de tarea1 + tarea1 + responsable de la tarea de integracion "){
      haciendoTareas.nomina().size.shouldBe(9)
    }
    it("haciendoTareas nomina de empleados y sus responsable + resposnable de tareas de integracion"){
      haciendoTareas.nomina().shouldContainExactly(juan,pedro,alberto,capporale,hector,ramon,horacio,fernandez,armando)
    }
  }
})
