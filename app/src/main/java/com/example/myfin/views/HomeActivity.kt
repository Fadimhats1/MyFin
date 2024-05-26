package com.example.myfin.views

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfin.R
import com.example.myfin.adapters.transaction.TransactionAdapter
import com.example.myfin.databinding.ActivityHomeBinding
import com.example.myfin.databinding.DialogTransactionBinding
import com.example.myfin.domains.transaction.model.ListTransactionUIData
import com.example.myfin.domains.transaction.model.TransactionSaveOrUpdateUIData
import com.example.myfin.models.Transaction.RequestSaveOrUpdateTransaction
import com.example.myfin.models.global.UIState
import com.example.myfin.utils.customViews.GlobalDialog
import com.example.myfin.utils.enums.TransactionType
import com.example.myfin.utils.global.GlobalFunction
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var dialogTransaction: GlobalDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inits
        initDialogs()
        initObservers()

        transactionAdapter = TransactionAdapter { }.apply {
            submitList(listOf())
        }

        binding.rvTransactions.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        binding.fabAdd.setOnClickListener {
            if (supportFragmentManager.findFragmentByTag("TRANSACTION_DIALOG") == null)
                dialogTransaction.show(supportFragmentManager, "TRANSACTION_DIALOG")
        }

        viewModel.getAllTransaction()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.dataListTransaction.collect {
                    resolveState(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.dataIsSavedOrUpdated.collect {
                    resolveState(it)
                }
            }
        }
    }

    private fun resolveState(state: UIState) {
        when (state) {
            is UIState.Success<*> -> {
                state.data?.let {
                    when (it) {
                        is ListTransactionUIData -> {
                            if (it.isSuccess) {
                                transactionAdapter.submitList(it.listTransaction)
                            }
                        }

                        is TransactionSaveOrUpdateUIData -> {
                            if (it.isSuccess) {
                                dialogTransaction.dismiss()
                                Snackbar.make(
                                    binding.root,
                                    it.message ?: "Berhasil",
                                    Snackbar.LENGTH_LONG
                                ).show()
                                viewModel.getAllTransaction()
                            }
                        }
                    }
                }
            }

            else -> {

            }
        }
    }

    @SuppressLint("InflateParams")
    private fun initDialogs() {
        dialogTransaction = GlobalDialog {
            val dialog = Dialog(this)

            var currentTransactionType: TransactionType? = null
            var currentSource: Int? = null

            val materialDate = MaterialDatePicker.Builder.datePicker().apply {
                setTitleText("Pilih Tanggal")
                setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            }.build()

            val dialogBinding = DialogTransactionBinding.inflate(layoutInflater).apply {

                val sourceAdapter = ArrayAdapter(
                    this@HomeActivity,
                    android.R.layout.simple_expandable_list_item_1,
                    resources.getStringArray(R.array.source_transactions)
                )

                val transactionTypeAdapter = ArrayAdapter(
                    this@HomeActivity,
                    android.R.layout.simple_expandable_list_item_1,
                    resources.getStringArray(R.array.type_transactions)
                )

                spType.apply {
                    setAdapter(
                        transactionTypeAdapter
                    )
                    setOnItemClickListener { _, _, position, _ ->
                        currentTransactionType =
                            if (position == 0) TransactionType.IN else TransactionType.OUT
                    }
                }

                spSource.apply {
                    setAdapter(
                        sourceAdapter
                    )
                    setOnItemClickListener { _, _, position, _ ->
                        currentSource = position
                    }
                }

                etDate.setOnClickListener {
                    Log.e("tilDate", "initDialogs: BUKA")
                    if (supportFragmentManager.findFragmentByTag("DATE_DIALOG") == null)
                        materialDate.show(supportFragmentManager, "DATE_DIALOG")
                }

                etAmount.addTextChangedListener(object : TextWatcher {
                    private var current = etAmount.text.toString().trim()

                    override fun beforeTextChanged(
                        s: CharSequence,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        tilAmount.apply {
                            error = null
                            isErrorEnabled = false
                            clearFocus()
                        }

                        Log.e("on_text_changed", "Current: $current \n sToString: $s", )
                        if (s.toString() != current) {
                            etAmount.removeTextChangedListener(this)

                            val cleanString = s.toString().replace("[Rp,. ]".toRegex(), "")
                            if (cleanString.isNotEmpty()) {
                                current = GlobalFunction.formatCurrency(cleanString).replace("Rp. ", "")
                                etAmount.setText(current)
                                etAmount.setSelection(current.length)
                            }

                            etAmount.addTextChangedListener(this)
                        }
                    }

                    override fun afterTextChanged(s: Editable) {
                    }
                })

                etAmount.doOnTextChanged { text, start, before, count ->


                }

                btnAdd.setOnClickListener {
                    var isValid = true

                    when {
                        etAmount.text.isNullOrEmpty() -> {
                            isValid = false
                            tilAmount.apply {
                                error = "Jumlah tidak boleh kosong"
                                requestFocus()
                            }
                        }

                        currentTransactionType == null -> {
                            isValid = false
                            tilType.apply {
                                error = "Tipe transaksi tidak boleh kosong"
                                requestFocus()
                            }
                        }

                        etDate.text.isNullOrEmpty() -> {
                            isValid = false
                            tilDate.apply {
                                error = "Tanggal tidak boleh kosong"
                                requestFocus()
                            }
                        }

                        currentSource == null -> {
                            isValid = false
                            tilSource.apply {
                                error = "Sumber tidak boleh kosong"
                                requestFocus()
                            }
                        }
                    }

                    // Save or update transaction if valid
                    if (isValid) {
                        val payload = RequestSaveOrUpdateTransaction(
                            etAmount.text.toString().replace("[Rp,. ]".toRegex(), "").toLong(),
                            currentSource!!,
                            GlobalFunction.formatStringToLocalDate(etDate.text.toString()),
                            etReason.text?.toString() ?: "",
                            currentTransactionType!!
                        )
                        viewModel.saveOrUpdateTransaction(payload)
                    }
                }
            }

            materialDate.apply {
                addOnPositiveButtonClickListener {
                    dialogBinding.etDate.setText(this.headerText)
                }
            }

            dialog.apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            dialog
        }
    }
}