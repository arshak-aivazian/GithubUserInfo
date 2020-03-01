package com.example.githubuserinfo.screens.user_list

import android.os.Bundle
import android.widget.Toast
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.githubuserinfo.MyApp
import com.example.githubuserinfo.R
import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User
import com.example.githubuserinfo.screens.user_detail.UserDetailActivity
import kotlinx.android.synthetic.main.activity_userlist.*
import javax.inject.Inject

class UserListActivity : MvpAppCompatActivity(), UserListView {

    lateinit var adapter: UserAdapter

    @Inject
    lateinit var model: UserListModel

    @InjectPresenter
    lateinit var presenter: UserListPresenter

    @ProvidePresenter
    fun providePresenter(): UserListPresenter {
        return UserListPresenter(model)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)


        adapter = UserAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setListener(object :
            UserAdapter.OnSelectedItem {
            override fun onClick(login: String) {
                presenter.onItemClicked(login)
            }
        })
    }

    override fun setUserList(pagedList: PagedList<User>) {
        adapter.submitList(pagedList)
    }

    override fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
    }

    override fun openUserDetail(login: String) {
        UserDetailActivity.startAcivity(this, login)
    }
}
