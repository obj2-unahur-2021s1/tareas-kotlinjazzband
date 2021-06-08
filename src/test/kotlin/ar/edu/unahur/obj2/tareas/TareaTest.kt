package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class TareaTest : DescribeSpec({

  val program = TareaSimple()
  program.horasEstimadas = 210
  program.costoInfraestructura= 300000

  val pedro = Empleado()
  pedro.valorHora = 1000
  val juan = Empleado()
  juan.valorHora = 2000
  val alberto = Empleado()
  alberto.valorHora = 1500

  val capporale = Responsable()
  capporale.valorHora = 1750

  program.agregarEmpleado(juan)
  program.agregarEmpleado(pedro)
  program.agregarEmpleado(alberto)
  program.agregarResponsable(capporale)

  describe("Testeando la Tarea Simple "){
    it("sueldo de pedro de la Tarea Simple"){
      pedro.sueldo(program).shouldBe(70000)
    }
    it("Sueldo de Juan de la Tarea Simple"){
      juan.sueldo(program).shouldBe(140000)
    }
    it("Sueldo de Alberto de la Tarea Simple"){
      alberto.sueldo(program).shouldBe(105000)
    }
    it("Sueldo de capporale de la Tarea Simple"){
      capporale.sueldo(program).shouldBe(367500)
    }
    it("Costo Total de Tarea Simple"){
      program.costoTotalTarea().shouldBe(982500)
    }
    it("Empleados de program"){
      program.empleados.shouldContainExactly(juan, pedro, alberto)
    }
  }

  describe("Requerimientos del enunciado para Tarea Simple") {
    it("Nomina de empleados y responsable de la Tarea Simple"){
    program.nomina().shouldContainExactly(juan,pedro,alberto,capporale)
    }
    it("horas necesarias para cumplir el Tarea Simple con los 3 empleados"){
      program.horasNecesarias().shouldBe(70)
    }
    it("Costo Total de Tarea Simple program"){
      program.costoTotalTarea().shouldBe(982500)
    }
  }

  describe("Testeo de Tareas de Integración"){
    val integrando = TareaSimple()
    integrando.horasEstimadas = 90
    integrando.agregarEmpleado(juan)
    integrando.agregarEmpleado(pedro)
    integrando.agregarEmpleado(alberto)

    val finalizando = TareaSimple()
    finalizando.horasEstimadas = 30
    finalizando.agregarEmpleado(juan)
    finalizando.agregarEmpleado(pedro)

    val haciendoCosas = TareaIntegracion()
    haciendoCosas.agregarResponsable(capporale)
    haciendoCosas.agregarSubtarea(integrando)
    haciendoCosas.agregarSubtarea(finalizando)
    it(" costo de sueldo de subtarea integrando "){
      integrando.costoPorSueldosEmpleados().shouldBe(135000)
    }
    it(" costo de sueldo de subtarea finalizando "){
      finalizando.costoPorSueldosEmpleados().shouldBe(45000)
    }
    it(" Tarea de integración integrando nomina de Empleado"){
      haciendoCosas.costoSubtareas().shouldBe(180000)
    }
    it("Costo Total de las tareas de integración resp =  5400  es el 3% "){
      haciendoCosas.costoTotalTarea().shouldBe(185400)
    }
    it("Cantidad de horas necesarias Incluye Horas de planificación (15) "){
      haciendoCosas.horasNecesarias().shouldBe(135)
    }
    it("Nomina de empleado de tarea de integración"){
      haciendoCosas.nomina().shouldContainExactly(juan, pedro, alberto, juan, pedro, capporale)
    }

  }

})
