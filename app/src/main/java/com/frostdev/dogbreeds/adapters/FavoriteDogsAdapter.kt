package com.frostdev.dogbreeds.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.databinding.CellFavouriteBinding
import com.frostdev.dogbreeds.helpers.GetDrawable
import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.injection.Initialization
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
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class FavoriteDogsAdapter() : RecyclerView.Adapter<FavoriteDogsAdapter.SingleDogViewHolder>() {

    @Inject
    lateinit var mPersistentDogs: PersistentDogs

    @Inject
    lateinit var dogApi: DogService

    @Inject
    lateinit var getDrawable: GetDrawable

    private val injector: Injector = DaggerInjector
        .builder()
        .dataModule(DataModule())
        .networkModule(NetworkModule())
        .build()

    lateinit var singleDogList: MutableLiveData<MutableList<String>>
    private var singleDogImageList: MutableList<Drawable> = mutableListOf()
    private lateinit var mRecycler: RecyclerView

    init {
        injector.inject(this)
        singleDogList = MutableLiveData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDogViewHolder {
        val binding: CellFavouriteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_favourite, parent, false)
        return SingleDogViewHolder(binding).listen { position, _ ->
            mPersistentDogs.removeFromFavouriteDogsSet(singleDogList.value!![position])
            singleDogList.value!!.removeAt(position)
            updateSingleDogList(singleDogList.value!!)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecycler = recyclerView
        (mRecycler.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    }

    override fun onBindViewHolder(holder: SingleDogViewHolder, position: Int) {
        holder.bindItems(singleDogImageList[position])
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItemCount(): Int {
        return singleDogImageList.size
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateSingleDogList(list: MutableList<String>){
        MainScope().launch {
            singleDogList.value = list
            withContext(Dispatchers.IO) {
                singleDogImageList.clear()
                singleDogList.value!!.forEach {
                    getDrawable.drawableFromUrl(it)?.let { it1 -> singleDogImageList.add(it1) }
                }
            }
            this@FavoriteDogsAdapter.notifyDataSetChanged()
        }
    }

    class SingleDogViewHolder(private val binding: CellFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FavouriteDogViewModel()

        fun bindItems(image: Drawable) {
            viewModel.bind(image)
            binding.viewModel = viewModel
            binding.isFavourite.setImageDrawable(Initialization.contextComponent.inject().getDrawable(R.drawable.favourite))
        }
    }
}
