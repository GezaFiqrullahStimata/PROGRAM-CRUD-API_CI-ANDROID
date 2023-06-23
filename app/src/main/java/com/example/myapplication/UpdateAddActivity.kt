package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.DataItem
import com.example.myapplication.presenter.CrudView
import com.example.myapplication.presenter.Presenter2

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter2
    lateinit var btnAction : Button
    lateinit var etName : EditText
    lateinit var etPhone : EditText
    lateinit var etAlamat : EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        presenter = Presenter2(this)
        btnAction = findViewById(R.id.btnAction)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etAlamat = findViewById(R.id.etAlamat)

        val itemDataItem = intent.getSerializableExtra("dataItem")
        if (itemDataItem == null){
            btnAction.text = "Tambah"

            btnAction.setOnClickListener() {
                presenter.addData(
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAlamat.text.toString())
            }
        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAlamat.setText(item?.staffAlamat.toString())
            btnAction.setOnClickListener() {
                presenter.updateData(
                    item?.staffId ?: "",
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAlamat.text.toString())
                finish()
            }
        }
    }

    override fun successAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    override fun errorAdd(msg: String) {}
    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorUpdate(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}