package com.lakooz.lpctest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lakooz.lpctest.database.AppDatabase
import com.lakooz.lpctest.databinding.PotsFragmentBinding
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.repositories.PotRepository
import kotlinx.android.synthetic.main.pots_fragment.*

class PotsFragment(var indice: Int) : Fragment() {
    private var pots: List<Pot> = ArrayList()
    private lateinit var viewModel :PotsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = PotsFragmentBinding.inflate(inflater, container, false)
        viewModel = PotsViewModel(activity!!.baseContext)

        // set up recycler view

        val rootView = inflater.inflate(R.layout.pots_fragment, container, false)
        addDataSet(rootView)

        val video_recyclerview = rootView.findViewById(R.id.recycler_view) as RecyclerView // Add this
        video_recyclerview.layoutManager = LinearLayoutManager(context)
        video_recyclerview.adapter = PotAdapter(context!!,rootView,pots)




      // TODO : set up view model

        return rootView
    }
    fun addDataSet(rootView: View){

         pots = viewModel.get(indice).sortedByDescending { pot -> pot.creationDate  }
           PotAdapter(context!!,rootView,pots).setPots(pots)



    }

}