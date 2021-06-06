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

  describe("Sueldo de Pedro"){
    it("sueldo de pedro"){
      pedro.sueldo(program).shouldBe(70000)
    }
    it("Sueldo de Juan"){
      juan.sueldo(program).shouldBe(140000)
    }
    it("Sueldo de Alberto"){
      alberto.sueldo(program).shouldBe(105000)
    }
    it("Sueldo de capporale"){
      capporale.sueldo(program).shouldBe(367500)
    }

    it("nomina de Empleados"){
      program.empleados.shouldContainExactly(juan,pedro, alberto)
    }
  }

  describe("Requerimientos") {
    it("nomina de empleados y responsable"){
    program.nominaEmpleadosYResponsable().shouldContainExactly(juan,pedro,alberto,capporale)
    }
    it("horas necesarias para cumplir el tarea con los 3 empleados"){
      program.horasNecesarias().shouldBe(70)
    }
    it("costo de la tarea program"){
      program.costoTotalTarea().shouldBe(982500)
    }
  }
})
