package com.example.appproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var txtUsuario:TextInputEditText
    private lateinit var txtClave:TextInputEditText
    private lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtUsuario=findViewById(R.id.txtNombre)
        txtClave=findViewById(R.id.txtClave)
        btnLogin=findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { login() }
    }
    fun login(){
        var login:String; var clave:String
        login=txtUsuario.text.toString()
        clave=txtClave.text.toString()
        //showAlert("Login es : "+login+"\n Clave es : "+clave)
        //validar correo y clave
        FirebaseAuth.getInstance().signInWithEmailAndPassword(login,clave)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    var intent=Intent(this,MenuActivity::class.java)
                    startActivity(intent)
                }
                else
                    showAlert("Usuario y/o clave incorrectos")
            }
    }
    fun showAlert(men:String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
}