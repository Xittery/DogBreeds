package com.frostdev.dogbreeds.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.databinding.CellSingleDogBinding
import com.frostdev.dogbreeds.fragments.AllDogsFragment
import com.frostdev.dogbreeds.model.SingleDog
import com.frostdev.dogbreeds.viewmodels.SingleDogViewModel
import java.util.ArrayList

class AllDogsAdapter(breedSelectionListener: AllDogsFragment.BreedSelectionListener) : RecyclerView.Adapter<AllDogsAdapter.SingleDogViewHolder>() {

    private lateinit var singleDogList:List<SingleDog>
    private var mBreedSelectionListener = breedSelectionListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDogViewHolder {
        val binding: CellSingleDogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_single_dog, parent, false)
        return SingleDogViewHolder(binding).listen { position, type ->
            this.mBreedSelectionListener.onBreedSelected(singleDogList[position].breed, singleDogList[position].subBreeds as ArrayList<String>)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
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

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
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
