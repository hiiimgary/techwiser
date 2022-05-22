package com.hiimgary.techwiser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TechyDaoInstrumentationTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var techyDao: TechyDao

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun canAddFavorite() {
        val techy = TechyCacheEntity(1, "Hello World")
        runBlocking {
            techyDao.addFavorite(techy);
            assert(techyDao.getFavorites().contains(techy))
        }
    }

    @Test
    fun canUpdateFavorite() {
        val techy = TechyCacheEntity(1, "Hello World")
        runBlocking {
            techyDao.addFavorite(techy);
            techyDao.updateFavorite("Hello World", "Hello World 2");
            assert(!techyDao.getFavorites().contains(techy))
            assert(techyDao.getFavorites().contains(TechyCacheEntity(1, "Hello World 2")))
        }
    }

    @Test
    fun canDeleteFavorite() {
        val techy = TechyCacheEntity(1, "Hello World")
        runBlocking {
            techyDao.addFavorite(techy);
            techyDao.deleteFavorite("Hello World");
            assert(!techyDao.getFavorites().contains(techy))
        }
    }

    @Test
    fun canGetFavorites() {
        val techy1 = TechyCacheEntity(1, "Hello World")
        val techy2 = TechyCacheEntity(2, "Hello World 2")
        runBlocking {
            techyDao.addFavorite(techy1);
            techyDao.addFavorite(techy2);
            assert(techyDao.getFavorites().contains(techy1));
            assert(techyDao.getFavorites().contains(techy2));
        }
    }
}