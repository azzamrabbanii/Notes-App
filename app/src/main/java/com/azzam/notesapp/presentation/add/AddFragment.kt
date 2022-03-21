package com.azzam.notesapp.presentation.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.azzam.notesapp.MainActivity
import com.azzam.notesapp.R
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.Priority
import com.azzam.notesapp.databinding.FragmentAddBinding
import com.azzam.notesapp.presentation.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val addViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarAdd.apply {
            setupWithNavController(navController, appBarConfiguration)
            (requireActivity() as MainActivity).setSupportActionBar(this)
            navController.addOnDestinationChangedListener{ _, destination, _ ->
                when (destination.id){
                    R.id.addFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener{
            insertNote()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            Toast.makeText(context, "successfully add note", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNote() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()
            val priority = spinnerPriorities.selectedItem.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar)

            val data = Notes(
                0,
                title,
                desc,
                date,
                parseToPriority(priority)
            )
            addViewModel.insertNotes(data)
        }

    }

    private fun parseToPriority(priority: String): Priority {
        val arrPriority = resources.getStringArray(R.array.priorities)
        return when (priority) {
            arrPriority[0] -> Priority.HIGH
            arrPriority[1] -> Priority.MEDIUM
            arrPriority[2] -> Priority.LOW
            else -> Priority.HIGH
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}