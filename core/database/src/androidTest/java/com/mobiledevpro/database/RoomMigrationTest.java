package com.mobiledevpro.database;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.room.testing.MigrationTestHelper;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Test a database migrations
 * <p>
 * Created by Dmitri Chernysh
 * <p>
 * http://mobile-dev.pro
 */
@RunWith(AndroidJUnit4.class)
public class RoomMigrationTest {
    // private static final String TEST_DB = BuildConfig.AppDatabaseName;

    @Rule
    public MigrationTestHelper helper;

    public RoomMigrationTest() {
     /*   helper = new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                AppDatabase.class.getCanonicalName(),
                new FrameworkSQLiteOpenHelperFactory());

      */
    }

    @Test
    public void migrate5to6() throws IOException {
      /*  int currentVersion = 5;
        int newVersion = 6;
        int examQuestionId = 11111111;
        // Create earliest version of the database.
        SupportSQLiteDatabase dbCurrent = helper.createDatabase(TEST_DB, currentVersion);
        dbCurrent.close();

        //make project before run this
        try {
            helper.runMigrationsAndValidate(TEST_DB, newVersion, true, AppDatabase.MIGRATION_5_6);
        } catch (IllegalStateException e) {
            throw new IOException("Make project before run test for migration from 5 to 6. " + e.getLocalizedMessage());
        }

        //test inserting
        AppDatabase newDb = getMigratedDatabase();
        long[] result = newDb.examDao()
                .insertQuestionAttachments(
                        buildQuestionAttachmentToInsert(examQuestionId)
                );

        if (result.length == 0)
            throw new IOException("ExamQuestionAttachment is not inserting into a new database");

       */
    }


    // Array of all migrations
  /*  private static final Migration[] ALL_MIGRATIONS =
            new Migration[]{
                    AppDatabase.MIGRATION_5_6
            };

    private AppDatabase getMigratedDatabase() {
        // Open latest version of the database. Room will validate the schema
        // once all migrations execute.
        AppDatabase appDb = Room.databaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                AppDatabase.class,
                TEST_DB)
                .addMigrations(ALL_MIGRATIONS).build();
        appDb.getOpenHelper().getWritableDatabase();
        appDb.close();
        return appDb;
    }

    private ArrayList<ExamQuestionAttachment> buildQuestionAttachmentToInsert(int questionId) {
        ExamQuestionAttachment.File file = new ExamQuestionAttachment.File();
        file.setUploadTime(1593188528);

        ExamQuestionAttachment attachment = new ExamQuestionAttachment();
        attachment.setQuestionPrimaryId(questionId);
        attachment.setFileName("test.jpg");
        attachment.setFileType("image");
        attachment.setWebUrlHash("56f4as4df654as6df46as4d6f6");
        attachment.setStatus(2);
        attachment.setFilePath("");
        attachment.setFile(file);

        ArrayList<ExamQuestionAttachment> list = new ArrayList<>();
        list.add(attachment);

        return list;
    }

   */

}
