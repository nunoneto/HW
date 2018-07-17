package pt.nunoneto.hw.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.property_list_fragment.*
import pt.nunoneto.hw.R
import pt.nunoneto.hw.entities.Property

class PropertyListFragment : Fragment() {

    companion object {
        fun newInstance() : PropertyListFragment {
            return PropertyListFragment()
        }
    }

    private lateinit var viewModel: PropertyListViewModel
    private lateinit var adapter: PropertyListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.property_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PropertyListViewModel::class.java)

        setUiComponents()
        setObservers()
    }

    private fun setUiComponents() {
        adapter = PropertyListAdapter(context!!)

        rv_property_list.layoutManager = LinearLayoutManager(context)
        rv_property_list.adapter = adapter
    }

    private fun setObservers() {
        viewModel.propertyList.observe(this, Observer<List<Property>> {
            properties: List<Property>? ->  updateAdapter(properties)
        })

        viewModel.error.observe(this, Observer<Boolean> {
            error -> onError(error)
        })
    }

    private fun onError(error: Boolean?) {
        if (error == true) {
            pb_load_properties.visibility = View.GONE
            tv_error.visibility = View.VISIBLE
        }
    }

    private fun updateAdapter(properties: List<Property>?) {
        if (properties == null) {
            return
        }

        pb_load_properties.visibility = View.GONE
        rv_property_list.visibility = View.VISIBLE

        adapter.properties = properties
        adapter.notifyDataSetChanged()
    }
}