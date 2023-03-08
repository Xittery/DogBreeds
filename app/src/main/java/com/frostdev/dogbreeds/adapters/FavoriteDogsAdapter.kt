package com.frostdev.dogbreeds.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.databinding.CellFavouriteBinding
import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.injection.component.DaggerInjector
import com.frostdev.dogbreeds.injection.component.Injector
import com.frostdev.dogbreeds.injection.module.DataModule
import com.frostdev.dogbreeds.injection.module.NetworkModule
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.viewmodels.FavouriteDogViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteDogsAdapter() : RecyclerView.Adapter<FavoriteDogsAdapter.SingleDogViewHolder>() {

    @Inject
    lateinit var mPersistentDogs: PersistentDogs

    @Inject
    lateinit var dogApi: DogService

    private val injector: Injector = DaggerInjector
        .builder()
        .dataModule(DataModule())
        .networkModule(NetworkModule())
        .build()

    private lateinit var singleDogList: List<String>
    private var singleDogImageList: MutableList<Drawable>
    private lateinit var mRecycler: RecyclerView

    init {
        injector.inject(this)
        singleDogImageList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDogViewHolder {
        val binding: CellFavouriteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_favourite, parent, false)
        return SingleDogViewHolder(binding).listen { position, _ ->
            mPersistentDogs.addToFavouriteDogsSet(singleDogList[position])
            notifyItemChanged(position)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecycler = recyclerView
        (mRecycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    }

    override fun onBindViewHolder(holder: SingleDogViewHolder, position: Int) {
        println("")
        //holder.bindItems()
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
    fun updateSingleDogList(list: List<String>){
        this.singleDogList = list
        this.singleDogList.forEach {
            MainScope().launch {
                withContext(Dispatchers.IO) {
                    var x = dogApi.getSpecificImage(it).execute().body()
                    println(x)
                }
            }
            println("")
            //this.singleDogImageList.add()
        }

        notifyDataSetChanged()
    }

    class SingleDogViewHolder(private val binding: CellFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FavouriteDogViewModel()

        fun bindItems(image: Drawable) {
            viewModel.bind(image)
            binding.viewModel = viewModel
        }
    }
}
