package com.example.myfin.adapters.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myfin.R
import com.example.myfin.models.Transaction

class TransactionAdapter :
    ListAdapter<Transaction, TransactionViewHolder>(TransactionDiffCallback()) {

    private class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.amount == newItem.amount || oldItem.transactionType == newItem.transactionType || oldItem.reason == newItem.reason || oldItem.source == newItem.source
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            parent.context,
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}