package com.example.vendo.vendor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendo.Adapter.myorderAdapterParent
import com.example.vendo.Model.ChildparentModel
import com.example.vendo.Model.Myorderparentmodel
import com.example.vendo.R


class HomeFragmentVendor : Fragment() {
    private lateinit var orderparentlist : ArrayList<Myorderparentmodel>
    private lateinit var childorderparentlist : ArrayList<ChildparentModel>
    private lateinit var myorderAdapterParent: myorderAdapterParent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home_vendor, container, false)
        val recyclerview : RecyclerView = view.findViewById(R.id.parent_recyclerView)

        orderparentlist = ArrayList()

        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        orderparentlist.add(Myorderparentmodel("OrderId : AQWE13343344", "jogeshwari east", "20 mins", "20 march 2022", "120 Rs"))
        childorderparentlist = ArrayList()
        childorderparentlist.add(ChildparentModel(1, "onion cheeze pizza"))
        childorderparentlist.add(ChildparentModel(1, "onion cheeze pizza"))

        myorderAdapterParent = myorderAdapterParent(orderparentlist,requireContext(),childorderparentlist)
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = myorderAdapterParent
        return  view;
    }

}