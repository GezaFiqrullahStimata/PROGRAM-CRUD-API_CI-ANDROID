package com.example.myapplication

import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.DataAdapter
import com.example.myapplication.model.DataItem
import com.example.myapplication.presenter.CrudView
import com.example.myapplication.presenter.Presenter

class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    lateinit var btnTambah : Button
    lateinit var rvCategory : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//tambahan
        presenter = Presenter(this)
        presenter.getData()
        btnTambah = findViewById(R.id.btnTambah)
        btnTambah.setOnClickListener {
            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
            finish()
        }
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory = findViewById(R.id.rvCategory)
        rvCategory.adapter = DataAdapter(data,object : DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                val bundle = Bundle()
                bundle.putSerializable("dataItem", item)
                val intent = Intent(applicationContext, UpdateAddActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })
    }
    override fun onFailedGet(msg: String) {
    }
    override fun onSuccessDelete(msg: String) {
        presenter.getData()
    }
    override fun onErrorDelete(msg: String) {
        Toast.makeText(
            this,
            "Delete Tidak Berhasil",
            Toast.LENGTH_SHORT
        ).show()
    }
    override fun successAdd(msg: String) {

    }
    override fun errorAdd(msg: String) {
    }
    override fun onSuccessUpdate(msg: String) {
    }
    override fun onErrorUpdate(msg: String) {
    }
}