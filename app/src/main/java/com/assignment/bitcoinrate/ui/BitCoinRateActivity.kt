package com.assignment.bitcoinrate.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.assignment.bitcoinrate.R
import com.assignment.bitcoinrate.data.BitCoinDetails
import com.assignment.bitcoinrate.data.BitCoinRateRoomDatabase
import com.assignment.bitcoinrate.data.BitCoinRepository
import kotlinx.android.synthetic.main.activity_main.*

class BitCoinRateActivity : AppCompatActivity() {
    private val database by lazy { BitCoinRateRoomDatabase.getDatabase(this) }
    private val repository by lazy { BitCoinRepository(database.bitCoinDao()) }
    private val workManager by lazy { WorkManager.getInstance(application) }

    class SomeViewModelFactory(
        private val workManager: WorkManager,
        private val repo: BitCoinRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            BitCoinRateViewModel(workManager, repo) as T
    }

    private val bitCoinRateViewModel: BitCoinRateViewModel by viewModels {
        SomeViewModelFactory(
            workManager,
            repository
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bitCoinRateViewModel.getBitCoinRate()
        bitCoinRateViewModel.bitCoinDetails.observe(this, bitCoinRateObserver())
    }

    private fun bitCoinRateObserver(): Observer<BitCoinDetails> {
        return Observer { bitCoinRate ->
            bitCoinRate?.let {
                bit_coin_rate_textView.text = bitCoinRate.rate + " $"
            }

        }
    }

}