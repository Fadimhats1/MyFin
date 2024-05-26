package com.example.myfin.adapters.transaction

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfin.R
import com.example.myfin.databinding.ItemTransactionBinding
import com.example.myfin.domains.transaction.model.TransactionUIModel
import com.example.myfin.utils.enums.TransactionType
import com.example.myfin.utils.global.GlobalFunction

class TransactionAdapter(val onEventCallback: (TransactionUIModel) -> Unit) :
    ListAdapter<TransactionUIModel, TransactionAdapter.TransactionViewHolder>(
        TransactionDiffCallback()
    ) {

    private class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionUIModel>() {
        override fun areItemsTheSame(
            oldItem: TransactionUIModel, newItem: TransactionUIModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TransactionUIModel, newItem: TransactionUIModel
        ): Boolean {
            return oldItem.amount == newItem.amount && oldItem.transactionType == newItem.transactionType && oldItem.reason == newItem.reason && oldItem.source == newItem.source
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TransactionViewHolder(val context: Context, val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: TransactionUIModel) {
            binding.apply {
                tvAmount.text = GlobalFunction.formatCurrency(item.amount.toString())
                tvKe.text = " #" + (layoutPosition + 1).toString()
                tvAmount.setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (item.transactionType == TransactionType.IN) R.color.green_in else R.color.red_out
                    )
                )
                Glide.with(context)
                    .load(if (item.transactionType == TransactionType.IN) R.drawable.ic_arrow_upward else R.drawable.ic_arrow_downward)
                    .into(ivIconItem)
                ivIconItem.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        if (item.transactionType == TransactionType.IN) R.color.green_in else R.color.red_out
                    ), PorterDuff.Mode.SRC_IN
                )
                tvDate.text = GlobalFunction.formatLocaleDateToString(item.date!!)
            }
            onEventCallback(item)
        }
    }
}