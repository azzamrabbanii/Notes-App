package com.azzam.notesapp.presentation.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.azzam.notesapp.MainActivity
import com.azzam.notesapp.R
import com.azzam.notesapp.databinding.FragmentUpdateBinding
import com.azzam.notesapp.utils.ExtensionFunctions.setActionBar


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarUpdate.apply {
            setupWithNavController(navController, appBarConfiguration)
            (requireActivity() as MainActivity).setSupportActionBar(this)
            navController.addOnDestinationChangedListener{ _, destination, _->
                when (destination.id) {
                    R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)

                }
            }
        }
        binding.toolbarUpdate.setActionBar(requireActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_detailFragment)
            Toast.makeText(context, "Notes has been Update", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}