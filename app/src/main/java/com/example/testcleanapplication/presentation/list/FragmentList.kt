package com.example.testcleanapplication.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcleanapplication.R
import com.example.testcleanapplication.controllers.TodoAdapter
import com.example.testcleanapplication.controllers.TodosItemInteraction
import com.example.testcleanapplication.databinding.FragmentListBinding
import com.example.testcleanapplication.domain.model.TodosModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FragmentList : Fragment(), TodosItemInteraction {

    lateinit var binding: FragmentListBinding

    private val viewModel by activityViewModels<ListViewModel>()

    @Inject
    lateinit var adapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnGetListLivedata.setOnClickListener {
            viewModel.getTodoListByLiveData()
        }


        btn_get_list_flow.setOnClickListener {

            lifecycleScope.launch {
               viewModel.getTodoListByFlow()
               viewModel.todosStateFlow.collectLatest {

                   when {

                       it.isLoading -> {
                           binding.progressbar.visibility = View.VISIBLE
                           binding.txtError.visibility = View.GONE
                       }

                       it.error != "" -> {

                           binding.progressbar.visibility = View.GONE
                           binding.txtError.visibility = View.VISIBLE

                           if (it.error.contains("400")) {
                               binding.txtError.text = "No data found!!!"
                           } else {
                               binding.txtError.text = it.error
                           }

                       }

                       it.data != null -> {
                           setListData(it.data)  // set current data
                           binding.progressbar.visibility = View.GONE
                           binding.txtError.visibility = View.GONE

                       }

                   }

               }
            }
        }



        viewModel.todosLivedata.observe(viewLifecycleOwner) {

            when {

                it.isLoading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.txtError.visibility = View.GONE
                }

                it.error != "" -> {

                    binding.progressbar.visibility = View.GONE
                    binding.txtError.visibility = View.VISIBLE

                    if (it.error.contains("400")) {
                        binding.txtError.text = "No data found!!!"
                    } else {
                        binding.txtError.text = it.error
                    }

                }

                it.data != null -> {
                    setListData(it.data)  // set current data
                    binding.progressbar.visibility = View.GONE
                    binding.txtError.visibility = View.GONE

                }

            }

        }

    }








    private fun setListData(list: List<TodosModel>) {

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        adapter.setListener(this)
        adapter.submitList(list)
        binding.recyclerView.adapter = adapter

    }


    override fun itemClicked(model: TodosModel?) {

//        val gson = Gson()
//        val json = gson.toJson(model)
//
//          findNavController().navigate(
//              resId = R.id.action_fragmentList_to_fragmentDetail,
//              args = Bundle().also {
//                  it.putString("model" ,json)
//              }
//          )

              findNavController().navigate(
              resId = R.id.action_fragmentList_to_fragmentDetail,
              args = Bundle().also {
                  it.putInt("id" , model!!.id)
              }
          )
    }

}