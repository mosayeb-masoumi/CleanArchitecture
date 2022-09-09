package com.example.testcleanapplication.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.testcleanapplication.R
import com.example.testcleanapplication.databinding.FragmentDetailBinding
import com.example.testcleanapplication.domain.model.TodosModel
import com.example.testcleanapplication.presentation.list.ListViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentDetail : Fragment() {

    lateinit var binding: FragmentDetailBinding
//    private var json: String? = null

//    lateinit var todosModel: TodosModel

    private val viewModel by activityViewModels<DetailViewModel>()

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

//            json = it.getString("model")
//            val type = object : TypeToken<TodosModel?>() {}.type
//            todosModel = Gson().fromJson<TodosModel?>(json, type)
//            var ss = todosModel

            id = it.getInt("id" ,-1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        getDetailByLiveData()
        getDetailByFlow()

    }



    private fun getDetailByLiveData() {
        viewModel.getDetailByLiveData(id!!)
        viewModel.detailLiveData.observe(viewLifecycleOwner){
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
                    binding.progressbar.visibility = View.GONE
                    binding.txtError.visibility = View.GONE
                    binding.llDetail.visibility = View.VISIBLE

                    binding.txtId.text = "id is : ${it.data.id}"
                    binding.txtTitle.text = "title is : ${it.data.title}"
                    binding.txtUserId.text = "userId is : ${it.data.userId}"
                }

            }
        }
    }


    private fun getDetailByFlow() {

        lifecycleScope.launch{
            viewModel.getDetailByFlow(id!!)
            viewModel.detailStateFlow.collectLatest {

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
                        binding.progressbar.visibility = View.GONE
                        binding.txtError.visibility = View.GONE
                        binding.llDetail.visibility = View.VISIBLE

                        binding.txtId.text = "id is : ${it.data.id}"
                        binding.txtTitle.text = "title is : ${it.data.title}"
                        binding.txtUserId.text = "userId is : ${it.data.userId}"
                    }

                }
            }
        }
    }

}