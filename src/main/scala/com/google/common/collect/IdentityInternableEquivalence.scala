package com.google.common.collect

import com.google.common.base.Equivalence

/**
 * An equivalence implementation for `IdentityInternable`s.
 *
 * User: felix
 * Date: 09.04.13
 * Time: 09:04
 */
object IdentityInternableEquivalence extends Equivalence[AnyRef] {
  protected def doEquivalent(a: AnyRef, b: AnyRef): Boolean = a.asInstanceOf[IdentityInternable].internableEquals(b)

  protected def doHash(identityInternable: AnyRef): Int = identityInternable.asInstanceOf[IdentityInternable].internableHashCode()
}