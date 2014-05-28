package com.google.common.collect

import org.scalatest.FunSuite

/**
 * Tests for IdentityInterners.
 *
 * User: Felix
 * Date: 19.11.12
 * Time: 09:43
 */
class TestInternable(val i: Int) extends IdentityInternable {

  def canEqual(other: Any): Boolean = other.isInstanceOf[TestInternable]

  override def internableEquals(other: AnyRef): Boolean = other match {
    case that: TestInternable =>
      (that canEqual this) &&
        i == that.i
    case _ => false
  }

  override def internableHashCode(): Int = i.hashCode()
}

class IdentityInternersTest extends FunSuite {
  test("Strong interner works") {
    val interner: Interner[TestInternable] = IdentityInterners.newStrongIdentityInterner()
    val internable1: IdentityInternable = interner.intern(new TestInternable(1))
    val internable2: IdentityInternable = interner.intern(new TestInternable(1))
    val internable3: IdentityInternable = interner.intern(new TestInternable(2))
    assert(internable2 === internable1)
    assert(internable3 !== internable1)
  }

  test("Weak interner works") {
    val interner: Interner[TestInternable] = IdentityInterners.newWeakIdentityInterner()
    val internable1: IdentityInternable = interner.intern(new TestInternable(1))
    val internable2: IdentityInternable = interner.intern(new TestInternable(1))
    val internable3: IdentityInternable = interner.intern(new TestInternable(2))
    assert(internable2 === internable1)
    assert(internable3 !== internable1)
  }
}