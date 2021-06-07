package ar.edu.unahur.obj2.tareas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
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

  describe("Requerimientos Test tarea Simple") {
    it("nomina de empleados y responsable"){
    program.nomina().shouldContainExactly(juan,pedro,alberto,capporale)
    }
    it("horas necesarias para cumplir el tarea con los 3 empleados"){
      program.horasNecesarias().shouldBe(70)
    }
    it("costo de la tarea program"){
      program.costoTotalTarea().shouldBe(982500)
    }
  }

  val program2 = TareaSimple()
  program2.horasEstimadas = 300
  program2.costoInfraestructura= 250000
  //250000+120000+100000+250000+275000
  val hector = Empleado()
  hector.valorHora = 1200
  val ramon = Empleado()
  ramon.valorHora = 1000
  val horacio = Empleado()
  horacio.valorHora = 2500

  val fernandez = Responsable()
  fernandez.valorHora = 2750

  program2.agregarEmpleado(hector)
  program2.agregarEmpleado(ramon)
  program2.agregarEmpleado(horacio)
  program2.agregarResponsable(fernandez)

  describe("Requerimientos Test tarea de integracion program y program2") {
    it("program2 nomina de empleados y responsable"){
      program2.nomina().shouldContainExactly(hector,ramon,horacio,fernandez)
    }
    it("program2 horas necesarias para cumplir el tarea con los 3 empleados"){
      program2.horasNecesarias().shouldBe(100)
    }
    it("program2 costo de la tarea program2"){
      program2.costoTotalTarea().shouldBe(1545000)
    }

    val nuevatareaDeIntegracion = TareaIntegracion()

    val armando = Responsable()
    armando.valorHora = 3500

    nuevatareaDeIntegracion.agregarResponsable(armando)
    nuevatareaDeIntegracion.agregarSubtarea(program)
    nuevatareaDeIntegracion.agregarSubtarea(program2)

    it("nuevatareaDeIntegracion program2   horas de la tarea de integracion"){
      nuevatareaDeIntegracion.horasNecesariasDeSubtareas().shouldBe(510)
    }
    it("nuevatareaDeIntegracion costo program + costo program2 + 0.03 de total del costo de la tarea"){
      nuevatareaDeIntegracion.costoTotalTarea().shouldBe(2603325)
    }
    it("nuevatareaDeIntegracion = nomina de program y program2 agregados a la tareadeintegracion "){
      nuevatareaDeIntegracion.nomina().size.shouldBe(9)
    }
    it("nuevatareaDeIntegracion nomina de empleados reposnable y el resposnable de tareasdeintegracion"){
      nuevatareaDeIntegracion.nomina().shouldContainExactly(juan,pedro,alberto,capporale,hector,ramon,horacio,fernandez,armando)
    }
  }
})
