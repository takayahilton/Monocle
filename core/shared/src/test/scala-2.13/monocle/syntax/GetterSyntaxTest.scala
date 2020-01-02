package monocle.syntax

import monocle.Getter
import monocle.implicits._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class GetterSyntaxTest extends AnyFunSuite with Matchers {
  case class Foo(map: Map[Int, String], list: List[Int], tuple: (String, Char, Boolean, Int, Long, Double))
  val foo = Foo(
    map = Map(1 -> "One", 2 -> "Two"),
    list = List(1, 2, 3),
    tuple = ("a", 'b', true, 1, 1L, 1.0)
  )
  val map: Getter[Foo, Map[Int, String]]                             = Getter[Foo, Map[Int, String]](_.map)
  val tuple: Getter[Foo, (String, Char, Boolean, Int, Long, Double)] = Getter(_.tuple)

  test("_1") {
    tuple._1.get(foo) shouldEqual "a"
    foo.optic(tuple)._1.get shouldEqual "a"
  }

  test("_2") {
    tuple._2.get(foo) shouldEqual 'b'
    foo.optic(tuple)._2.get shouldEqual 'b'
  }

  test("_3") {
    tuple._3.get(foo) shouldEqual true
    foo.optic(tuple)._3.get shouldEqual true
  }

  test("_4") {
    tuple._4.get(foo) shouldEqual 1
    foo.optic(tuple)._4.get shouldEqual 1
  }

  test("_5") {
    tuple._5.get(foo) shouldEqual 1L
    foo.optic(tuple)._5.get shouldEqual 1L
  }

  test("_6") {
    tuple._6.get(foo) shouldEqual 1.0
    foo.optic(tuple)._6.get shouldEqual 1.0
  }

  test("reverse") {
    tuple.reverse.get(foo) shouldEqual (1.0, 1L, 1, true, 'b', "a")
    foo.optic(tuple).reverse.get shouldEqual (1.0, 1L, 1, true, 'b', "a")
  }

  test("at") {
    map.at(1).get(foo) shouldEqual foo.map.get(1)
    foo.optic(map).at(1).get shouldEqual foo.map.get(1)
  }
}
