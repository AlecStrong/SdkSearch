package com.jakewharton.sdksearch.store;

import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.content.Context;
import android.support.annotation.Nullable;
import com.squareup.sqldelight.android.SqlDelightDatabaseHelper;
import com.squareup.sqldelight.db.SqlDatabase;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module //
abstract class DbModule {
  @Provides static QueryWrapper queryWrapper(Context context, @Nullable String filename) {
    Configuration configuration = Configuration.builder(context)
        .callback(new DbCallback())
        .name(filename)
        .build();
    SqlDatabase db = new SqlDelightDatabaseHelper(new FrameworkSQLiteOpenHelperFactory().create(configuration));
    return new QueryWrapper(db);
  }
  
  @Provides static ItemQueries itemQueries(QueryWrapper queryWrapper) {
    return queryWrapper.getItemQueries();
  }

  @Binds abstract ItemStore itemStore(SqlItemStore impl);
}
