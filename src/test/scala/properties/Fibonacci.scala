package properties

import org.scalacheck._
import org.scalacheck.Prop.forAll
import algorithms.dynamic.{DPFibonacci, recFibonacci}

object Fibonacci extends Properties("Math") {

  property("fibonacci") = forAll(Gen.choose(2, 100)) { (n: Int) =>
    DPFibonacci(n)._1 == recFibonacci(n)._1
  }

}
