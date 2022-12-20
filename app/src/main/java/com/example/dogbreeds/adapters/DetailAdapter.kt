package com.example.dogbreeds.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreeds.R
import com.example.dogbreeds.activities.DetailActivity
import com.example.dogbreeds.databinding.CellDetailBinding
import com.example.dogbreeds.model.SingleDog
import com.example.dogbreeds.viewmodels.SingleDogViewModel

class DetailAdapter(detailSelectionListener: DetailActivity.DetailSelectionListener) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private lateinit var detailDogList: List<SingleDog>
    private var mDetailSelectionListener = detailSelectionListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding: CellDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_detail, parent, false)
        return DetailViewHolder(binding).listen { position, type ->
            this.mDetailSelectionListener.onImageSelected(detailDogList[position].image)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindItems(detailDogList[position])
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItemCount(): Int {
        return if(::detailDogList.isInitialized) detailDogList.size else 0
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateImageList(list: List<SingleDog>){
        this.detailDogList = list
        notifyDataSetChanged()
    }

    class DetailViewHolder(private val binding: CellDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = SingleDogViewModel()

        fun bindItems(singleDog: SingleDog) {
            viewModel.bind(singleDog)
            binding.viewModel = viewModel
        }
    }
}
