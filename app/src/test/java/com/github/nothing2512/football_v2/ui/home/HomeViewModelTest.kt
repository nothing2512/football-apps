package com.github.nothing2512.football_v2.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class HomeViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private val viewModel = mockk<HomeViewModel>()

    @Test
    fun loadFragment() {

        val data = Fragment()
        val fragment = MutableLiveData<Fragment>()
        fragment.value = data
        every { viewModel.fragment } returns fragment
        every { viewModel.submitQuery() } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel.fragment
        verify { viewModel.fragment }
        confirmVerified(viewModel)
        assertThat(viewModel.fragment.value, `is`(data))
    }
}