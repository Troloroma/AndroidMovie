package com.example.core.uicomponent.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.navigation.NavigationFlow
import com.example.core.navigation.ToFlowNavigatable

abstract class BaseFragment<T: ViewBinding>(@LayoutRes idRes:Int) : Fragment(idRes) {
    protected abstract val binding: T

    fun showStatus(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(binding.root.context, text, duration).show()
    }

    //навигация между графами
    fun navigateTopLvl(navigationFlow: NavigationFlow){
        (requireActivity() as? ToFlowNavigatable)?.navigateToFlow(navigationFlow)
    }
    //навигация внутри графа просто через findNavController()
}
