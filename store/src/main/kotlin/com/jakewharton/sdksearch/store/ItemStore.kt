package com.jakewharton.sdksearch.store

import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface ItemStore {
  fun count(): ReceiveChannel<Long>
  suspend fun updateItems(items: List<Item>)
  fun queryItems(term: String): ReceiveChannel<List<Item>>
}
