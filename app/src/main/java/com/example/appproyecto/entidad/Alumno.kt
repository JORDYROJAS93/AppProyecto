package com.example.appproyecto.entidad

class Alumno {
    //atributos
    var dni:String=""
    var nombre:String=""
    var paterno:String=""
    var materno:String=""
    var sexo:String=""

    constructor(dni:String,nombre:String,paterno:String,materno:String,sexo:String){
        this.dni=dni
        this.nombre=nombre
        this.paterno=paterno
        this.materno=materno
        this.sexo=sexo
    }
    constructor(){

    }
}