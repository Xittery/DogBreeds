package com.example.dogbreeds.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreeds.R
import com.example.dogbreeds.databinding.CellSingleDogBinding
import com.example.dogbreeds.fragments.AllDogsFragment
import com.example.dogbreeds.model.SingleDog
import com.example.dogbreeds.viewmodels.SingleDogViewModel

class FavoriteDogsAdapter : RecyclerView.Adapter<FavoriteDogsAdapter.SingleDogViewHolder>() {

    private lateinit var singleDogList:List<SingleDog>
    private lateinit var mRecycler: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDogViewHolder {
        val binding: CellSingleDogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_single_dog, parent, false)
        return SingleDogViewHolder(binding)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecycler = recyclerView
        (mRecycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    }

    override fun onBindViewHolder(holder: SingleDogViewHolder, position: Int) {
        holder.bindItems(singleDogList[position])
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItemCount(): Int {
        return if(::singleDogList.isInitialized) singleDogList.size else 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSingleDogList(list: List<SingleDog>){
        this.singleDogList = list
        notifyDataSetChanged()
    }

    class SingleDogViewHolder(private val binding: CellSingleDogBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = SingleDogViewModel()

        fun bindItems(dog: SingleDog) {
            viewModel.bind(dog)
            binding.viewModel = viewModel
        }
    }
}
