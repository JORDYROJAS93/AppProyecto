package com.example.appproyecto.controller

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.appproyecto.entidad.Docente
import com.example.appproyecto.utils.appConfig

class DocenteController {
    //función para retornar un arreglo de objetos de la clase Docente
    fun findAll():ArrayList<Docente>{
        //crear arreglo de objetos
        var data=ArrayList<Docente>()
        //acceder a la base de datos en modo lectura(valor de retorno SQLiteDatabase)
        var CN=appConfig.BD.readableDatabase
        //sentencia sql
        var sql="select *from tb_docente"
        //obtener datos de la tabla tb_docente
        var RS=CN.rawQuery(sql,null)
        //bucle para realizar recorrido sobre RS
        while(RS.moveToNext()) {
            //objeto de la clase Docente con los valores de RS
            var bean=Docente(RS.getInt(0),RS.getString(1),
                             RS.getString(2),RS.getString(3),
                             RS.getString(4),RS.getDouble(5),
                             RS.getInt(6),RS.getString(7))
            data.add(bean)
        }
        return data
    }
    //función para registrar docente
    fun save(bean:Docente):Int{
        var estado=-1
        //acceder a la base de datos en modo escritura
        var CN=appConfig.BD.writableDatabase
        //objeto de la clase ContentValues
        var data=ContentValues()
        //asignar claves(nombre de campos de la tabla tb_docente)
        data.put("nom",bean.nombre)
        data.put("pat",bean.paterno)
        data.put("mat",bean.materno)
        data.put("sexo",bean.sexo)
        data.put("sue",bean.sueldo)
        data.put("hijos",bean.hijos)
        data.put("foto",bean.foto)
        estado=CN.insert("tb_docente","cod",data).toInt()
        return estado
    }
    //función para buscar Docente según código
    fun findById(codigo:Int):Docente{
        //declarar objeto de la clase Docente
        lateinit var bean:Docente
        var CN=appConfig.BD.readableDatabase
        var sql="select *from tb_docente where cod=?"
        var RS=CN.rawQuery(sql, arrayOf(codigo.toString()))
        if(RS.moveToNext()) {
            //instanciar ibjeto bean
            bean=Docente(RS.getInt(0),RS.getString(1),
                RS.getString(2),RS.getString(3),
                RS.getString(4),RS.getDouble(5),
                RS.getInt(6),RS.getString(7))
        }
        return bean
    }
    fun update(bean:Docente):Int{
        var estado=-1
        var CN=appConfig.BD.writableDatabase
        var data=ContentValues()
        data.put("nom",bean.nombre)
        data.put("pat",bean.paterno)
        data.put("mat",bean.materno)
        data.put("sexo",bean.sexo)
        data.put("sue",bean.sueldo)
        data.put("hijos",bean.hijos)
        data.put("foto",bean.foto)
        estado=CN.update("tb_docente",data,"cod=?", arrayOf(bean.codigo.toString()))
        return estado
    }
    fun delete(codigo:Int):Int{
        var estado=-1
        var CN=appConfig.BD.writableDatabase
        estado=CN.delete("tb_docente","cod=?", arrayOf(codigo.toString()))
        return estado
    }
}















