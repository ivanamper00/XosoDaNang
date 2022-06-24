package rem.apiv.xsnng

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import rem.apiv.xsnng.binding.viewBinding
import rem.apiv.xsnng.databinding.ActivityMainBinding
import rem.apiv.xsnng.fragment.ContentFragment
import rem.apiv.xsnng.fragment.Data
import rem.apiv.xsnng.fragment.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val navAdapter by lazy { NavAdapter(this) }

    lateinit var fragments: List<Fragment>

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {

        fragments = listOf(
            MainFragment(this),
            ContentFragment(Data.items[1]),
            ContentFragment(Data.items[2]),
            ContentFragment(Data.items[3]),
            ContentFragment(Data.items[4])
        )

        viewPagerAdapter = ViewPagerAdapter(this, fragments)

        with(binding){

            with(viewPager){
                offscreenPageLimit = 3
                adapter = viewPagerAdapter
                isUserInputEnabled = false
            }

            with(navContent){
                with(navRecycler){
                    adapter = navAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            menuButton.setOnClickListener {
                drawerToggle()
            }
        }
    }

    private fun drawerToggle() {
       if(binding.drawer.isDrawerOpen(GravityCompat.START)) closeDrawer()
        else openDrawer()
    }

    private fun closeDrawer() {
        binding.drawer.closeDrawer(GravityCompat.START)
    }

    private fun openDrawer() {
        binding.drawer.openDrawer(GravityCompat.START)
    }

    companion object {
        fun getStartActivity(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onSelectedItem(position: Int) {
        binding.viewPager.currentItem = position
        navAdapter.selectedPos = position
        closeDrawer()
    }

    override fun onBackPressed() {
        if(binding.viewPager.currentItem > 0){
            binding.viewPager.currentItem = 0
        }else AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }
}