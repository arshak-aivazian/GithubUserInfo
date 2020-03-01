package com.example.githubuserinfo.screens.user_detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.githubuserinfo.R
import com.example.githubuserinfo.databinding.ActivityUserDetailBinding
import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : MvpAppCompatActivity(), UserDetailView {

    companion object {
        val KEY_USER_LOGIN = "userLogin"

        fun startAcivity(activity: Activity, login: String) {
            val intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra(KEY_USER_LOGIN, login)
            activity.startActivity(intent)
        }
    }

    lateinit var binding: ActivityUserDetailBinding

    @InjectPresenter
    lateinit var presenter: UserDetailPresenter

    @ProvidePresenter
    fun providePresenter(): UserDetailPresenter {
        val model = UserDetailModel(ApiService.create())
        val login = intent.getStringExtra(KEY_USER_LOGIN)
        return UserDetailPresenter(model, login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        btn_browser.setOnClickListener { presenter.onBtnOpenClicked(btn_browser.tag as String) }
    }

    override fun setUserInfo(user: User) {
        binding.user = user
    }

    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
    }
}
