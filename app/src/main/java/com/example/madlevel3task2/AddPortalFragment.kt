package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*
import androidx.fragment.app.setFragmentResult

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
private const val REQ_PORTAL_KEY = "req_portal_key"
private const val ARG_PORTAL_NAME = "arg_portal_name"
private const val ARG_PORTAL_URL = "arg_portal_url"
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_add_portal).setOnClickListener {

            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val portalTitleText = et_title.text.toString()
        val portalUrlText = et_url.text.toString()
        val args = Bundle()

        if (portalTitleText.isNotBlank() && portalUrlText.isNotBlank()) {

            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(ARG_PORTAL_NAME, portalTitleText), Pair(ARG_PORTAL_URL, portalUrlText)))
            // pop fragment off the back stack, this will bring back the RemindersFragment Fragment and close the AddReminderFragment
            findNavController().popBackStack()

        } else {
            Toast.makeText(activity, R.string.not_valid_portal, Toast.LENGTH_SHORT).show()
        }
    }

}