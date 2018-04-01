package com.jakewharton.sdksearch.store

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import com.squareup.sqldelight.android.create

private const val VERSION = 3

internal class DbCallback : SupportSQLiteOpenHelper.Callback(VERSION) {
  override fun onCreate(db: SupportSQLiteDatabase) {
    QueryWrapper.onCreate(QueryWrapper.create(db).getConnection())
  }

  override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
    if (oldVersion < 3) {
      db.execSQL("DROP TABLE item")
      onCreate(db)
    }
  }
}
