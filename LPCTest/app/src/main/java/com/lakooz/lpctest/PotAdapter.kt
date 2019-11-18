package com.lakooz.lpctest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lakooz.lpctest.databinding.PotItemBinding
import com.lakooz.lpctest.databinding.PotsFragmentBinding
import com.lakooz.lpctest.model.Pot
import kotlinx.android.synthetic.main.pots_fragment.view.*


class PotAdapter(val context: Context, val emptyView: View? = null,val pots: List<Pot>) : RecyclerView.Adapter<PotAdapter.ViewHolder>() {


   private var potss: List<Pot>? = ArrayList<Pot>()
    private var recyclerView: RecyclerView?= RecyclerView(context)

    init {
        println("init: $pots")
    }
   fun setPots(pots: List<Pot>?) {
            this.potss = pots
        // TODO : notify data change and handle empty view
        println("test post: "+pots!!.isEmpty())

        if(potss!!.isEmpty())
        {
            emptyView!!.videRelative.visibility =  View.VISIBLE
            recyclerView?.visibility = View.GONE
        }
        else{
           // emptyView!!.visibility = View.GONE

            println("pots: $potss")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       //TODO
        val binding: PotItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.pot_item, parent, false)
        println("create View Pots: $pots")
        if(pots.isEmpty())
        {
            emptyView!!.videRelative.visibility =  View.VISIBLE
            recyclerView?.visibility = View.GONE
        }
        else{
            // emptyView!!.visibility = View.GONE

            println("pots: $pots")
        }
        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {
        // TODO
        return pots.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO : bind view holder & format amount properly
        println("pots!!: $pots")
        holder.bind(pots[position])
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = null

        super.onDetachedFromRecyclerView(recyclerView)
    }

    inner class ViewHolder(private val binding: PotItemBinding) : RecyclerView.ViewHolder(binding.getRoot()) {
        fun bind(item: Pot) {
            with(binding) {

                println("item: $item")

                title.text = item.name
                amount.text = item.amount.toString()+"€ récoltés"
                countributors.text = item.contributorsCount.toString()
                println("item.imageUrl: "+item.imageUrl)
                println(item.imageUrl != null)
                if( item.imageUrl!= null) {
                    Glide.with(context).load(item.imageUrl).into(img)
                }
            }


            }

    }
}