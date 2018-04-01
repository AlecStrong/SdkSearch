package com.jakewharton.sdksearch.store

import kotlin.IllegalArgumentException

object ItemUtil {
  private val PACKAGE = "^([a-z0-9]+.)+".toRegex()

  fun createForInsert(fqcn: String, link: String, deprecated: Boolean): Item {
    val range = PACKAGE.find(fqcn)?.range
    if (range == null) {
      throw IllegalArgumentException("FQCN '$fqcn' doesn't appear to be valid.")
    }
    val packageName = fqcn.substring(range.start, range.endInclusive)
    val className = fqcn.substring(range.endInclusive + 1)
    return Item.Impl(-1, packageName, className, deprecated, link)
  }
}
