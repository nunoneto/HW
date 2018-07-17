package pt.nunoneto.hw.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class PropertyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, PropertyListFragment.newInstance())
                    .commitNow()
        }
    }
}