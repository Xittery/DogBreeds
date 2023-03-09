package com.frostdev.dogbreeds.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.databinding.CellDetailBinding
import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.injection.Initialization
import com.frostdev.dogbreeds.injection.component.DaggerInjector
import com.frostdev.dogbreeds.injection.component.Injector
import com.frostdev.dogbreeds.injection.module.DataModule
import com.frostdev.dogbreeds.model.SingleDog
import com.frostdev.dogbreeds.viewmodels.SingleDogViewModel
import javax.inject.Inject
import kotlin.math.sin

class DetailAdapter() : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    @Inject
    lateinit var mPersistentDogs: PersistentDogs
    private val injector: Injector = DaggerInjector
        .builder()
        .dataModule(DataModule())
        .build()

    private lateinit var detailDogList: List<SingleDog>
    private var favDogList: MutableSet<String>?

    init {
        injector.inject(this)
        favDogList = mPersistentDogs.getFavouriteDogsSet()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding: CellDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_detail, parent, false)
        return DetailViewHolder(binding).listen { position, _ ->
            if(mPersistentDogs.addOrRemoveToFavouriteDogsSet(detailDogList[position].imageUrl)) {
                binding.isFavourite.setImageDrawable(Initialization.contextComponent.inject().getDrawable(R.drawable.favourite))
            } else {
                binding.isFavourite.setImageDrawable(Initialization.contextComponent.inject().getDrawable(R.drawable.notfavourite))
            }
            favDogList = mPersistentDogs.getFavouriteDogsSet()
            notifyItemChanged(position)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindItems(detailDogList[position], favDogList)
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

        fun bindItems(singleDog: SingleDog, favoriteList: MutableSet<String>?) {
            viewModel.bind(singleDog)
            binding.viewModel = viewModel
            Glide.with(itemView.context).load(singleDog.imageUrl).into(binding.detailImage)
            favoriteList?.forEach {
                if(it == singleDog.imageUrl) {
                    binding.isFavourite.setImageDrawable(Initialization.contextComponent.inject().getDrawable(R.drawable.favourite))
                }
            }
        }
    }
}
