package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel3task2.models.Portal
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

private const val REQ_PORTAL_KEY = "req_portal_key"
private const val ARG_PORTAL_NAME = "arg_portal_name"
private const val ARG_PORTAL_URL = "arg_portal_url"
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalsAdapter =
            PortalsAdapter(portals, { portal: Portal -> portalItemClicked(portal) })

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a grid layout manager, adapter
        rvPortals.layoutManager =
                GridLayoutManager(context, 2)
        rvPortals.adapter = portalsAdapter

        observeAddReminderResult()
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            val portalTitle = bundle.getString(ARG_PORTAL_NAME)
            val portalUrl = bundle.getString(ARG_PORTAL_URL)
            if (!portalTitle.isNullOrBlank() && !portalUrl.isNullOrBlank()) {
                val portal = Portal(portalTitle, portalUrl)
                portals.add(portal)
                portalsAdapter.notifyDataSetChanged()
            } else {
                Log.e(
                        "PortalsFragment",
                        "Request triggered, but empty portal fields! Portal: $portalTitle , $portalUrl"
                )
            }
        }
    }

    private fun portalItemClicked(portalItem: Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()

        // If context is not null launch the Chrome Custom Tabs
        // The Uri needs to begin with 'http://', else you get No Activity found exception
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(Uri.decode(portalItem.url))) }

        Toast.makeText(context, "Clicked: ${portalItem.url}", Toast.LENGTH_LONG).show()
    }

}