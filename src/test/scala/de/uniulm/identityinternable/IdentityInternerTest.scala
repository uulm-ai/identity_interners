package de.uniulm.identityinternable

import org.scalatest.FunSuite

/**
 * Tests for IdentityInterners.
 *
 * User: Felix
 * Date: 19.11.12
 * Time: 09:43
 */
case class TestInternable(i: Int) extends IdentityInternable

class IdentityInternerTest extends FunSuite {
  test("Strong interner works") {
    val interner: IdentityInterner[TestInternable] = IdentityInterner.newStrongIdentityInterner()
    val internable1: TestInternable = interner.intern(TestInternable(1))
    val internable2: TestInternable = interner.intern(TestInternable(1))
    val internable3: TestInternable = interner.intern(TestInternable(2))
    assert(internable2 === internable1)
    assert(internable3 !== internable1)
    assert(internable1 !== TestInternable(1)) // without interning, no IdentityInternable is equal to another
  }

  test("Weak interner works") {
    val interner: IdentityInterner[TestInternable] = IdentityInterner.newWeakIdentityInterner()
    val internable1: TestInternable = interner.intern(TestInternable(1))
    val internable2: TestInternable = interner.intern(TestInternable(1))
    val internable3: TestInternable = interner.intern(TestInternable(2))
    assert(internable2 === internable1)
    assert(internable3 !== internable1)
    assert(internable1 !== TestInternable(1)) // without interning, no IdentityInternable is equal to another
  }
}