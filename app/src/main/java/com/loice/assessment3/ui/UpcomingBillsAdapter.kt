package com.loice.assessment3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.loice.assessment3.databinding.UpcomingBillsListItemBinding
import com.loice.assessment3.model.UpcomingBill
import com.loice.assessment3.utils.dateTimeUtils

class UpcomingBillsAdapter (var upcomingbill: List<UpcomingBill>,private val onClickBill: OnClickBill):Adapter<UpcomingBillsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UpcomingBillsViewHolder {
        val binding=UpcomingBillsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpcomingBillsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
        val upcomingBill=upcomingbill[position]
        holder.binding.apply {
            cbUpccoming.isChecked=upcomingBill.paid
            cbUpccoming.text=upcomingBill.name
            tvamount.text=upcomingBill.amount.toString()
            tvDueDate.text=dateTimeUtils.formatDateReadable(upcomingBill.dueDate)
        }
        holder.binding.cbUpccoming.setOnClickListener {
            onClickBill.onCheckBoxMarked(upcomingBill)
        }
    }

    override fun getItemCount(): Int {
      return upcomingbill.size
    }
}

class UpcomingBillsViewHolder(var binding:UpcomingBillsListItemBinding): ViewHolder(binding.root)

interface OnClickBill{
    fun onCheckBoxMarked(upcomingbill: UpcomingBill){

    }
}