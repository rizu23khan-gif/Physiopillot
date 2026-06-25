package com.example.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PucsParsingTest {

    @Test
    fun testParseAllAnatomyChapters() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = ChapterContentRepository

        for (i in 1..34) {
            val chId = "anat_ch$i"
            try {
                val ch = repo.loadChapterContent(context, chId)
                assertNotNull("$chId failed to parse", ch)
            } catch (e: Exception) {
                e.printStackTrace()
                fail("$chId threw exception: ${e.message}")
            }
        }
    }
}
